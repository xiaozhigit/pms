package com.xxx.pms.util;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if ("null".equals(object)) {
            return (true);
        }
        return (false);
    }

    public static boolean isNotEmpty(Object object) {
        if (object != null && !object.equals("") && !object.equals("null")) {
            return (true);
        }
        return (false);
    }

    /**
     * 生成UUID 32位
     * @return
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }


    /***
     * 验证非法字符
     * @param stb
     * @return
     */
    public static boolean specialCharacters(String stb) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(stb);
        return m.find();
    }

    /***
     * 日期格式转换为字符串
     * "yyyy-MM-dd HH:mm:ss"
     */
    public static boolean formatDate(Date time){
        Date startDate = null;
        Date endDate = null;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String start = sdf.format(new Date()) + " 00:00:00";
            String end = sdf.format(new Date()) + " 23:59:59";
            startDate = sdfs.parse(start);
            endDate = sdfs.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
        return startDate.getTime() <= time.getTime() && endDate.getTime() >= time.getTime();
    }




    //将字符串其中的部分字符替换
    public static String stringReplace(String oldStr,String oldReplace,String newReplace){
        String newStr = oldStr.replaceAll(oldReplace, newReplace);
        return newStr;
    }


    public static void copyFile(String source, String dest) throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }

}
