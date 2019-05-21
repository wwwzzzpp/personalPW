package com.wzp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-8-24
 */
public class TimeUtils {

	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat DATE_FORMAT_MONTH = new SimpleDateFormat(
			"yyyy-MM");
	public static final SimpleDateFormat DATE_FORMAT_DAY = new SimpleDateFormat(
			"dd");

	/**
	 * long time to string
	 * 
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */
	public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(timeInMillis));
	}

	/**
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @param timeInMillis
	 * @return
	 */
	public static String getTime(long timeInMillis) {
		return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
	}

	/**
	 * get current time in milliseconds
	 * 
	 * @return
	 */
	public static long getCurrentTimeInLong() {
		return System.currentTimeMillis();
	}

	/**
	 * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @return
	 */
	public static String getCurrentTimeInString() {
		return getTime(getCurrentTimeInLong());
	}

	/**
	 * get current time in milliseconds
	 * 
	 * @return
	 */
	public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
		return getTime(getCurrentTimeInLong(), dateFormat);
	}

	/**
	 * get Date from a date String
	 * 
	 * @return
	 */
	public static Date stringToDate(String str) {
		Date date = null;
		try {
			date = DATE_FORMAT_DATE.parse(str);

		} catch (ParseException e) {
			Log.d(e.getMessage());
		}
		return date;
	}

	/**
	 * get Date from a date String
	 * 
	 * @return
	 */
	public static String stringToDateDetail(Date date) {
		String fomartDate = null;
		try {
			fomartDate = DATE_FORMAT_DATE.format(date);

		} catch (Exception e) {
			Log.d(e.getMessage());
		}

		return fomartDate;
	}

	public static String stringToMonthDetail(Date date) {
		String fomartDate = null;
		try {
			fomartDate = DATE_FORMAT_MONTH.format(date);

		} catch (Exception e) {
			Log.d(e.getMessage());
		}

		return fomartDate;
	}

	public static String stringToDayDetail(Date date) {
		String fomartDate = null;
		try {
			fomartDate = DATE_FORMAT_DAY.format(date);

		} catch (Exception e) {
			Log.d(e.getMessage());
		}

		return fomartDate;
	}

	/**
	 * get Date from a date String
	 * 
	 * @return
	 */
	public static Date stringToDateTime(String str) {
		Date date = null;
		try {
			date = DEFAULT_DATE_FORMAT.parse(str);

		} catch (ParseException e) {
			Log.d(e.getMessage());
		}
		return date;
	}

	/**
	 * get Date from a date String yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String stringToDateDetailTime(Date date) {
		String fomartDate = null;
		try {
			fomartDate = DEFAULT_DATE_FORMAT.format(date);

		} catch (Exception e) {
			Log.d(e.getMessage());
		}

		return fomartDate;
	}
}
