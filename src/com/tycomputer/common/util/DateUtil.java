package com.tycomputer.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * 日期 : 2010-2-24<br>
 * 作者 : zhangliuhua<br>
 * 项目 : googleAppTest<br>
 * 功能 : 日期工具<br>
 */
public class DateUtil {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static GregorianCalendar gc = new GregorianCalendar();

	/**
	 * 
	 * getSysDate 作用: 得到系统时间
	 * 
	 * @return java.util.Date
	 */
	public static Date getSysDate() {
		return new Date();
	}

	/**
	 * 
	 * getSysCalendar 作用: 得到系统时间
	 * 
	 * @return java.util.Calendar
	 */
	public static Calendar getSysCalendar() {
		Calendar currCalendar = Calendar.getInstance();
		return currCalendar;
	}

	/**
	 * 取得当前月的字符串形式yyyy-MM
	 */
	public static String getCurrMonth() {
		return (new SimpleDateFormat("yyyy-MM")).format(new Date());

	}

	/**
	 * 取得当前时间的字符串形式yyyy-MM-dd
	 */

	public static String getCurrDate() {
		return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}

	/**
	 * 取得当前时间的字符串形式yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrTime() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}

	/**
	 * 字符串转换成日期
	 * 
	 */
	public static Date parseDate(String s) {
		Date d = null;

		try {
			if (s.length() <= 10) {
				d = sdf.parse(s);
			} else {
				d = sdf2.parse(s);
			}

		} catch (Exception e) {
		}

		return d;
	}

	/**
	 * 字符串转换成Calendar
	 */
	public static Calendar parseCalendar(String s) {
		Calendar c = null;
		Date d = parseDate(s);

		if (d != null) {
			c = Calendar.getInstance();
			c.setTime(d);
		}
		return c;
	}

	/**
	 * 字符串转换成日期
	 */
	public static Date parseDateTime(String s) {
		Date d = null;

		try {
			d = sdf2.parse(s);

		} catch (Exception e) {
		}

		return d;
	}

	/**
	 * 返回日期格式
	 */
	public static String toString(Date d) {
		String s = null;
		try {
			s = sdf.format(d);
		} catch (Exception e) {
		}
		return s;
	}

	public static String format(Date d, String format) {
		if (d == null) {
			return null;
		}
		SimpleDateFormat sdformat = new SimpleDateFormat(format);
		return sdformat.format(d);
	}

	/*
	 * 转换日期和时间为指定格式的字符串 用例：format(new Now(), "yyyy-MM-dd") format(new Now(),
	 * "yyyy-MM") format(new Now(), "yyyy-MM-dd HH:mm:ss")
	 */
	public static String format(Calendar c, String format) {
		return format(c == null ? (Date) null : c.getTime(), format);
	}

	public static String format(Date d) {
		return format(d, "yyyy-MM-dd");
	}

	public static String format(Calendar c) {
		return format(c == null ? (Date) null : c.getTime());
	}

	public static String format(String s, String format) {
		Date d = parseDate(s);

		if (d == null) {
			return null;
		}

		return format(d, format);
	}

	public static String format(String s) {
		return format(s, "yyyy-MM-dd");
	}

	public static String getDayOfWeek(final Calendar c) {
		String str = "";
		if (c == null)
			return str;
		int day = c.get(Calendar.DAY_OF_WEEK);
		switch (day) {
		case Calendar.SUNDAY:
			str = "日";
			break;
		case Calendar.MONDAY:
			str = "一";
			break;
		case Calendar.TUESDAY:
			str = "二";
			break;
		case Calendar.WEDNESDAY:
			str = "三";
			break;
		case Calendar.THURSDAY:
			str = "四";
			break;
		case Calendar.FRIDAY:
			str = "五";
			break;
		case Calendar.SATURDAY:
			str = "六";
			break;
		default:
			str = "";
		}
		return str;
	}

	public static void main(String[] args) {
		Calendar c = DateUtil.getSysCalendar();
		System.out.println(DateUtil.format(c,"yyyy-MM-dd HH:mm:ss"));

	}

}
