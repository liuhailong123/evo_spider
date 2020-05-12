package com.frameworks.utils;

import org.apache.poi.ss.usermodel.Cell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作辅助类
 *
 * @author ShenHuaJie
 * @version $Id: DateUtil.java, v 0.1 2014年3月28日 上午8:58:11 ShenHuaJie Exp $
 */
public final class DateUtil {

    private DateUtil() {
    }

    public static boolean isCellDateFormatted(Cell dateCell) {
        return org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(dateCell);
    }

    /**
     * 日期格式
     **/
    public interface DATE_PATTERN {
        String HHMMSS = "HHmmss";
        String HH_MM_SS = "HH:mm:ss";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    }

    public static final String getDateBYyyyyMMdd(){
        return format(new Date(), DATE_PATTERN.YYYYMMDD);
    }

    public static void main(String[] args) {
        String str = "2020-03-05 11:28:13";
        String format = format(str, DATE_PATTERN.YYYYMMDDHHMMSS);
        System.out.println(format);
    }

    /**
     * 时间格式转换时间戳
     * @param time 当前时间
     * @param pattern 当前时间格式
     */
    public static Long parse(String time, String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date parse = simpleDateFormat.parse(time);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static final String format(Object date) {
        return format(date, DATE_PATTERN.YYYY_MM_DD);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static final String format(Object date, String pattern) {
        if (date == null) {
            return null;
        }
        if (pattern == null) {
            return format(date);
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取日期
     *
     * @return
     */
    public static final String getDate() {
        return format(new Date());
    }

    /**
     * 获取日期时间
     *
     * @return
     */
    public static final String getDateTime() {
        return format(new Date(), DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取日期
     *
     * @param pattern
     * @return
     */
    public static final String getDateTime(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 日期计算
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static final Date addDate(Date date, int field, int amount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 日期减法
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static final Date subDate(Date date, int field, int amount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, (-1) * amount);
        return calendar.getTime();
    }

    /**
     * 字符串转换为日期:不支持yyM[M]d[d]格式
     *
     * @param date
     * @return
     */
    public static final Date stringToDate(String date) {
        if (date == null) {
            return null;
        }
        String separator = String.valueOf(date.charAt(4));
        String pattern = "yyyyMMdd";
        if (!separator.matches("\\d*")) {
            pattern = "yyyy" + separator + "MM" + separator + "dd";
            if (date.length() < 10) {
                pattern = "yyyy" + separator + "M" + separator + "d";
            }
        } else if (date.length() < 8) {
            pattern = "yyyyMd";
        }
        pattern += " HH:mm:ss.SSS";
        pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 间隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final Integer getDayBetween(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        long n = end.getTimeInMillis() - start.getTimeInMillis();
        return (int) (n / (60 * 60 * 24 * 1000L));
    }

    /**
     * 间隔月
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final Integer getMonthBetween(Date startDate, Date endDate) {
        if (startDate == null || endDate == null || !startDate.before(endDate)) {
            return null;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int year1 = start.get(Calendar.YEAR);
        int year2 = end.get(Calendar.YEAR);
        int month1 = start.get(Calendar.MONTH);
        int month2 = end.get(Calendar.MONTH);
        int n = (year2 - year1) * 12;
        n = n + month2 - month1;
        return n;
    }

    /**
     * 间隔月，多一天就多算一个月
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final Integer getMonthBetweenWithDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null || !startDate.before(endDate)) {
            return null;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int year1 = start.get(Calendar.YEAR);
        int year2 = end.get(Calendar.YEAR);
        int month1 = start.get(Calendar.MONTH);
        int month2 = end.get(Calendar.MONTH);
        int n = (year2 - year1) * 12;
        n = n + month2 - month1;
        int day1 = start.get(Calendar.DAY_OF_MONTH);
        int day2 = end.get(Calendar.DAY_OF_MONTH);
        if (day1 <= day2) {
            n++;
        }
        return n;
    }


    /**
     * 某时间与当前时间进行比较
     *
     * @param time 时间 yyyy-MM-dd HH:mm:ss
     * @return 当前时间 >= time  返回true；当前时间 < time 返回false
     */
    public static boolean compareTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        Long t2 = date.getTime();

        Long t1 = System.currentTimeMillis();
        if (t1 >= t2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：得到当前月份月初 格式为：xxxx-yy-zz (eg: 2007-12-01)<br>
     *
     * @return String
     * @author pure
     */
    public static String thisMonth() {
        String strY = null;
        int x = Calendar.getInstance().get(Calendar.YEAR);
        int y = Calendar.getInstance().get(Calendar.MONTH) + 1;
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-01";
    }

    /**
     * 功能：得到当前月份月底 格式为：xxxx-yy-zz (eg: 2007-12-31)<br>
     *
     * @return String
     * @author pure
     */
    public static String thisMonthEnd() {
        String strY = null;
        String strZ = null;
        boolean leap = false;
        int x = Calendar.getInstance().get(Calendar.YEAR);
        int y = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
            strZ = "31";
        }
        if (y == 4 || y == 6 || y == 9 || y == 11) {
            strZ = "30";
        }
        if (y == 2) {
            leap = leapYear(x);
            if (leap) {
                strZ = "29";
            } else {
                strZ = "28";
            }
        }
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-" + strZ;
    }

    /**
     * 功能：得到当前季度季初 格式为：xxxx-yy-zz (eg: 2007-10-01)<br>
     *
     * @return String
     * @author pure
     */
    public static String thisSeason() {
        String dateString = "";
        int x = Calendar.getInstance().get(Calendar.YEAR);
        int y = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if (y >= 1 && y <= 3) {
            dateString = x + "-" + "01" + "-" + "01";
        }
        if (y >= 4 && y <= 6) {
            dateString = x + "-" + "04" + "-" + "01";
        }
        if (y >= 7 && y <= 9) {
            dateString = x + "-" + "07" + "-" + "01";
        }
        if (y >= 10 && y <= 12) {
            dateString = x + "-" + "10" + "-" + "01";
        }
        return dateString;
    }

    /**
     * 功能：得到当前季度季末 格式为：xxxx-yy-zz (eg: 2007-12-31)<br>
     *
     * @return String
     * @author pure
     */
    public static String thisSeasonEnd() {
        String dateString = "";
        int x = Calendar.getInstance().get(Calendar.YEAR);
        int y = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if (y >= 1 && y <= 3) {
            dateString = x + "-" + "03" + "-" + "31";
        }
        if (y >= 4 && y <= 6) {
            dateString = x + "-" + "06" + "-" + "30";
        }
        if (y >= 7 && y <= 9) {
            dateString = x + "-" + "09" + "-" + "30";
        }
        if (y >= 10 && y <= 12) {
            dateString = x + "-" + "12" + "-" + "31";
        }
        return dateString;
    }

    /**
     * 功能：得到当前年份年初 格式为：xxxx-yy-zz (eg: 2007-01-01)<br>
     *
     * @return String
     * @author pure
     */
    public static String thisYear() {
        int x = Calendar.getInstance().get(Calendar.YEAR);
        return x + "-01" + "-01";
    }

    /**
     * 功能：得到当前年份年初 格式为：yyyyMMddHHmmss (eg: 2007-01-01)<br>
     *
     * @return String
     * @author pure
     */
    public static String thisDate() {
        int x = Calendar.getInstance().get(Calendar.YEAR);
        return x + "01" + "01000000";
    }

    /**
     * 当前年份年初加3年有效期
     * @return
     */
    public static String thisDateThreeEnd(){
        int x = Calendar.getInstance().get(Calendar.YEAR);
        x += 3;
        return x + "12" + "31235959";
    }

    /**
     * 当前年份年初加3年有效期
     * @return
     */
    public static String thisYearThreeEnd(){
        int x = Calendar.getInstance().get(Calendar.YEAR);
        x += 3;
        return x + "-12" + "-31";
    }

    /**
     * 功能：得到当前年份年底 格式为：xxxx-yy-zz (eg: 2007-12-31)<br>
     *
     * @return String
     * @author pure
     */
    public static String thisYearEnd() {
        int x = Calendar.getInstance().get(Calendar.YEAR);
        return x + "-12" + "-31";
    }

    /**
     * 功能：判断输入年份是否为闰年<br>
     *
     * @param year
     * @return 是：true  否：false
     * @author pure
     */
    public static boolean leapYear(int year) {
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    leap = true;
                } else {
                    leap = false;
                }
            } else {
                leap = true;
            }
        } else {
            leap = false;
        }
        return leap;
    }

    public static String parse(String date){
        String dest = "";
        date = date.replace('-', '/');

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MMM/yy", java.util.Locale.US);
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int year, month, day;
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);

        dest += year;
        if (month < 10)
            dest += "0" + month;
        else
            dest += month;
        if (day < 10)
            dest += "0" + day;
        else
            dest += day;

        return dest;
    }
}
