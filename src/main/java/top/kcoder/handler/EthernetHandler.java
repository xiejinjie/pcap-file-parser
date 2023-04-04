package top.kcoder.handler;

import org.springframework.stereotype.Component;
import top.kcoder.constant.Protocol;
import top.kcoder.domain.Frame;
import top.kcoder.domain.frame.EthernetFrame;
import top.kcoder.util.NetUtil;

/**
 * EthernetHandler
 *
 * @author xiejinjie
 * @date 2023/4/3
 */
@Component
public class EthernetHandler implements ProtocolHandler{
    @Override
    public Protocol protocol() {
        return Protocol.Ethernet;
    }

    @Override
    public Frame parseFrame(byte[] buf, int offset) {
        String dstMac = NetUtil.bytesToHexString(buf, offset, 6);
        String srcMac = NetUtil.bytesToHexString(buf, offset + 6, 6);
        int type = NetUtil.byteArrayToIntBigEndian(buf, offset + 12, 2);

        EthernetFrame frame = new EthernetFrame();
        frame.setDstMac(dstMac);
        frame.setSrcMac(srcMac);
        frame.setDataType(type);
        frame.setDataOffset(offset + 14);
        return frame;
    }
}
