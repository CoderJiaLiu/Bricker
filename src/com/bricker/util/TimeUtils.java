package com.bricker.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TimeUtils {
	public static final String DateToString(long dateL) {
		SimpleDateFormat sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
		Date date = new Date(dateL);
		String dateString = sdt.format(date);
		return dateString;
	}
}
