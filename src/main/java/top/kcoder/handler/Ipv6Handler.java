package top.kcoder.handler;

import org.springframework.stereotype.Component;
import top.kcoder.constant.Protocol;
import top.kcoder.domain.Frame;

/**
 * Ipv6Handler
 *
 * @author xiejinjie
 * @date 2023/3/28
 */
@Component
public class Ipv6Handler implements ProtocolHandler{
    @Override
    public Protocol protocol() {
        return Protocol.IPv6;
    }

    @Override
    public Frame parseFrame(byte[] buf, int offset) {
        return null;
    }
}
