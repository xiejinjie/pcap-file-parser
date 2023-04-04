package top.kcoder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kcoder.constant.Protocol;
import top.kcoder.domain.Frame;
import top.kcoder.domain.PcapFile;
import top.kcoder.domain.PcapHeader;
import top.kcoder.domain.PcapPacket;
import top.kcoder.handler.ProtocolHandler;
import top.kcoder.service.IPcapService;
import top.kcoder.util.NetUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static top.kcoder.constant.Constant.*;

/**
 * pcap文件格式：|-PcapHeader(24B)-|-PacketHeader(16B)-|-PacketData-|
 * PcapHeader格式：|-Magic(4B)-|-Major(2B)-|-Minor(2B)-|-ThisZone(4B)-|-SigFlgs(4B)-|-SnapLen(4B)-|-LinkType(4B)-|
 * PacketHeader格式：|-Timestamp(4B)-|-Timestamp(4B)-|-CapLen(4B)-|-Len(4B)-|
 * PacketData：一般对应 LinkType=1，表示一个以太帧
 * 以太帧格式：|-DstMac(6B)-|-SrcMac(6B)-|-Type(2B)-|-Data-|
 * 以太帧 Data：Type为 0x08，ipv4报文；Type为 0x86dd，ipv6报文
 * ipv6报头(40B)格式：|-Version(4b)-|-TrafficClass(8b)-|-FlowLabel(20b)-|-PayloadLength(2B)-|-NextHeader(1B)-|-HopLimit(1B)-|-SourceAddress(16B)-|-DestinationAddress(16B)-|
 *
 * @author xiejinjie
 */
@Service
public class PcapServiceImpl implements IPcapService {
    private static final Logger logger = LoggerFactory.getLogger(PcapServiceImpl.class);

    Map<Protocol, ProtocolHandler> protocolHandlerMap;


    @Autowired
    public PcapServiceImpl(List<ProtocolHandler> protocolHandlers) {
        this.protocolHandlerMap = new HashMap<>();
        for (ProtocolHandler p : protocolHandlers) {
            protocolHandlerMap.put(p.protocol(), p);
        }
    }

    @Override
    public void print(PcapFile pcapFile) {
        logger.info("Pcap file parse result:");

        List<PcapPacket> packetList = pcapFile.getPacketList();

        logger.info(pcapFile.getPcapHeader().toString());

        if (packetList != null && packetList.size() > 0) {
            for (PcapPacket pcapPacket: packetList) {
                logger.info("============================================================");
                Frame frame = pcapPacket.getFrame();
                logger.info(frame.toString());
                while ((frame = frame.getData()) != null) {
                    logger.info(frame.toString());
                }
            }
        }
    }

    @Override
    public PcapFile readFile(File file) {
        if (file == null || file.isDirectory()) {
            return null;
        }
        String[] ss = file.getName().split("\\.");
        if (!"pcap".equals(ss[ss.length - 1])) {
            return null;
        }
        logger.info("Read file {} ...", file.getName());
        try (FileInputStream fis = new FileInputStream(file)) {
            // The MTU size for Ethernet is 1500 bytes，so the max size of PacketData is 1514
            byte[] buf = new byte[1514];

            // PcapHeader
            fis.read(buf, 0, PCAP_HEADER_LEN);
            int linkType = NetUtil.byteArrayToIntLittleEndian(buf, 20, 4);
            PcapHeader pcapHeader = new PcapHeader();
            pcapHeader.setLinkType(linkType);

            // PcapPacket
            List<PcapPacket> packetList = new ArrayList<>();
            while (fis.read(buf, 0, PACKET_HEADER_LEN) != -1) {
                // PacketHeader
                long time = 1000L * NetUtil.byteArrayToIntLittleEndian(buf, 0, 4);
                int capLen = NetUtil.byteArrayToIntLittleEndian(buf, 8, 4);
                // PacketData
                fis.read(buf, 0, capLen);
                Frame frame = parseFrame(buf, linkType);
                PcapPacket pcapPacket = new PcapPacket();
                pcapPacket.setFrame(frame);
                packetList.add(pcapPacket);
            }
            PcapFile pcapFile = new PcapFile();
            pcapFile.setPcapHeader(pcapHeader);
            pcapFile.setPacketList(packetList);
            pcapFile.setFileName(file.getName());
            return pcapFile;
        } catch (IOException e) {
            logger.error("Read file failure", e);
        }
        return null;
    }

    private Frame parseFrame(byte[] buf, int linkType) {
        Frame res = null;
        Frame lastFrame = null;
        int dataOffset = 0;
        int dataType = linkType;
        for (int i = 0; i < PROCESS_PIPELINE.length; i++) {
            ProtocolHandler handler = findHandler(PROCESS_PIPELINE[i], dataType);
            if (handler == null) {
                logger.warn("There is no matching handler.");
                break;
            }
            Frame frame = handler.parseFrame(buf, dataOffset);
            dataOffset = frame.getDataOffset();
            dataType = frame.getDataType();
            if (lastFrame != null) {
                lastFrame.setData(frame);
            }
            lastFrame = frame;
            if (res == null) {
                res = frame;
            }
        }
        return res;
    }

    private ProtocolHandler findHandler(Protocol[] protocols, int type) {
        for (Protocol p : protocols) {
            if (p.getType() == type) {
                return protocolHandlerMap.get(p);
            }
        }
        return null;
    }
}
