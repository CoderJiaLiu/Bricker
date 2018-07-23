package com.bricker.framework;

public interface OnPriceRefreshedListenner {
	void onPriceRefreshed(String sourceName, boolean succeed, CoinID from, CoinID to, double rate, long timeStamp);
}
