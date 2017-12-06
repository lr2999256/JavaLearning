package com.learn.common.md5;

import com.learn.common.rsa.Base64;
import org.mortbay.jetty.security.B64Code;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.MessageDigest;

public class Test {

    public static String readMD5File(String fileName) {
        String data = "";
        try {
            InputStreamReader fr = new InputStreamReader(new FileInputStream(fileName), "GBK");
            BufferedReader br = new BufferedReader(fr);
            String buff = "";
            while ((buff = br.readLine()) != null) {
                data = data + buff;
            }
            br.close();
            fr.close();
        } catch (Exception e) {
//            logger.error("Exception:" + e);
        }

        return data;
    }

    public static String getFileMD5(String fileName) {
        MessageDigest MD5 = null;
        BufferedInputStream bis = null;
        String result = null;
        try {
            MD5 = MessageDigest.getInstance("MD5");
            byte[] md5Result = null;
            byte[] tmpBuff = new byte[2048];

            bis = new BufferedInputStream(new FileInputStream(fileName));
            int len = 0;
            while ((len = bis.read(tmpBuff)) != -1) {
                MD5.update(tmpBuff, 0, len);
            }
            md5Result = MD5.digest();
            BASE64Encoder base64en = new BASE64Encoder();
            result = new String(base64en.encode(md5Result));
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }
        return result;
    }

    public static void write(String content,String filePath) throws Exception{
        FileWriter fw=null;
        try{
            File file=new File(filePath);

            if(file.exists()){
                if(file.isDirectory()){
                    throw new Exception(filePath+"是一个已经存在的目录，无法写入数据");
                }else{
                    file.delete();
                }
            }
            fw = new FileWriter(filePath);
            fw.write(content);
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

    public static void main(String args[]){
//        String md5FileContent = readMD5File("E:\\chkfile\\ICBC_EXPRESSPAY_CHECKRESULT_1066_20170815.MD5");
//        String chkMd5Content = getFileMD5("E:\\chkfile\\ICBC_EXPRESSPAY_CHECKRESULT_1066_20170815.TXT");
        try {
            write("","E:\\chkfile\\CPI_REFUND_20170819.dat");
        }catch (Exception e){

        }
        System.out.print("aaa");
    }
}
