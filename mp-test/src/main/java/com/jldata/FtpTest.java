package com.jldata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Lijinliang
 * @date 2019/6/10 13:47
 */
public class FtpTest {
    public static void main(String[] args) throws Exception{
        //上传
        FTPUtil ftpUtil = new FTPUtil();
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\dhc\\Desktop\\测试问题.xlsx"));
        ftpUtil.uploadFile("/temp/",fis,"测试问题.xlsx");
        //下载
        FileOutputStream _bis = new FileOutputStream(new File("d:\\测试问题.xlsx"));
        ftpUtil.downloadFile("/temp/","测试问题.xlsx",_bis);

    }
}
