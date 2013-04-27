package com.tycomputer.yyc.manager.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpServiceImpl {

	private String hostname;
	private int port = 21;
	private String username;
	private String password;
	private String localPath;// 本地要上传的目录
	private String remotePath = "";// ftp要存放的目录
	private FTPClient ftpClient;
	private boolean forceUpload = false;
	private List<String> uploadFileList = new ArrayList<String>();

	private boolean cd(String path) {
		boolean b = false;
		try {
			if (ftpClient == null || !ftpClient.isConnected()) {
				this.connect();
			}
			if ((path != null) && (!path.equals(""))) {
				b = ftpClient.changeWorkingDirectory(path);
				if (!b) {

					b = ftpClient.makeDirectory(path);
					if (b) {
						b = ftpClient.changeWorkingDirectory(path);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
//
//	public static void main(String[] args) {
//		FtpServiceImpl ftpService = new FtpServiceImpl();
//		ftpService.setHostname("211.147.221.5");
//		ftpService.setUsername("yuyingcai.com.cn");
//		ftpService.setPassword("EBt7VH3aVw");
//		ftpService.setLocalPath("/home/zhangliuhua/developer/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/dclipinyyc/html");
//		//
//		ftpService.setLocalPath("/home/zhangliuhua/developer/workspace/dclipinyyc/WebContent/html");
//		ftpService.setRemotePath("htdocs");
//		ftpService.setForceUpload(true);
//		try {
//			ftpService.delFtpFiles(false);
//			
//			//
//			// File upload = new File(ftpService.getLocalPath());
//			// if (ftpService.getRemotePath() != null &&
//			// !ftpService.getRemotePath().trim().equals("")) {
//			// ftpService.cd(ftpService.getRemotePath());
//			// }
//			// ftpService.uploadFilesFromLocal(upload, true);
//			// ftpService.closeConn();
//			// for (String uploadFile : ftpService.getUploadFileList()) {
//			// System.out.println(uploadFile);
//			// }
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public boolean delFtpFiles(){
		boolean b = delFtpFiles(true);
		this.closeConn();
		return b;
	}
	

	/**
	 * 删除ftp上remotePath下的所有文件
	 * 
	 * @param isFirst
	 *            是否第一次，第一次就先 cd remotePath
	 * @return
	 */
	private boolean delFtpFiles(boolean isFirst) {
		boolean result = false;
		try {
			if (ftpClient == null || !ftpClient.isConnected()) {
				this.connect();
			}
			if (isFirst) {
				cd(remotePath);
			}
			FTPFile[] ftpFiles = ftpClient.listFiles();
			for (FTPFile ftpFile : ftpFiles) {
				if (ftpFile.isFile() && !ftpFile.getName().equals(".") && (!ftpFile.getName().equals(".."))) {
					ftpClient.deleteFile(ftpFile.getName());
				}
			}
			FTPFile[] ftpDirs = ftpClient.listDirectories();
			for (FTPFile ftpDir : ftpDirs) {
				if (!ftpDir.getName().equals(".") && (!ftpDir.getName().equals(".."))) {
					cd(ftpDir.getName());
					delFtpFiles(false);
					ftpClient.changeToParentDirectory();
					ftpClient.rmd(ftpDir.getName());
				}
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} 
		return result;
	}

	/**
	 * 上传文件到ftp
	 * 
	 * @return 成功上传的文件名称
	 */
	public boolean uploadFiles(String servletContextPath) {
		File upload = new File(servletContextPath + (localPath == null ? "" : localPath));
		if (this.remotePath != null && !this.remotePath.trim().equals("")) {
			cd(this.remotePath);
		}
		boolean r = this.uploadFilesFromLocal(upload, true);
		this.closeConn();
		return r;

	}

	/**
	 * 上传本地路径localPath下的所有文件到ftp服务器
	 * 
	 * @param localFile
	 *            本地路径
	 * @param isEnterPath
	 *            是否已进入目录
	 * @return
	 */
	private boolean uploadFilesFromLocal(File localFile, boolean isEnterPath) {
		boolean result = false;
		try {
			if (ftpClient == null || !ftpClient.isConnected()) {
				this.connect();
			}
			if (localFile.exists()) {
				if (localFile.isDirectory()) {
					// 目录
					if (!isEnterPath) {
						cd(localFile.getName());
					}
					FTPFile[] ftpFiles = ftpClient.listFiles();
					File[] child = localFile.listFiles();
					for (int i = 0; i < child.length; i++) {
						if (child[i].isDirectory()) {
							uploadFilesFromLocal(child[i], false);
						} else {
							boolean upload = false;
							if (!this.forceUpload) {
								for (FTPFile ftpFile : ftpFiles) {
									if (ftpFile.getName().equals(child[i].getName())) {
										if (ftpFile.getSize() != child[i].length()) {
											upload = true;
										}
										break;
									}
								}
							} else {
								upload = true;
							}

							if (upload) {
								InputStream input = new FileInputStream(child[i]);
								boolean b = ftpClient.storeFile(child[i].getName(), input);
								if (b) {
									uploadFileList.add(child[i].getPath());
								}
								input.close();
							}
						}
					}
					ftpClient.changeToParentDirectory();
					result = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * 
	 * Description : 连接 ftp
	 * 
	 * @throws Exception
	 */
	private boolean connect() throws Exception {
		boolean b = false;
		ftpClient = new FTPClient();
		ftpClient.connect(hostname, port);
		ftpClient.setSoTimeout(10000);
		ftpClient.setConnectTimeout(10000);
		ftpClient.setDefaultTimeout(10000);
		b = ftpClient.login(username, password);
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		if (!b) {
			ftpClient.disconnect();
		}
		return b;
	}

	/**
	 * 
	 * Description : 关闭连接
	 */
	private void closeConn() {
		if (ftpClient != null) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	public FTPClient getFtpClient() {
		return ftpClient;
	}

	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}

	public boolean isForceUpload() {
		return forceUpload;
	}

	public void setForceUpload(boolean forceUpload) {
		this.forceUpload = forceUpload;
	}

	public List<String> getUploadFileList() {
		return uploadFileList;
	}

	public void setUploadFileList(List<String> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}
}

//
// /**
// *
// * Description : 返回当前目录的所有文件及文件夹
// *
// * @return
// * @throws IOException
// */
// public FileInfoBean getFileList(String dirName) throws IOException {
//
// FileInfoBean root = new FileInfoBean();
// root.setDir(true);
// root.setName(dirName);
// List dir = new ArrayList();
// root.setSub(dir);
//
// BufferedReader dr = new BufferedReader(new InputStreamReader(aftp.list()));
// String s = "";
//
// while ((s = dr.readLine()) != null) {
// if (s.indexOf("<DIR>") == -1) {
// String[] spit = s.split(" ");
// FileInfoBean fInfo = new FileInfoBean();
// fInfo.setDir(false);
// fInfo.setName(spit[spit.length - 1]);
// fInfo.setSub(null);
// fInfo.setSize(Long.parseLong(spit[spit.length - 2]));
// fInfo.setDate(getTimeAfter1970(spit[0], spit[2]));
// dir.add(fInfo);
// } else {
// String[] spit = s.split(" ");
// aftp.cd(spit[spit.length - 1]);
// dir.add(getFileList(spit[spit.length - 1]));
// aftp.cdUp();
// }
// }
// return root;
// }

// /**
// *
// * Description : 上传文件到 ftp 的当前目录
// *
// * @param filepathname
// * @return
// */
// public boolean uploadFile(File file,String remote) {
// boolean result = false;
// if (!file.exists() || file.isDirectory()){
// return result;
// }
// try {
// //String filename = filepathname.substring(filepathname.lastIndexOf("/") +
// 1);
// InputStream input = new FileInputStream(file);
//
// aftp.storeFile(remote, input);
//
// input.close();
//
// RandomAccessFile sendFile = new RandomAccessFile(file, "r");
// sendFile.seek(0);
// aftp.storeFile(remote, local)
// TelnetOutputStream outs = aftp.put(file.getName());
// DataOutputStream outputs = new DataOutputStream(outs);
// while (sendFile.getFilePointer() < sendFile.length()) {
// int ch = sendFile.read();
// outputs.write(ch);
// }
// outs.close();
// sendFile.close();
// result = true;
// } catch (IOException e) {
// e.printStackTrace();
// }
// return result;
// }

// /**
// *
// * Description : 上传文件到 ftp 指定的目录
// *
// * @param filepathname
// * @param path
// * @return
// */
// public boolean uploadFile(String filepathname, String[] path) {
// boolean result = false;
// try {
// String filename = filepathname.substring(filepathname.lastIndexOf("\\") + 1);
// RandomAccessFile sendFile = new RandomAccessFile(filepathname, "r");
// sendFile.seek(0);
//
// if (path != null && (path.length > 0)) {
// for (int i = 0; i < path.length; i++) {
// aftp.cd(path[i]);
// }
// }
// TelnetOutputStream outs = aftp.put(filename);
// DataOutputStream outputs = new DataOutputStream(outs);
// while (sendFile.getFilePointer() < sendFile.length()) {
// int ch = sendFile.read();
// outputs.write(ch);
// }
// outs.close();
// sendFile.close();
// if (path != null && (path.length > 0)) {
// for (int i = 0; i < path.length; i++) {
// aftp.cdUp();
// }
// }
// result = true;
// } catch (IOException e) {
// e.printStackTrace();
// }
// return result;
// }
//
// /**
// *
// * Description : 从 ftp 当前目录下载文件到本地
// *
// * @param filename
// * @param loaclpath
// * @return
// */
// public boolean downloadFile(FileInfoBean fileinfo, String loaclPath) {
// boolean result = false;
// try {
// int ch;
// File fi = new File(loaclPath + fileinfo.getName());
// if (fi.exists() && !fi.canWrite()) {
// fi.delete();
// fi = new File(loaclPath + fileinfo.getName());
// }
// // fi.setLastModified(fileinfo.getDate());
// RandomAccessFile getFile = new RandomAccessFile(fi, "rw");
// getFile.seek(0);
// TelnetInputStream fget = aftp.get(fileinfo.getName());
// DataInputStream puts = new DataInputStream(fget);
// while ((ch = puts.read()) >= 0) {
// getFile.write(ch);
// }
// fget.close();
// getFile.close();
// fi.setLastModified(fileinfo.getDate());
// result = true;
// // System.out.println(loaclPath + filename + "            OK!!");
// } catch (IOException e) {
// e.printStackTrace();
//
// }
// return result;
// }
//
// /**
// *
// * Description : 从 ftp 指定目录下载文件到本地
// *
// * @param path
// * @param filename
// * @param loaclpath
// * @return
// */
// public boolean downloadFile(String[] path, String filename, String loaclPath)
// {
// boolean result = false;
// try {
// int ch;
// if ((path != null) && (path.length > 0)) {
// for (int i = 0; i < path.length; i++) {
// aftp.cd(path[i]);
// }
// }
// File fi = new File(loaclPath + "\\" + filename);
//
// RandomAccessFile getFile = new RandomAccessFile(fi, "rw");
// getFile.seek(0);
// TelnetInputStream fget = aftp.get(filename);
// DataInputStream puts = new DataInputStream(fget);
// while ((ch = puts.read()) >= 0) {
// getFile.write(ch);
// }
// fget.close();
// getFile.close();
// if ((path != null) && (path.length > 0)) {
// for (int i = 0; i < path.length; i++) {
// aftp.cdUp();
// }
// }
// result = true;
// } catch (IOException e) {
// e.printStackTrace();
//
// }
// return result;
// }

//
// private long getTimeAfter1970(String date,String hm) {
// Date d = null;
// try {
// SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
// d = sdf.parse(date);
// int h = Integer.parseInt(hm.substring(0,2));
// int m = Integer.parseInt(hm.substring(3,5));
// if (hm.substring(5).equals("PM")){
// h += 12;
// }
// d.setHours(h);
// d.setMinutes(m);
// } catch (Exception e) {
// d = new Date();
// }
// Calendar c = Calendar.getInstance();
// c.setTime(d);
// return c.getTimeInMillis();
// }
// }
