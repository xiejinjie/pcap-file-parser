package top.kcoder.handler;

import top.kcoder.constant.Protocol;
import top.kcoder.domain.Frame;

/**
 * ProtocolHandler
 *
 * @author xiejinjie
 * @date 2023/3/28
 */
public interface ProtocolHandler {
    Protocol protocol();

    Frame parseFrame(byte[] buf, int offset);
}
