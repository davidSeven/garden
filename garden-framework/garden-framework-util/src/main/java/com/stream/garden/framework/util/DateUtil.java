package com.stream.garden.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author garden
 */
@SuppressWarnings("unused")
public final class DateUtil {

    private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
    private static final double DAYS_PER_YEAR = 365.25;
    private DateUtil() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

    public static long getOneDayTimeMillseconds() {
        return MILLIS_PER_DAY;
    }

    @Deprecated
    public static Date currentTime() {
        return now();
    }

    /**
     * 获取系统当前时间
     *
     * @return 系统时间
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 获取系统当前时间
     *
     * @param dateUtilEnum DateUtilEnum
     * @return 系统时间
     */
    public static String now(DateUtilEnum dateUtilEnum) {
        return getDateFormat(dateUtilEnum).format(now());
    }

    /**
     * 获取正确的DateFormat对象
     *
     * @param pattern DateUtilEnum
     * @return DateFormat
     */
    private static DateFormat getDateFormat(DateUtilEnum pattern) {
        return new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
    }

    /**
     * Date类型转换成String
     *
     * @param date    日期
     * @param pattern 格式
     * @return 日期文字描述
     */
    public static String format(Date date, DateUtilEnum pattern) {
        if (date == null) {
            return null;
        }
        DateFormat formatter = getDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * Calendar类型转换成String
     *
     * @param calendar 日历
     * @param pattern  格式
     * @return 日期文字描述
     */
    public static String format(Calendar calendar, DateUtilEnum pattern) {
        if (calendar == null) {
            return null;
        }
        return format(calendar.getTime(), pattern);
    }

    /**
     * Timestamp类型转换成String
     *
     * @param timestamp Timestamp
     * @param pattern   格式
     * @return 日期文字描述
     */
    public static String format(Timestamp timestamp, DateUtilEnum pattern) {
        if (timestamp == null) {
            return null;
        }
        return format((Date) timestamp, pattern);
    }

    /**
     * String类型转换成Date
     *
     * @param text    文本
     * @param pattern 格式
     * @return Date
     * @throws ParseException 转换异常
     */
    public static Date parseDate(String text, DateUtilEnum pattern) throws ParseException {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        DateFormat formatter = getDateFormat(pattern);
        return formatter.parse(text);
    }

    /**
     * String类型转换成Calendar
     *
     * @param text    文本
     * @param pattern 格式
     * @return Calendar
     * @throws ParseException 转换异常
     */
    public static Calendar parseCalendar(String text, DateUtilEnum pattern) throws ParseException {
        Date d = parseDate(text, pattern);
        if (d == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar;
    }

    /**
     * String类型转换成Timestamp
     *
     * @param text    文本
     * @param pattern 格式
     * @return Timestamp
     * @throws ParseException 转换异常
     */
    public static Timestamp parseTimestamp(String text, DateUtilEnum pattern) throws ParseException {
        Date d = parseDate(text, pattern);
        if (d == null) {
            return null;
        }
        return new Timestamp(d.getTime());
    }

    /**
     * 校验文本是否是日期格式
     *
     * @param text    文本
     * @param pattern 格式
     * @return 格式是否正确
     */
    public static boolean validDate(String text, DateUtilEnum pattern) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat(pattern.getValue());
        try {
            format.setLenient(false);
            format.parse(text);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 获取年月的第一天
     *
     * @param year  年
     * @param month 月
     * @return Date
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        int mon = 0;
        switch (month) {
            case 1:
                calendar.set(year, Calendar.JANUARY, 1, 0, 0, 0);
                break;
            case 2:
                calendar.set(year, Calendar.FEBRUARY, 1, 0, 0, 0);
                break;
            case 3:
                calendar.set(year, Calendar.MARCH, 1, 0, 0, 0);
                break;
            case 4:
                calendar.set(year, Calendar.APRIL, 1, 0, 0, 0);
                break;
            case 5:
                calendar.set(year, Calendar.MAY, 1, 0, 0, 0);
                break;
            case 6:
                calendar.set(year, Calendar.JUNE, 1, 0, 0, 0);
                break;
            case 7:
                calendar.set(year, Calendar.JULY, 1, 0, 0, 0);
                break;
            case 8:
                calendar.set(year, Calendar.AUGUST, 1, 0, 0, 0);
                break;
            case 9:
                calendar.set(year, Calendar.SEPTEMBER, 1, 0, 0, 0);
                break;
            case 10:
                calendar.set(year, Calendar.OCTOBER, 1, 0, 0, 0);
                break;
            case 11:
                calendar.set(year, Calendar.NOVEMBER, 1, 0, 0, 0);
                break;
            case 12:
                calendar.set(year, Calendar.DECEMBER, 1, 0, 0, 0);
                break;
            default:
                break;
        }
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取年月的最后一天
     *
     * @param year  年
     * @param month 月
     * @return Date
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     * 根据出生日期计算出年龄
     *
     * @param birthday 生日
     * @return 年龄
     */
    public static Integer calculateAgeByBirthday(Date birthday) {
        if (birthday == null) {
            return 0;
        }
        int birthdayYear = Integer.parseInt(format(birthday, DateUtilEnum.DATE_FMT_Y_M_D).split("-")[0]);
        int birthdayMonth = Integer.parseInt(format(birthday, DateUtilEnum.DATE_FMT_Y_M_D).split("-")[1]);
        int nowYear = Integer.parseInt(format(currentTime(), DateUtilEnum.DATE_FMT_Y_M_D).split("-")[0]);
        int nowMonth = Integer.parseInt(format(currentTime(), DateUtilEnum.DATE_FMT_Y_M_D).split("-")[1]);
        int age = nowYear - birthdayYear;
        if (nowMonth < birthdayMonth) {
            age--;
        }
        return age;
    }

    /**
     * 时间加减
     *
     * @param source Date
     * @param year   int
     * @param month  int
     * @param date   int
     * @param hour   int
     * @param minute int
     * @param second int
     * @return Date
     */
    public static Date dateAdd(Date source, int year, int month, int date, int hour, int minute, int second) {
        if (source == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(source);
        c.add(Calendar.YEAR, year);
        c.add(Calendar.MONTH, month);
        c.add(Calendar.DATE, date);
        c.add(Calendar.HOUR, hour);
        c.add(Calendar.MINUTE, minute);
        c.add(Calendar.SECOND, second);
        return c.getTime();
    }

    /**
     * 计算两个日期中间的天数
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 天数
     */
    public static Integer calcDays(Date start, Date end) {
        if (start == null || end == null) {
            return null;
        }
        String startTime = format(start, DateUtilEnum.DATE_FMT_H_M_S);
        String endTime = format(end, DateUtilEnum.DATE_FMT_H_M_S);
        int add = 0;
        if (endTime.compareTo(startTime) < 0) {
            add = 1;
        }
        long n = end.getTime() - start.getTime();
        long result = n / (24 * 60 * 60 * 1000) + add;
        return (int) result;
    }

    @Deprecated
    public static String getDate(Date date, String format) {
        return new DateTime(date.getTime()).toString(format);
    }

    @Deprecated
    public static String getDate(Date date) {
        return new DateTime(date.getTime()).toString(DateUtilEnum.DATE_FMT_YMD.getValue());
    }

    @Deprecated
    public static Date parseStringToDate(String dateString) {
        return DateTime.parse(dateString).toDate();
    }

    public static Date parse(String text) throws ParseException {
        if (StringUtils.isEmpty(text)) {
            return null;
        } else if (text.matches(DateUtilConstant.REG_DATE_FMT_Y)) {
            return DateUtil.parseDate(text, DateUtilEnum.DATE_FMT_Y);
        } else if (text.matches(DateUtilConstant.REG_DATE_FMT_Y_M)) {
            return DateUtil.parseDate(text, DateUtilEnum.DATE_FMT_Y_M);
        } else if (text.matches(DateUtilConstant.REG_DATE_FMT_Y_M_D)) {
            return DateUtil.parseDate(text, DateUtilEnum.DATE_FMT_Y_M_D);
        } else if (text.matches(DateUtilConstant.REG_DATE_FMT_Y_M_D_H)) {
            return DateUtil.parseDate(text, DateUtilEnum.DATE_FMT_Y_M_D_H);
        } else if (text.matches(DateUtilConstant.REG_DATE_FMT_Y_M_D_HM)) {
            return DateUtil.parseDate(text, DateUtilEnum.DATE_FMT_Y_M_D_HM);
        } else if (text.matches(DateUtilConstant.REG_DATE_FMT_Y_M_D_HMS)) {
            return DateUtil.parseDate(text, DateUtilEnum.DATE_FMT_Y_M_D_HMS);
        } else if (text.matches(DateUtilConstant.REG_DATE_FMT_Y_M_D_HMSSS)) {
            return DateUtil.parseDate(text, DateUtilEnum.DATE_FMT_Y_M_D_HMSSS);
        } else {
            throw new ParseException("Invalid Date value '" + text + "'", 0);
        }
    }

    @Deprecated
    public static int calculateMonthIn(Date from, Date to) {
        return Months.monthsBetween(new DateTime(from), new DateTime(to)).getMonths();
    }

    public static long daysBetween(Date startDate, Date endDate) {
        Calendar start = new GregorianCalendar();
        start.setTime(startDate);
        Calendar end = new GregorianCalendar();
        end.setTime(endDate);
        return daysBetween(start, end);
    }

    public static long daysBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    public static long monthsBetween(Date startDate, Date endDate) {
        Calendar start = new GregorianCalendar();
        start.setTime(startDate);
        Calendar end = new GregorianCalendar();
        end.setTime(endDate);
        return monthsBetween(start, end);
    }

    public static long monthsBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    public static long yearsBetween(Date startDate, Date endDate) {
        Calendar start = new GregorianCalendar();
        start.setTime(startDate);
        Calendar end = new GregorianCalendar();
        end.setTime(endDate);
        return yearsBetween(start, end);
    }

    public static long yearsBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long yearsBetween = 0;
        date.add(Calendar.YEAR, 1);
        while (date.before(endDate)) {
            date.add(Calendar.YEAR, 1);
            yearsBetween++;
        }
        return yearsBetween;
    }

    public static Date getFutureNormalizedDay(int numOfdays) {
        int actualNumOfDays = numOfdays - 1;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, actualNumOfDays);
        return normalizeDay(cal.getTime());
    }

    public static Date normalizeDay(Date date) {
        return setTime(date, 0, 0, 0);
    }

    public static Date normalizeMonth(Date date) {
        if (date == null) {
            return null;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return normalizeDay(cal.getTime());
    }

    public static Date normalizeYear(Date date) {
        if (date == null) {
            return null;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        return normalizeMonth(cal.getTime());
    }

    public static Date setTime(Date baseDate, int hourOfDay, int min, int sec) {
        if (baseDate == null) {
            return null;
        }
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(baseDate);
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, sec);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * Returns true if <code>targetDate</code> is in the range of [
     * <code>startDate</code>, <code>endDate</code>], otherwise false.
     *
     * @param startDate  start date.
     * @param endDate    end date.
     * @param targetDate the date to be calculated
     * @return true if <code>targetDate</code> is in the range of [
     * <code>startDate</code>, <code>endDate</code>], otherwise false.
     */
    public static boolean isBetween(Date startDate, Date endDate, Date targetDate) {
        if (startDate == null || endDate == null || targetDate == null) {
            throw new NullPointerException("Parameter date shouldn't be null");
        }
        return startDate.getTime() <= targetDate.getTime() && targetDate.getTime() <= endDate.getTime();
    }

    /**
     * Returns the count of days between <code>startDate</code> and
     * <code>endDate</code>.
     *
     * @param startDate start date
     * @param endDate   end date
     * @return the count of days between <code>startDate</code> and
     * <code>endDate</code>.
     */
    public static long diffDays(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new NullPointerException("Parameter date shouldn't be null");
        }
        return (endDate.getTime() - startDate.getTime()) / MILLIS_PER_DAY;
    }

    /**
     * return the date of <code>days</code> before now.
     *
     * @param days int
     * @return Date
     */
    public static Date daysBeforeNow(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        return calendar.getTime();
    }

    /**
     * return the date of <code>days</code> before <code>date</code>.
     *
     * @param date Date
     * @param days int
     * @return Date
     */
    public static Date dateBefore(Date date, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        return calendar.getTime();
    }

    @Deprecated
    public static String getDateTime(Date date) {
        return new DateTime(date.getTime()).toString(DateUtilEnum.DATE_FMT_YMDHMS.getValue());
    }

    @Deprecated
    public static String getDateTimeMillisecond(Date date) {
        return new DateTime(date.getTime()).toString(DateUtilEnum.DATE_FMT_YMDHMSSSSS.getValue());
    }

    @Deprecated
    public static String getHourMinuteOfTime(Date date) {
        return new DateTime(date.getTime()).toString(DateUtilEnum.DATE_FMT_HMS.getValue());
    }

    @Deprecated
    public static String getDateDetail(Date date) {
        return new DateTime(date.getTime()).toString(DateUtilEnum.DATE_FMT_Y_M_D_HMS.getValue());
    }

    @Deprecated
    public static Date addYears(Date date, int years) {
        return new DateTime(date).plusYears(years).toDate();
    }

    @Deprecated
    public static Date addMonths(Date date, int months) {
        return new DateTime(date).plusMonths(months).toDate();
    }

    @Deprecated
    public static Date addMinutes(Date date, int minutes) {
        return new DateTime(date).plusMinutes(minutes).toDate();
    }

    @Deprecated
    public static Date addHours(Date date, int hours) {
        return new DateTime(date).plusHours(hours).toDate();
    }

    @Deprecated
    public static Date addSeconds(Date date, int seconds) {
        return new DateTime(date).plusSeconds(seconds).toDate();
    }

    @Deprecated
    public static Date parseDate(String time) {
        return DateTimeFormat.forPattern(DateUtilEnum.DATE_FMT_Y_M_D.getValue()).parseDateTime(time).toLocalDate().toDate();
    }

    @Deprecated
    public static String formatDate(Date date, String format) {
        return DateTimeFormat.forPattern(format).print(new DateTime(date));
    }

    @Deprecated
    public static Date parseDate(String time, String format) {
        return DateTimeFormat.forPattern(format).parseDateTime(time).toDate();
    }

    @Deprecated
    public static Date addDays(Date date, int days) {
        return new DateTime(date).plusDays(days).toDate();
    }

    @Deprecated
    public static Date getBeginOfDate(Date date) {
        return new DateTime(date).withTime(0, 0, 0, 0).toDate();
    }

    @Deprecated
    public static Date getEndOfDate(Date date) {
        return new DateTime(date).withTime(23, 59, 59, 0).toDate();
    }

    public static Date getYearMonthDay(Date date) {
        return normalizeDay(date);
    }

    public static Date daysAfterNow(int numOfdays) {
        int actualNumOfDays = numOfdays - 1;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, actualNumOfDays);
        return getYearMonthDay(cal.getTime());
    }

    public static Date monthsAfterNow(int num) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, num);
        return getYearMonthDay(cal.getTime());
    }

