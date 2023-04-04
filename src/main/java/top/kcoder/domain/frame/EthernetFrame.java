package top.kcoder.domain.frame;

import top.kcoder.domain.Frame;

/**
 * Ethernet
 *
 * @author xiejinjie
 * @date 2023/4/3
 */
public class EthernetFrame extends Frame {
    private String dstMac;
    private String srcMac;

    public String getDstMac() {
        return dstMac;
    }

    public void setDstMac(String dstMac) {
        this.dstMac = dstMac;
    }

    public String getSrcMac() {
        return srcMac;
    }

    public void setSrcMac(String srcMac) {
        this.srcMac = srcMac;
    }

    @Override
    public String toString() {
        return "Ethernet{" +
                "dstMac='" + dstMac + '\'' +
                ", srcMac='" + srcMac + '\'' +
                '}';
    }
}
