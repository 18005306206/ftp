package com.jk.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.SocketException;

public class FtpTest1 {

    /**

     * FTP上传单个文件测试

     */

/**

*

*/

 

/***

* 经测试可以上传文件类型:rar,xls,doc,txt,gif

* @ftpGoalDir:ftp服务器的目录  

* @sourceFileUrl 上传文件的路径

*

*/

    public static boolean testUpload(Ftp ftp) {

        //上传不成功

     boolean res=false;

     //创建一个连接FTP服务器对象

     FTPClient ftpClient = new FTPClient();

        //创建一个文件写入流对象

        FileInputStream fis = null;

 

 

        try {

         //连接FTP服务器

            ftpClient.connect(ftp.getIp());

            //登录FTP连接

            ftpClient.login(ftp.getUserName(), ftp.getPassWord());

            //创建文件对象

            File srcFile = new File(ftp.getSourceFileUrl());

            //向FTP服务器写入文件

            fis = new FileInputStream(srcFile);

            //设置上传目录

            ftpClient.changeWorkingDirectory(ftp.getFtpGoalDir());

            //向目标服务器写入文件大小限制

            ftpClient.setBufferSize(1024);

            //

            ftpClient.setControlEncoding("utf-8");

            //设置文件类型（二进制）

            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            ftpClient.storeFile(ftp.getRenameFileName(), fis);

            res=true;

            System.out.println("upload successful!");

        } catch (IOException e) {

            e.printStackTrace();

            throw new RuntimeException("FTP客户端出错！", e);

        } finally {

            IOUtils.closeQuietly(fis);

            try {

                ftpClient.disconnect();

            } catch (IOException e) {

                e.printStackTrace();

                throw new RuntimeException("关闭FTP连接发生异常！", e);

            }

        }

       

        return res;

    }

 
    
    //--------------------------------------------------------------------


    public static boolean downFile(Ftp ft) { 
        boolean success = false; 
        FTPClient ftp = new FTPClient(); 
        try { 
            int reply; 
            ftp.connect(ft.getIp(), ft.getPort()); 
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
            ftp.login(ft.getUserName(), ft.getPassWord());//登录 
            reply = ftp.getReplyCode(); 
            if (!FTPReply.isPositiveCompletion(reply)) { 
                ftp.disconnect(); 
                return success; 
            } 
            ftp.changeWorkingDirectory(ft.getSourceFileUrl());//转移到FTP服务器目录 
            FTPFile[] fs = ftp.listFiles(); 
            
            for(FTPFile ff:fs){ 
                if(ff.getName().equals(ft.getDownFileName())){ 
                    File localFile = new File(ft.getDownDir()+"/"+ff.getName()); 
                     
                    OutputStream is = new FileOutputStream(localFile);  
                    ftp.retrieveFile(ff.getName(), is); 
                    is.close(); 
                    System.out.println("下载完毕----");
                } 
            } 
             
            ftp.logout(); 
            success = true; 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            if (ftp.isConnected()) { 
                try { 
                    ftp.disconnect(); 
                } catch (IOException ioe) { 
                } 
            } 
        } 
        return success; 
    }

    /** 
     * 删除FTP上的文件 
     */  
      
 
    	// 删除文件至FTP通用方法

  	
    	
    	
   public static boolean deleteFile(Ftp ftp){
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        try {
         //连接FTP服务器
         ftpClient.connect(ftp.getIp(), ftp.getPort());
         //登录FTP服务器
         ftpClient.login(ftp.getUserName(), ftp.getPassWord());
         //验证FTP服务器是否登录成功
         int replyCode = ftpClient.getReplyCode();
         if(!FTPReply.isPositiveCompletion(replyCode)){
          return flag;
         }
         //切换FTP目录
         ftpClient.changeWorkingDirectory(ftp.getFtpGoalDir());
         ftpClient.dele(ftp.getDownFileName());
         ftpClient.logout();
         flag = true;
        } catch (Exception e) {
         e.printStackTrace();
        } finally{
         if(ftpClient.isConnected()){
          try {
           ftpClient.logout();
          } catch (IOException e) {
          }
         }
        }
        return flag;
       }

   
   
   
   
    	///
	public static boolean downAllFile(Ftp ftp) { 
	boolean success = false; 
	FTPClient ftpc = new FTPClient(); 
	String LOCAL_CHARSET="utf-8";
	try { 

	//ftpc.setCharset(Charset.forName("GBK"));

	////ftp中文编码设置
	ftpc.setControlEncoding("utf-8");
	FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX); 
	conf.setServerLanguageCode("zh");
	ftpc.configure(conf);

	int reply; 
	ftpc.connect(ftp.getIp(),ftp.getPort()); 


	//如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	ftpc.login(ftp.getUserName(), ftp.getPassWord());//登录 
	reply = ftpc.getReplyCode(); 
	if (!FTPReply.isPositiveCompletion(reply)) { 
	ftpc.disconnect(); 
	return success; 
	} 


	ftpc.changeWorkingDirectory(ftp.getSourceFileUrl());//转移到FTP服务器目录 
	ftpc.enterLocalPassiveMode(); //设置被动模式
	ftpc.setFileType(ftpc.BINARY_FILE_TYPE); //设置下载文件为二进制模式
	ftpc.setFileTransferMode(ftpc.STREAM_TRANSFER_MODE); ///传输文件为流的形式




	FTPFile[] fs = ftpc.listFiles();
	////判断本地的日期路径是否存在
	File file = new File(ftp.getDownDir());
	//判断文件夹是否存在,如果不存在则创建文件夹
	if (!file.exists()) {
	file.mkdir();
	}
	
	///循环下载
	for(FTPFile ff:fs){ 
    
 
 
   	    File localFile = new File(ftp.getDownDir()+"/"+ff.getName()); 

   		OutputStream is = new FileOutputStream(localFile);


   		ftpc.retrieveFile(new String(ff.getName().getBytes("utf-8"), "GBK"), is); 
   		is.close(); 

   		System.out.println("下载完毕----");
      
	

	} 

	ftpc.logout(); 
	success = true; 
	} catch (IOException e) { 
	//e.printStackTrace(); 

	} finally { 
	if (ftpc.isConnected()) { 
	try { 
	ftpc.disconnect(); 
	} catch (IOException ioe) { 
	} 
	} 
	} 
	return success; 
	}
    	
    	
    	
    	
    }    




