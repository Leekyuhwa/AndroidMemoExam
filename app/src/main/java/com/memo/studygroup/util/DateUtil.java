package com.memo.studygroup.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	private static final String DATE_FORMAT = "yyyy.MM.dd";

	public static String getToday() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.KOREA);
		Date currentTime = new Date();
		return mSimpleDateFormat.format(currentTime);
	}
}
