package top.kcoder.domain;

import java.util.List;

/**
 * PcapFile
 *
 * @author xiejinjie
 * @date 2023/3/28
 */
public class PcapFile {
    private String fileName;

    private PcapHeader pcapHeader;

    private List<PcapPacket> packetList;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public PcapHeader getPcapHeader() {
        return pcapHeader;
    }

    public void setPcapHeader(PcapHeader pcapHeader) {
        this.pcapHeader = pcapHeader;
    }

    public List<PcapPacket> getPacketList() {
        return packetList;
    }

    public void setPacketList(List<PcapPacket> packetList) {
        this.packetList = packetList;
    }
}