    public static String getCurrentDate(String aFormat) {
        return getCurrentDate(aFormat, Calendar.getInstance().getTimeInMillis());
    }

    @Deprecated
    public static String getCurrentDate(String aFormat, long time) {
        return new DateTime(time).toString(aFormat);
    }

    @Deprecated
    public static String getCurrentDate() {
        return getCurrentDate(DateUtilEnum.DATE_FMT_YMD.getValue());
    }

    @Deprecated
    public static String getCurrentTime() {
        return getCurrentDate(DateUtilEnum.DATE_FMT_HMS.getValue());
    }

    @Deprecated
    public static String getCurrentDateAndTime() {
        return getCurrentDate(DateUtilEnum.DATE_FMT_YMDHMS.getValue());
    }

    public static List<Integer> getYears(int duration) {
        List<Integer> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        for (int i = 0; i < duration; i++) {
            list.add(year - i);
        }
        return list;
    }

    public static BigDecimal getThisYearDays() {
        return BigDecimal.valueOf(DAYS_PER_YEAR);
    }

    public static Date getYesterday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_WEEK, -1);
        return cal.getTime();
    }

    @Deprecated
    public static int getDaysBetween(Date smdate, Date bdate) {
        return Days.daysBetween(new DateTime(smdate), new DateTime(bdate)).getDays();
    }

    public static int getDayOfYear() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    public static int getDayOfMonth(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return (DateUtil.normalizeDay(date1).getTime() == DateUtil.normalizeDay(date2).getTime());
    }

    public static boolean isSameMonth(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Date d1 = DateUtil.normalizeMonth(date1);
        Date d2 = DateUtil.normalizeMonth(date2);
        if (null == d1 || null == d2) {
            return false;
        }
        return (d1.getTime() == d2.getTime());
    }

    @Deprecated
    public static Date getNowDate() {
        return new Date();
    }

    @Deprecated
    public static Date parseDateToYYYY(String time) {
        return DateTimeFormat.forPattern(DateUtilEnum.DATE_FMT_Y.getValue()).parseDateTime(time).toLocalDate().toDate();
    }

    public static boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    public static int getDayOfWeek(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date is null");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        return cal.get(Calendar.DAY_OF_WEEK);
    }

}
