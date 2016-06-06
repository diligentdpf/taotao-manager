


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import diao.taotao.common.util.FtpUtil;


public class FtpUploadTest {
    @Test
    public void ftpTest() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.1.191", 21);
        try {
            ftpClient.login("ftpuser", "123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream inputStream =
                new FileInputStream(new File("E:\\DownLoadJpg\\scenery\\1 (15).jpg"));
        System.out.println(inputStream.available());
        ftpClient.changeWorkingDirectory("/home/ftpuser/html/image");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.storeFile("peizhixiu3.jpg", inputStream);
        ftpClient.logout();
    }

    @Test
    public void testFtpUtil() throws Exception {
        // FileInputStream inputStream = new FileInputStream(new File("E:" + File.separator
        // + "DownLoadJpg" + File.separator + "scenery" + File.separator + "1 (15).jpg"));
        // D:\Documents\picture E:\DownLoadJpg\scenery
        // FileInputStream inputStream = new FileInputStream(new File("d:" + File.separator
        // + "Documents" + File.separator + "picture" + File.separator + "1 (15).jpg"));
        FileInputStream inputStream = new FileInputStream(new File("e:" + File.separator
                + "DownLoadJpg" + File.separator + "scenery" + File.separator + "1 (15).jpg"));
        // 连接地址，端口；用户名，密码；ftp的工作路径；绝对路径下的日期相对路径；服务器上文件名；要上传文档对应的流
        // FtpUtil.uploadFile("192.168.1.191", 21, "ftpuser", "123456", "/home/ftpuser/html/image",
        // "/2016/05/11", "peizhixiu4.jpg", inputStream);
        boolean success = FtpUtil.uploadFile("120.24.53.46", 21, "ftpuser", "123456",
                "/home/ftpuser/html/images", "/2016/06/02", "peizhixiu"+(int)(Math.random()*999)+".jpg", inputStream);
        System.out.println(success);

    }


}
