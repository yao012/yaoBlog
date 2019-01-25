package com.blog.common.util;

import org.apache.commons.lang3.StringUtils;

public class VersionUtils {

	/**
	 * 比较两个字符串形式的版本号的大小
	 * @param v1
	 * @param v2
	 * @return -1表示前者小于后者   0表示等于   1表示大于
	 * 1.1.1	1.1.01
	 */
	public static int compare(String v1, String v2){

		if(StringUtils.isBlank(v1) || StringUtils.isBlank(v2)){
			return 0;
		}

		int i=0,j=0,x=0,y=0;
		int v1Len = v1.length(); 
		int v2Len = v2.length(); 
		char c;
		do{
			while(i<v1Len){
				//计算出v1中的点之前的数字
				c = v1.charAt(i++);
				if(c>='0' && c<='9'){
					x = x*10 + (c-'0');
				}else if(c=='.'){
					break;//结束
				}else{
					//无效的字符
				}
			}
			while(j<v2Len){
				//计算出v2中的点之前的数字
				c = v2.charAt(j++);
				if(c>='0' && c<='9'){
					y = y*10 + (c-'0');
				}else if(c == '.'){
					break;//结束
				}else{
					//无效的字符
				}
			}
			if(x<y){
				return -1;
			}else if(x>y){
				return 1;
			}else{
				x=0; y=0;
				continue;
			}
		}while((i<v1Len) || (j<v2Len));
		return 0;
	}

	/**
	 *  测试当前版本是否需要升级
	 * @param currentVersion
	 * @param lastVersion
	 * @param lastVnum
	 */
	public static boolean needUpgrade(String currentVersion,String lastVersion,int lastVnum){
		boolean needUpgrade = true;
		if(currentVersion != null){
			if(currentVersion.contains(".")){
				if(compare(currentVersion, lastVersion)>=0){
					//客户端的版本已经是最新的了
					needUpgrade = false;
				}
			}else{//客户端使用的是整数形式的版本号
				int v = Integer.parseInt(currentVersion);
				if(v>=lastVnum){
					//客户端的版本已经是最新的了
					needUpgrade = false;
				}
			}
		}

		return needUpgrade;
	}

	/**
	 *
	 * @param vnum
	 * @param lastVnum
	 * @param vtext
	 * @param lastVtext
	 * @return true 表示需要升级 ,false 不需要升级
	 */
	public static boolean checkupByVnumOrVtext(int vnum, int lastVnum, String vtext, String lastVtext) {
		if (vtext == null) {
			if (vnum < lastVnum) {
				return true;
			}
		} else {
			if (compare(vtext, lastVtext) == -1) {
				return true;
			}
		}
		return false;
	}


}
