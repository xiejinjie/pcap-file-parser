package top.kcoder.domain;

/**
 * Frame
 *
 * @author xiejinjie
 * @date 2023/3/28
 */
public class Frame {
    private Frame data;

    protected int dataType;
    protected int dataOffset;

    public Frame getData() {
        return data;
    }

    public void setData(Frame data) {
        this.data = data;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getDataOffset() {
        return dataOffset;
    }

    public void setDataOffset(int dataOffset) {
        this.dataOffset = dataOffset;
    }
}
