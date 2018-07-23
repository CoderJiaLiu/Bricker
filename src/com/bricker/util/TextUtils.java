package com.bricker.util;

public class TextUtils {
	public static final boolean isEmpty(CharSequence s) {
		if (s == null) {
			return true;
		}

		if (s.length() == 0) {
			return true;
		}

		return false;
	}
	
	public static final boolean equals(CharSequence s1,CharSequence s2) {
		if(s1 == s2) {
			return true;
		}
		
		if(s1 == null || s2 == null) {
			return false;
		}
		
		return s1.equals(s2);
	}
}
