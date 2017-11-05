package ftp;

import org.junit.Test;

import com.jk.util.Ftp;
import com.jk.util.FtpTest1;




public class testUser {
	
	@Test
	public void upload(){
		Ftp ftp=new Ftp();
		ftp.setIp("127.0.0.1");
		ftp.setPort(21);
		ftp.setUserName("py");
		ftp.setPassWord("py");
		ftp.setSourceFileUrl("D:\\1.jpg");
		ftp.setFtpGoalDir("D:\\ftp");
		ftp.setRenameFileName("1.jpg");
		FtpTest1.testUpload(ftp);
	}
	
	
	public void delete(){
		Ftp ftp=new Ftp();
		ftp.setIp("127.0.0.1");
		ftp.setPort(21);
		ftp.setUserName("py");
		ftp.setPassWord("py");
		ftp.setFtpGoalDir("D:\\ser");
		ftp.setDownFileName("22.jpg");

		FtpTest1.deleteFile(ftp);
		
	}
	
}
