package com.bricker.util.log;

public enum LogCategory {
	CAMPARE(0x1),
	FRAMEWORK(0xFF),
	NET(0x100);

	private int mValue;

	private LogCategory(int value) {
		mValue = value;
	}
	
	public int getValue() {
		return mValue;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String string = super.toString() + ":" + mValue;
		return string;
	}
}
