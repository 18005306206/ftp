package com.jk.util;

public class Ftp {

	private String ip;//ip

	private int port;//端口号

	private String userName;//用户名

	private String passWord;//密码

	private String sourceFileUrl;//上传或下载的文件路径

	private String ftpGoalDir;//上传目标路径

	private String downDir;//下载路径

	private String renameFileName;//上传后文件名

	private String downFileName;//下载后文件名

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSourceFileUrl() {
		return sourceFileUrl;
	}

	public void setSourceFileUrl(String sourceFileUrl) {
		this.sourceFileUrl = sourceFileUrl;
	}

	public String getFtpGoalDir() {
		return ftpGoalDir;
	}

	public void setFtpGoalDir(String ftpGoalDir) {
		this.ftpGoalDir = ftpGoalDir;
	}

	public String getDownDir() {
		return downDir;
	}

	public void setDownDir(String downDir) {
		this.downDir = downDir;
	}

	public String getRenameFileName() {
		return renameFileName;
	}

	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	

	
	
	
	
	
	
	
}
