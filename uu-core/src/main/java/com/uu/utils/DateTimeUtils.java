package com.uu.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 功能描述：LocalDate and LocalDateTime的时间处理
 *
 * @author crazySea 960236576@qq.com
 * @date: 2020-06-30 0030 10:44
 * @return
 */
public class DateTimeUtils {

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM)
     */
    public final static String DATE_PATTERN_YEAR_MONTH = "yyyy-MM";
    /**
     * 时间格式(HH:mm)
     */
    public final static String DATE_TIME_PATTERN_HOUR_MIN = "HH:mm";
    /**
     * 时间格式(HH:mm:ss)
     */
    public final static String TIME_PATTERN = "HH:mm:ss";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式(yyyy-MM-dd HH:mm)
     */
    public final static String DATE_TIME_PATTERN_MINUTE = "yyyy-MM-dd HH:mm";
    /**
     * 时间格式短地址(yyyyMMddHHmmss)
     */
    public final static String DATE_TIME_PATTERN_SHORT = "yyyyMMddHHmmss";


    /**
     * 获得当前时间的yyyy-MM-dd格式字符串
     *
     * @return String
     */
    public static String getCurrentDate() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String nowDate = today.format(df);
        return nowDate;
    }

    /**
     * LocalDate转化为指定格式字符串
     *
     * @param fromDate
     * @param dateFormat
     * @return
     */
    public static String getLocalDate(LocalDate fromDate, String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        if (fromDate != null) {
            String dateStr = fromDate.format(df);
            return dateStr;
        }
        return null;

    }

    public static String getLocalDateTime(LocalDateTime fromDateTime, String dateTimeFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateTimeFormat);
        if (fromDateTime != null) {
            String localTime = fromDateTime.format(df);
            return localTime;
        }
        return null;

    }

    public static String getLocalDateTime(LocalDateTime fromDateTime) {

        return getLocalDateTime(fromDateTime, DATE_TIME_PATTERN);

    }

    /**
     * 功能描述：获得n年后的日期格式字符串
     *
     * @param beginDate  开始时间
     * @param year       n年后
     * @param dateFormat 时间格式
     * @return
     * @author crazySea 960236576@qq.com
     * @Date: 2020-06-30 0030 9:34
     */
    public static String getYearLaterDate(String beginDate, Integer year, String dateFormat) {
        LocalDate fromDate = fromString2LocalDate(beginDate, dateFormat);
        if (fromDate != null) {
            LocalDate toDate = fromDate.plus(year, ChronoUnit.YEARS);
            return getLocalDate(toDate, dateFormat);
        }
        return null;

    }

    /**
     * 功能描述：获得n个月后的日期格式字符串
     *
     * @param beginDate  开始时间
     * @param month      几个月
     * @param dateFormat 时间格式
     * @return
     * @author crazySea 960236576@qq.com
     * @Date: 2020-06-30 0030 9:40
     */
    public static String getMonthLaterDate(String beginDate, Integer month, String dateFormat) {
        LocalDate fromDate = fromString2LocalDate(beginDate, dateFormat);
        if (fromDate != null) {
            LocalDate toDate = fromDate.plus(month, ChronoUnit.MONTHS);
            return getLocalDate(toDate, dateFormat);
        }
        return null;

    }

    /**
     * 功能描述：获得n天后的日期格式字符串
     *
     * @param beginDate  开始时间
     * @param day        几天
     * @param dateFormat 时间格式
     * @return
     * @author crazySea 960236576@qq.com
     * @Date: 2020-06-30 0030 9:40
     */
    public static String getDayLaterDate(String beginDate, Integer day, String dateFormat) {
        LocalDate fromDate = fromString2LocalDate(beginDate, dateFormat);
        if (fromDate != null) {
            LocalDate toDate = fromDate.plus(day, ChronoUnit.DAYS);
            return getLocalDate(toDate, dateFormat);
        }
        return null;
    }

    /**
     * 功能描述：获得n小时后的日期格式字符串
     *
     * @param beginDate  开始时间
     * @param hour       几小时
     * @param dateFormat 时间格式
     * @return
     * @author crazySea 960236576@qq.com
     * @Date: 2020-06-30 0030 9:40
     */
    public static String getHourLaterDate(String beginDate, Integer hour, String dateFormat) {
        LocalDate fromDate = fromString2LocalDate(beginDate, dateFormat);
        if (fromDate != null) {
            LocalDate toDate = fromDate.plus(hour, ChronoUnit.HOURS);
            return getLocalDate(toDate, dateFormat);
        }
        return null;
    }

    /**
     * 功能描述：获得n分钟后的日期格式字符串
     *
     * @param beginDate 开始时间
     * @param minute    几分钟
     * @return
     * @author crazySea 960236576@qq.com
     * @Date: 2020-06-30 0030 9:40
     */
    public static LocalDateTime getMinuteLaterDateTime(LocalDateTime beginDate, Integer minute) {
        if (beginDate != null) {
            return beginDate.plus(minute, ChronoUnit.MINUTES);
        }
        return null;
    }

    /**
     * 功能描述：获得n秒的日期格式字符串
     *
     * @param beginDate  开始时间
     * @param second     几秒
     * @param dateFormat 时间格式
     * @return
     * @author crazySea 960236576@qq.com
     * @Date: 2020-06-30 0030 9:40
     */
    public static String getSecondLaterDate(String beginDate, Integer second, String dateFormat) {
        LocalDate fromDate = fromString2LocalDate(beginDate, dateFormat);
        if (fromDate != null) {
            LocalDate toDate = fromDate.plus(second, ChronoUnit.SECONDS);
            return getLocalDate(toDate, dateFormat);
        }
        return null;
    }

    /**
     * 时间格式字符串转化为指定格式的时间
     *
     * @param stringDate
     * @param dateFormat
     * @return
     */
    public static LocalDate fromString2LocalDate(String stringDate, String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate fromDate = LocalDate.parse(stringDate, df);
            return fromDate;
        } catch (Exception e) {
            return null;
        }

    }

    public static LocalDate fromString2LocalDate(String stringDate) {
        return fromString2LocalDate(stringDate, DATE_PATTERN);

    }

    /**
     * 时间格式字符串转化为指定格式的时间
     *
     * @param dateFormat
     * @return
     */
    public static LocalDateTime fromString2LocalDateTime(String stringDateTime, String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(stringDateTime, df);
            return fromDateTime;
        } catch (Exception e) {
            return null;
        }

    }

    public static LocalDateTime fromString2LocalDateTime(String stringDateTime) {
        return fromString2LocalDateTime(stringDateTime, DATE_TIME_PATTERN);

    }

    /**
     * 获得毫秒数
     *
     * @param localDateTime
     * @return
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }


    /**
     * 根据出生日期(yyyy-MM-dd)字符串计算年龄
     *
     * @param birthDay
     * @return
     */
    public static Integer getAgeByBirthDay(String birthDay) {
        LocalDate birthDate = fromString2LocalDate(birthDay, DATE_PATTERN);
        LocalDate currentDate = LocalDate.now();
        if (birthDate != null) {
            //判断birthDay是否合法
            if (currentDate.isBefore(birthDate)) {
                return 0;
            } else {
                int age = birthDate.until(currentDate).getYears();
                return age;
            }

        } else {
            return null;
        }

    }

    /**
     * Long类型时间戳转化为LocalDateTime
     *
     * @param dateTimeLong
     * @return
     */
    public static LocalDateTime fromLong2LocalDateTime(Long dateTimeLong) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateTimeLong), ZoneId.systemDefault());
        return dateTime;
    }

    /**
     * 获取本月第一天
     *
     * @return
     * @author wangjk
     * @date 2019年6月12日
     */
    public static LocalDate getFirstDayOfCurrentMonth() {
        LocalDate currentDay = LocalDate.now();
        return currentDay.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取本月最后一天
     *
     * @return
     * @author wangjk
     * @date 2019年6月12日
     */
    public static LocalDate getLastDayOfCurrentMonth() {
        LocalDate currentDay = LocalDate.now();
        return currentDay.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取当天开始时间 2019-06-12 00:00:00
     *
     * @return
     * @author wangjk
     * @date 2019年6月12日
     */
    public static LocalDateTime getTodayBeginTime() {
        LocalDate currentDay = LocalDate.now();
        return LocalDateTime.of(currentDay, LocalTime.MIN);
    }

    /**
     * 获取当天结束时间 2019-06-12 23:59:59
     *
     * @return
     * @author wangjk
     * @date 2019年6月12日
     */
    public static LocalDateTime getTodayEndTime() {
        LocalDate currentDay = LocalDate.now();
        return LocalDateTime.of(currentDay, LocalTime.MAX);
    }

    /**
     * 获取本周开始时间 2019-06-10 00:00:00
     *
     * @return
     * @author wangjk
     * @date 2019年6月12日
     */
    public static LocalDateTime getWeekBeginTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        return currentDateTime.minusDays(currentOrdinal)
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 获取本周开始时间 2019-06-10 00:00:00
     *
     * @return
     * @author wangjk
     * @date 2019年6月12日
     */
    public static String getWeekBeginTimeString() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        LocalDateTime weekBeginDateTime = currentDateTime.minusDays(currentOrdinal)
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        return getLocalDateTime(weekBeginDateTime, DATE_TIME_PATTERN);
    }

    /**
     * 获取本周结束时间 2019-06-16 23:59:59
     *
     * @return
     * @author wangjk
     * @date 2019年6月12日
     */
    public static LocalDateTime getWeekEndTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        return currentDateTime.plusDays(6 - currentOrdinal)
                .withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    /**
     * 获取本周结束时间字符串 2019-06-16 23:59:59
     *
     * @return
     * @author wangjk
     * @date 2019年6月12日
     */
    public static String getWeekEndTimeString() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentOrdinal = currentDateTime.getDayOfWeek().ordinal();
        LocalDateTime weekEndDateTime = currentDateTime.plusDays(6 - currentOrdinal)
                .withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        return getLocalDateTime(weekEndDateTime, DATE_TIME_PATTERN);
    }

    /**
     * 功能描述：获取两个时间的差多少天
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     * @author crazySea 960236576@qq.com
     * @Date: 2020-06-30 0030 10:38
     */
    public static long daysCompare(LocalDateTime startDate, LocalDateTime endDate) {
        Duration duration = Duration.between(startDate, endDate);
        return duration.toDays();
    }

    /**
     * 功能描述：获取两个时间的差多少小时
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     * @author crazySea 960236576@qq.com
     * @Date: 2020-06-30 0030 10:38
     */
    private static long hoursCompare(LocalDateTime startDate, LocalDateTime endDate) {
        Duration duration = Duration.between(startDate, endDate);
        return duration.toHours();
    }

    /**
     * 功能描述：获取两个时间的差多少分钟
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     * @author crazySea 960236576@qq.com
     * @Date: 2020-06-30 0030 10:38
     */
    public static long minutesCompare(LocalDateTime startDate, LocalDateTime endDate) {
        Duration duration = Duration.between(startDate, endDate);
        return duration.toMinutes();
    }

    /**
     * 功能描述：获取两个时间的差多少秒
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     * @author crazySea 960236576@qq.com
     * @Date: 2020-06-30 0030 10:38
     */
    private static long secondsCompare(LocalDateTime startDate, LocalDateTime endDate) {
        Duration duration = Duration.between(startDate, endDate);
        return duration.getSeconds();
    }

    /**
     * 功能描述：获取两个时间的差多少毫秒
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     * @author crazySea 960236576@qq.com
     * @Date: 2020-06-30 0030 10:38
     */
    private static long millisCompare(LocalDateTime startDate, LocalDateTime endDate) {
        Duration duration = Duration.between(startDate, endDate);
        return duration.toMillis();
    }

    /**
     * Date转LocalDateTime
     *
     * @param date 转换的date
     * @return 转换后的LocalDateTime
     * @author zhoumuzhou
     * @Date :
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Date转LocalDate
     *
     * @param date 转换的date
     * @return 转换后的LocalDateTime
     * @author zhoumuzhou
     * @Date :
     */
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 判断日期是否在范围内，包括边界
     *
     * @param date      传入日期
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     * @author zhoumuzhou
     * @date 2020-07-06 16:46:30
     **/
    public static boolean isItInTheDateRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return isItInTheDateRange(date, startDate, endDate, Boolean.TRUE);
    }

    /**
     * 判断日期是否在范围内
     *
     * @param date      传入日期
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param border    是否包括边界
     * @return
     * @author zhoumuzhou
     * @date 2020-07-06 16:46:30
     **/
    public static boolean isItInTheDateRange(LocalDate date, LocalDate startDate, LocalDate endDate, boolean border) {
        boolean flag = Boolean.FALSE;
        if (border) {
            flag = (date.isAfter(startDate) || date.isEqual(startDate)) && (date.isBefore(endDate) || date.isEqual(endDate));
        } else {
            flag = (date.isAfter(startDate)) && (date.isBefore(endDate));
        }
        return flag;
    }

    /**
     * 判断日期是大于等于指定日期
     *
     * @param chooseDateTime 传入时间
     * @param dateTime       比较的时间
     * @return
     * @author zhoumuzhou
     * @date 2020-07-06 16:46:30
     **/
    public static boolean isAfterDateTime(LocalDateTime chooseDateTime, LocalDateTime dateTime) {
        return chooseDateTime.isAfter(dateTime) || chooseDateTime.isEqual(dateTime);
    }

    /**
     * 判断日期是大于等于指定日期
     *
     * @param chooseDate 传入日期
     * @param date       比较的日期
     * @return
     * @author zhoumuzhou
     * @date 2020-07-06 16:46:30
     **/
    public static boolean isAfterDate(LocalDate chooseDate, LocalDate date) {
        return chooseDate.isAfter(date) || chooseDate.isEqual(date);
    }


    /**
     * 判断日期是小于等于指定日期
     *
     * @param chooseDate 传入日期
     * @param date       比较的日期
     * @return
     * @author zhoumuzhou
     * @date 2020-07-06 16:46:30
     **/
    public static boolean isBeforeDate(LocalDate chooseDate, LocalDate date) {
        boolean flag = chooseDate.isBefore(date) || chooseDate.isEqual(date);
        return flag;
    }

    /**
     * 判断传入时间小于等于比较的时间
     *
     * @param chooseDateTime 传入时间
     * @param dateTime       比较的时间
     * @return
     * @author zhoumuzhou
     * @date 2020-07-06 16:46:30
     **/
    public static boolean isBeforeDateTime(LocalDateTime chooseDateTime, LocalDateTime dateTime) {
        return chooseDateTime.isBefore(dateTime) || chooseDateTime.isEqual(dateTime);
    }

    /**
     * 判断时间是否在范围内，包括边界
     *
     * @param time      传入日期
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     * @author zhoumuzhou
     * @date 2020-07-06 16:46:30
     **/
    public static boolean isItInTheTimeRange(LocalTime time, LocalTime startTime, LocalTime endTime) {
        return isItInTheTimeRange(time, startTime, endTime, Boolean.TRUE);
    }

    /**
     * 判断时间是否在范围内
     *
     * @param time      传入时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param border    是否包括边界
     * @return
     * @author zhoumuzhou
     * @date 2020-07-06 16:46:30
     **/
    public static boolean isItInTheTimeRange(LocalTime time, LocalTime startTime, LocalTime endTime, boolean border) {
        boolean flag = Boolean.FALSE;
        if (border) {
            flag = (time.isAfter(startTime) || time.equals(startTime)) && (time.isBefore(endTime) || time.equals(endTime));
        } else {
            flag = (time.isAfter(startTime)) && (time.isBefore(endTime));
        }
        return flag;
    }


    public static void main(String[] args) {
//        LocalTime e = LocalTime.now();
//        System.out.println(e.getHour());
//        System.out.println(e.getMinute());
//        int day = Math.toIntExact(ChronoUnit.DAYS.between( LocalDateTime.now().plusDays(-1).withHour(12),LocalDateTime.now()));
//        String date = "2020/8/9 11:50:43";
//        String expireTime = LocalDateTime.now().plusMinutes(10).format(DateTimeFormatter.ofPattern(DateTimeUtils.DATE_TIME_PATTERN_SHORT));

        LocalDateTime cur = LocalDateTime.now().withHour(12).withMinute(40).withSecond(10);
        LocalDateTime curNext = LocalDateTime.now();
        System.out.println(getDuringTimes(cur, curNext));
    }

    public static String getDayAndMonth(LocalDateTime localDateTime) {
        return localDateTime.getMonthValue() + "月" + localDateTime.getDayOfMonth() + "日";
    }

    /**
    * @Description: 计算相差天数
    * @param endTime
    * @param startTime
    * @Author: liang_qing
    * @Email: clarence_liang@163.com
    * @Date: 2021/4/8 上午9:52
    * @Return long
    **/
    public static long getDuringDays(LocalDateTime endTime, LocalDateTime startTime) {
        return endTime.toLocalDate().toEpochDay() - startTime.toLocalDate().toEpochDay();
    }

    public static boolean isInRange(LocalDateTime checkTime, LocalDateTime startTime, LocalDateTime endTime,boolean border) {
        if(border){
            return isBeforeDateTime(checkTime,endTime) && isAfterDateTime(checkTime,startTime);
        }else {
            return checkTime.isBefore(endTime) && checkTime.isAfter(startTime);
        }
    }

    public static String getDuringTimes(LocalDateTime startTime, LocalDateTime endTime) {

        //获取相差小时数
        long hours = ChronoUnit.HOURS.between(startTime,endTime);

        //获取相差分钟数
        startTime = startTime.plusHours(hours);
        long minutes = ChronoUnit.MINUTES.between(startTime,endTime);

        //获取剩余时间的相差秒数
        startTime = startTime.plusMinutes(minutes);
        long seconds = ChronoUnit.SECONDS.between(startTime,endTime);

        StringBuilder duringTime = new StringBuilder();
        if(hours > 0){
            duringTime.append(hours).append("小时");
        }
        if(minutes > 0){
            duringTime.append(minutes).append("分钟");
        }
        duringTime.append(seconds).append("秒");

        return duringTime.toString();
    }
}
