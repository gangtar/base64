package com.gang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Base64Security {

    public static void main(String[] args) throws IOException {
	// write your code here
        String table = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        File file = new File("C:\\Users\\admin\\Desktop\\stego.txt");
        BufferedReader reader = null;
        StringBuffer s = new StringBuffer();
        StringBuffer flag = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr=reader.readLine())!=null){
                //s.append(tempStr+"\n");
                int length = tempStr.length();
                //System.out.println(tempStr.charAt(length - 1));
                if ("=".equals(String.valueOf(tempStr.charAt(length-1)))){
                    //System.out.println("daol");
                    if ("=".equals(String.valueOf(tempStr.charAt(length-2)))){
                        int i = table.indexOf(tempStr.charAt(length - 3));//找出含有信息的字符对应的序列十进制
                        //System.out.println(i);
                        String bin = Integer.toBinaryString(i);
                        int i1 = Integer.parseInt(bin);
                        String formatBin = String.format("%04d", i1);
                        //System.out.println(formatBin);
                        int length1 = formatBin.length();
                        //System.out.println(length1+"==");
                        String substring = formatBin.substring(length1 - 4, length1);
                        //System.out.println(substring);
                        s.append(substring);
                    }else {
                        int i = table.indexOf(tempStr.charAt(length - 2));//找出含有信息的字符对应的序列十进制
                        //System.out.println(i);
                        String bin = Integer.toBinaryString(i);//二进制
                        int i1 = Integer.parseInt(bin);
                        String formatBin = String.format("%04d", i1);
                        //System.out.println(formatBin);
                        int length1 = formatBin.length();
                        //System.out.println(length1+"=");
                        String substring = formatBin.substring(length1 - 2, length1);
                        //System.out.println(substring);
                        s.append(substring);
                    }
                }
            }
            reader.close();
            //System.out.println(s.toString());
            for (int i=0;i<(s.length()/8);i++){
                flag.append((char)(Integer.parseInt(s.substring(i*8,(i+1)*8),2)));
            }
            System.out.println("flag:"+flag);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader !=null){
                try {
                    reader.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }

            }
        }


    }
}
