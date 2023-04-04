package top.kcoder.service;

import top.kcoder.domain.PcapFile;

import java.io.File;

/**
 * IPcapService
 *
 * @author xiejinjie
 * @date 2023/3/28
 */
public interface IPcapService {

    void print(PcapFile pcapFile);

    PcapFile readFile(File file);
}
