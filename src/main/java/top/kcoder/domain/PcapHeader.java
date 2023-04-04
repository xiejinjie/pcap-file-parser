package top.kcoder.domain;

/**
 * PcapHeader
 *
 * @author xiejinjie
 * @date 2023/4/4
 */
public class PcapHeader {
    private int linkType;

    public int getLinkType() {
        return linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
    }

    @Override
    public String toString() {
        return "PcapHeader{" +
                "linkType=" + linkType +
                '}';
    }
}
