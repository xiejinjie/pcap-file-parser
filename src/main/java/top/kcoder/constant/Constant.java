package top.kcoder.constant;

import static top.kcoder.constant.Protocol.*;

public class Constant {
    public static final int PCAP_HEADER_LEN = 24;
    public static final int PACKET_HEADER_LEN = 16;

    public static final int TYPE_VLAN = 0x8100;
    public static final int TYPE_IPV6 = 0x86dd;
    public static final int VERSION_IPV6 = 6;

    public static final int HEADER_IDP = 0x92;

    public static final int HEADER_SEAGP = 0x93;

    public static final int HEADER_SEASP = 3;

    public static final int FRAME_TYPE_STREAM_MAX = 0x7;

    /*
     * 计算标识
     */
    public static final int SIGN_HIDDEN = 0x80;

    public static Protocol[][] PROCESS_PIPELINE = {
            {Ethernet, LinuxSll},
            {IPv4, IPv6},
            {TCP, UDP}
    };
}