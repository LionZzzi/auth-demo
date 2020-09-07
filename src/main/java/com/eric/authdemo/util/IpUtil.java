package com.eric.authdemo.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.*;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Eric
 */
@Slf4j
public class IpUtil {

    public static void info(String ip) throws DbMakerConfigException, FileNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TimeInterval timer = DateUtil.timer();
        String dbPath = IpUtil.class.getResource("/ip2region.db").getPath();
        DbConfig dbConfig = new DbConfig();
        DbSearcher dbSearcher = new DbSearcher(dbConfig, dbPath);
        Method method = dbSearcher.getClass().getMethod("btreeSearch", String.class);
        DataBlock dataBlock = (DataBlock) method.invoke(dbSearcher, ip);
        System.out.println(timer.intervalRestart());
        System.out.println(dataBlock.getRegion());
    }

    public static void main(String[] args) throws DbMakerConfigException, FileNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        info("111.230.130.61");
    }
}
