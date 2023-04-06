package top.kcoder.handler;

import org.springframework.stereotype.Component;
import top.kcoder.constant.Protocol;
import top.kcoder.domain.Frame;
import top.kcoder.domain.frame.IcmpFrame;
import top.kcoder.util.NetUtil;

/**
 * IcmpHandler
 *
 * @author xiejinjie
 * @date 2023/4/3
 */
@Component
public class IcmpHandler implements ProtocolHandler{
    @Override
    public Protocol protocol() {
        return Protocol.ICMP;
    }

    @Override
    public Frame parseFrame(byte[] buf, int offset, int endIndex) {
        int type = NetUtil.byteArrayToIntBigEndian(buf, offset, 1);
        int seq = NetUtil.byteArrayToIntBigEndian(buf, offset + 6, 2);
        String content = NetUtil.bytesToHexString(buf, offset + 8, endIndex - (offset + 8));

        IcmpFrame frame = new IcmpFrame();
        frame.setType(type);
        frame.setSeq(seq);
        frame.setContent(content);
        frame.setDataOffset(-1);
        return frame;
    }
}
