package top.kcoder.domain;

import java.util.List;

/**
 * Protocol
 *
 * @author xiejinjie
 * @date 2023/4/4
 */
public class Protocol {
    private String protocolName;

    private int type;

    private List<Protocol> upProtocols;

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Protocol> getUpProtocols() {
        return upProtocols;
    }

    public void setUpProtocols(List<Protocol> upProtocols) {
        this.upProtocols = upProtocols;
    }
}
