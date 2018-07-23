package com.bricker.framework;

public interface ComparePolicy {
	int compare(PriceInfo infoA,PriceInfo infoB);
	String getDescription();
}
