package com.bricker.util.log;

public class Tag {
	private String mName;
	private LogCategory mCategory;

	public Tag(String name, LogCategory category) {
		mName = name;
		mCategory = category;
	}

	public String getName() {
		return mName;
	}

	public LogCategory getCategory() {
		return mCategory;
	}
}
