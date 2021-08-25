package cn.wjqixige.app.utils;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class DateUtil {
    private static long longMax = 9999999999999L;

    /**
     * 字符串补全；判断字符串位数，不足的前面补零
     * @param str 输入字符串
     * @param num 位数
     * @return 返回补全之后的字符串
     */
    public static String autoGenericCode(String str, int num){
        if (str.length() > num)
            throw new IllegalArgumentException("Input character is invalid.");

        if (str.length() < num) {
            StringBuilder prefix = new StringBuilder();
            int len = num - str.length();
            for (int i = 0; i < len; i++) {
                prefix.append("0");
            }
            return prefix + str;
        } else {
            return str;
        }
    }


    /**
     * 返回当前时间的倒序时间戳
     * @return
     */
    public static String getCurrentDescLongTime(){
        long time = System.currentTimeMillis();
        return String.valueOf(longMax - time);
    }

    public static String getCurrentDescLongTime(long time){
        return String.valueOf(longMax - time);
    }


    public static String getDescLongTime(String time){
        String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss"};
        long longTime = 0;
        try {
            longTime = longMax - DateUtils.parseDate(time, parsePatterns).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(longTime);
    }


    /**
     * 获取当前时间的字符串格式
     * @return
     */
    //"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss"
    public static String getCurrentTimeStr(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(System.currentTimeMillis());
    }

    public static String getCurrentTimeStr(long time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return dateFormat.format(time);
    }

}
