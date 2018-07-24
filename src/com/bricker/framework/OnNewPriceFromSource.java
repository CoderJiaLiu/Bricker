package com.bricker.framework;

public interface OnNewPriceFromSource {
	void onNewPrice(Source source, boolean succeed, double price, long timeStamp);
}
