package top.kcoder.handler;

import org.springframework.stereotype.Component;
import top.kcoder.constant.Protocol;
import top.kcoder.domain.Frame;
import top.kcoder.domain.frame.LinuxSllFrame;
import top.kcoder.util.NetUtil;


/**
 * LinuxSllHandler
 *
 * @author xiejinjie
 * @date 2023/4/4
 */
@Component
public class LinuxSllHandler implements ProtocolHandler {
    @Override
    public Protocol protocol() {
        return Protocol.LinuxSll;
    }

    @Override
    public Frame parseFrame(byte[] buf, int offset) {
        int type = NetUtil.byteArrayToIntBigEndian(buf, offset + 14, 2);
        LinuxSllFrame frame = new LinuxSllFrame();
        frame.setDataType(type);
        frame.setDataOffset(offset + 16);
        return frame;
    }
}
