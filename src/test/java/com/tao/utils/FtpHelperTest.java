package com.tao.utils;

import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 28029 on 2018/3/25.
 */
public class FtpHelperTest {
    @Test
    public void openConnect() throws Exception {
        FtpHelper ftpHelper = new FtpHelper();
        try {
            ftpHelper.openConnect();
            System.out.println("ftp is Connect? -- "+ftpHelper.isConnect());
            List<FTPFile> files = ftpHelper.listFiles("/home/ftpuser/www");
            for(FTPFile file :files)
            {
                System.out.println(file.getName());
            }
            ftpHelper.closeConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void downloadFile() throws Exception {

    }

    @Test
    public void uploadFile() throws Exception {
        FtpHelper ftpHelper = new FtpHelper();
        try {
            ftpHelper.openConnect();
            System.out.println("ftp is Connect? -- "+ftpHelper.isConnect());
            if(!ftpHelper.isConnect())
            {
                System.out.println("connnect false");
                return;
            }
            ftpHelper.uploadFile("F:\\图片\\红宝石.jpg","/home/ftpuser/www/images");
            List<FTPFile> files = ftpHelper.listFiles("/home/ftpuser/www");
            for(FTPFile file :files)
            {
                System.out.println(file.getName());
            }
            ftpHelper.closeConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}