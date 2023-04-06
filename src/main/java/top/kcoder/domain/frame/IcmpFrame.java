package top.kcoder.domain.frame;

import top.kcoder.domain.Frame;

/**
 * IcmpFrame
 *
 * @author xiejinjie
 * @date 2023/4/3
 */
public class IcmpFrame extends Frame {
    private int type;

    private int seq;

    private String content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "IcmpFrame{" +
                "type=" + type +
                ", seq=" + seq +
                ", content='" + content + '\'' +
                '}';
    }
}
