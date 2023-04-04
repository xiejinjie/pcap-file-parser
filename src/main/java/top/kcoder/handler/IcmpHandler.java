package top.kcoder.handler;

import org.springframework.stereotype.Component;
import top.kcoder.constant.Protocol;
import top.kcoder.domain.Frame;

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
        return null;
    }

    @Override
    public Frame parseFrame(byte[] buf, int offset) {
        return null;
    }
}
