package top.kcoder.handler;

import org.springframework.stereotype.Component;
import top.kcoder.constant.Protocol;
import top.kcoder.domain.Frame;
import top.kcoder.domain.frame.Ipv4Frame;
import top.kcoder.util.NetUtil;

/**
 * Ipv4Handler
 *
 * @author xiejinjie
 * @date 2023/3/28
 */
@Component
public class Ipv4Handler implements ProtocolHandler{
    @Override
    public Protocol protocol() {
        return Protocol.IPv4;
    }

    @Override
    public Frame parseFrame(byte[] buf, int offset, int endIndex) {
        int t = NetUtil.byteArrayToIntBigEndian(buf, offset, 1);
        int version = (t & 0xf0) >> 4;
        int headLen = (t & 0x0f);
        int ttl = NetUtil.byteArrayToIntBigEndian(buf, offset + 8, 1);
        int protocol = NetUtil.byteArrayToIntBigEndian(buf, offset + 9, 1);
        String srcIp = NetUtil.byteArrayToIpv4String(buf, offset + 12);
        String dstIp = NetUtil.byteArrayToIpv4String(buf, offset + 16);

        Ipv4Frame frame = new Ipv4Frame();
        frame.setVersion(version);
        frame.setHeadLen(headLen);
        frame.setTtl(ttl);
        frame.setSrcIp(srcIp);
        frame.setDstIp(dstIp);

        frame.setDataType(protocol);
        frame.setDataOffset(offset + headLen * 4);

        return frame;
    }
}
