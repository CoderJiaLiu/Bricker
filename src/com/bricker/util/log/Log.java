package com.bricker.util.log;

public class Log {
	private static int sOpenCategories;
	private static LogLevel sLevel;

	public static void config(boolean open, LogLevel level, LogCategory... categorys) {
		for (LogCategory category : categorys) {
			int value = category.getValue();
			if (open) {
				sOpenCategories |= value;
			} else {
				sOpenCategories &= ~value;
			}
		}
		sLevel = level;
	}

	public static void v(Tag tag, String message) {
		log(tag, message, LogLevel.v);
	}

	public static void d(Tag tag, String message) {
		log(tag, message, LogLevel.d);
	}

	public static void i(Tag tag, String message) {
		log(tag, message, LogLevel.i);
	}

	public static void w(Tag tag, String message) {
		log(tag, message, LogLevel.w);
	}

	public static void e(Tag tag, String message) {
		log(tag, message, LogLevel.e);
	}
	
	private static void log(Tag tag, String message,LogLevel level) {
		if(level.ordinal() >= sLevel.ordinal() && tag != null && ((tag.getCategory().getValue() & sOpenCategories) != 0)) {
			System.out.println(tag.getName() + " : " + message);
		}
	}
}
