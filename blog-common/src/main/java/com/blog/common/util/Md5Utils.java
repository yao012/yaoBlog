package com.blog.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Administrator
 */
public class Md5Utils {

	private final static Logger logger = LogManager.getLogger(Md5Utils.class);

	public static String md5(File file){
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			
			byte[] bytes = new byte[1024];
			int readLen;
			FileInputStream in = new FileInputStream(file);
			try{
			while((readLen = in.read(bytes)) > 0){
				digest.update(bytes, 0, readLen);
			}
			}catch(Exception ex){
				
			}finally{
				in.close();
			}
			return HexUtils.hexString(digest.digest());
		} catch (Exception e) {
        	logger.error(e.getMessage(), e);
		}
		return "";		
	}
	

	public static String md5(String text){
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(text.getBytes(StandardCharsets.UTF_8));
			return HexUtils.hexString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}


	public static String md5(InputStream inputStream){

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");

			byte[] bytes = new byte[1024];
			int readLen;
			try{
				while((readLen = inputStream.read(bytes)) > 0){
					digest.update(bytes, 0, readLen);
				}
			}catch(Exception ex){

			}finally{
				inputStream.close();
			}
			return HexUtils.hexString(digest.digest());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}


	/** 获取在线文件的md5值 */
	public static String UrlFileMd5(String fileUrl){
		String md5 = "";
		try {
			URL url = new URL(fileUrl);
			URLConnection conn = url.openConnection();
			md5 =  md5(conn.getInputStream());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return md5;
	}

	public static void main(String[] args) {
		System.out.println(
		UrlFileMd5("https://eques.oss-cn-hangzhou.aliyuncs.com/Theme/android/equesThemeSkin201862101.zip")
		);
	}


}
