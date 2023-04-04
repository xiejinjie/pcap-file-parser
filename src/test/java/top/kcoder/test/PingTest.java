package top.kcoder.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.kcoder.domain.PcapFile;
import top.kcoder.service.IPcapService;

import java.io.File;

/**
 * PingTest
 *
 * @author xiejinjie
 * @date 2023/4/3
 */
public class PingTest extends BaseTest{
    @Autowired
    private IPcapService pcapService;

    @Test
    public void pingV6Test() {
        PcapFile pcapFile = pcapService.readFile(getTestFile("data/ping-v4.pcap"));
        pcapService.print(pcapFile);
    }
}
