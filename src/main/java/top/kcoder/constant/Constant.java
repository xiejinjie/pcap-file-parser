package top.kcoder.constant;

import static top.kcoder.constant.Protocol.*;

public class Constant {
    public static final int PCAP_HEADER_LEN = 24;
    public static final int PACKET_HEADER_LEN = 16;

    public static final int TYPE_VLAN = 0x8100;
    public static final int TYPE_IPV6 = 0x86dd;


    public static Protocol[][] PROCESS_PIPELINE = {
            {Ethernet, LinuxSll},
            {IPv4, IPv6},
            {TCP, UDP}
    };
}