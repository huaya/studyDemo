package com.maxlong.ftp;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPSClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2018-06-11 0011 20:32
 * 类说明
 */
public class FtpsDemo {

    public static void main(String[] args) {

        FTPSClient ftp = new FTPSClient();
        try {
            ftp.connect("172.28.250.9",21);

            ftp.enterLocalPassiveMode();
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            ftp.execPROT("P");

            Boolean login = ftp.login("ftpstest","123456");
            System.out.println(login);

            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

            File file = new File("D:\\Program Files\\workspace\\studyDemo\\src\\main\\java\\com\\maxlong\\ftp\\test.txt");
            FileInputStream input =new FileInputStream(file);

            ftp.changeWorkingDirectory("mxl");
            ftp.storeFile("text.txt",input);
            ftp.logout();
            ftp.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
