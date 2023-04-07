# Pcap 文件解析

通过解析pcap文件，学习相关的网络协议

pcap文件格式：|-PcapHeader(24B)-|-PacketHeader(16B)-|-PacketData-|

PcapHeader格式：|-Magic(4B)-|-Major(2B)-|-Minor(2B)-|-ThisZone(4B)-|-SigFlgs(4B)-|-SnapLen(4B)-|-LinkType(4B)-|

PacketHeader格式：|-Timestamp(4B)-|-Timestamp(4B)-|-CapLen(4B)-|-Len(4B)-|
