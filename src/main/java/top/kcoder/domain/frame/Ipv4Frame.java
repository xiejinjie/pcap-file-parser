package top.kcoder.domain.frame;

import top.kcoder.domain.Frame;

/**
 * IpFrame
 *
 * @author xiejinjie
 * @date 2023/4/3
 */
public class Ipv4Frame extends Frame {
    private int version;

    private int headLen;

    private int ttl;

    private String srcIp;

    private String dstIp;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getHeadLen() {
        return headLen;
    }

    public void setHeadLen(int headLen) {
        this.headLen = headLen;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public String getDstIp() {
        return dstIp;
    }

    public void setDstIp(String dstIp) {
        this.dstIp = dstIp;
    }


    @Override
    public String toString() {
        return "Ipv4Frame{" +
                "version=" + version +
                ", headLen=" + headLen +
                ", ttl=" + ttl +
                ", srcIp='" + srcIp + '\'' +
                ", dstIp='" + dstIp + '\'' +
                '}';
    }
}
