package top.kcoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import top.kcoder.domain.PcapFile;
import top.kcoder.service.IPcapService;
import top.kcoder.util.BeanUtil;

import java.io.File;

/**
 * Test
 *
 * @author xiejinjie
 * @date 2023/3/24
 */
@SpringBootApplication
public class PcapFileParserApp implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(PcapFileParserApp.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(PcapFileParserApp.class)
                .web(WebApplicationType.NONE)
                .headless(false)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) {
        String[] sourceArgs = args.getSourceArgs();
        if (sourceArgs.length > 0) {
            IPcapService pcapService = BeanUtil.getBean(IPcapService.class);
            PcapFile pcapFile = pcapService.readFile(new File(sourceArgs[0]));
            pcapService.print(pcapFile);
        }
    }
}
