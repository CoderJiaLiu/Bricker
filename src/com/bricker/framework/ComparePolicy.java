package com.bricker.framework;

import java.util.List;

public interface ComparePolicy {
	String getDescription();
	int compare(PriceInfo infoA,PriceInfo infoB);
	boolean compare(List<PriceInfo> prices,OnCompareResultListener listener);
}
