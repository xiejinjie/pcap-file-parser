package top.kcoder.constant;

/**
 * Protocol
 *
 * @author xiejinjie
 * @date 2023/3/28
 */
public enum Protocol {
    Ethernet(1),
    LinuxSll(113),
    IPv4(0x0800),
    IPv6(0x86dd),
    ICMP(1),
    TCP(6),
    UDP(17);

    private int type;

    Protocol(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
