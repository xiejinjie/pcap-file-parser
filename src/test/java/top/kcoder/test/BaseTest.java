package top.kcoder.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.kcoder.PcapFileParserApp;

import java.io.File;
import java.net.URL;

/**
 * BaseTest
 *
 * @author xiejinjie
 * @date 2023/4/3
 */
@SpringBootTest(classes = PcapFileParserApp.class)
@RunWith(SpringRunner.class)
public abstract class BaseTest {

    protected File getTestFile(String file) {
        URL fileUrl = BaseTest.class.getClassLoader().getResource(file);
        if (fileUrl == null) {
            return null;
        }
        return new File(fileUrl.getFile());
    }
}
