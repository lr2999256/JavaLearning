package com.learn.common.utils;

import java.io.*;

/**
 * 文件工具类
 * @author Peter
 *
 */
public class FileUtils {
	/**
	 * 传入文件夹路径，该方法能够实现创建整个路径
	 * @param path 文件夹路径，不包含文件名称及后缀名
	 */
	public static void isDir(String path){
		String[] paths = path.split("/");
			String filePath = "";
			for(int i = 0 ; i < paths.length ; i++){
				if(i == 0){
					filePath = paths[0];
				}else{
					filePath += "/" + paths[i];
				}
				creatDir(filePath);
			}
	}
	
	/**
	 * 该方法用来判断文件夹是否存在，如果不存在则创建，存在则什么都不做
	 * @param filePath
	 */
	public static void creatDir(String filePath){
		File file = new File(filePath);
		if(!file.exists()){
				file.mkdir();
		}
	}

	public static void writeAppend(String content,String filePath) throws Exception{
		FileWriter fw=null;
		try{
			fw = new FileWriter(filePath,true);
			fw.write(content);
			fw.close();
		}catch (IOException e){
			throw e;
		}finally {
			try {
				if(null!=fw){
					fw.close();
				}
			}catch (IOException e2){
			}
		}
	}

	public static void main(String args[]) throws Exception{
		writeAppend("abc","E:\\chkfile\\test.txt");
		writeAppend("der\n","E:\\chkfile\\test.txt");
	}
}
