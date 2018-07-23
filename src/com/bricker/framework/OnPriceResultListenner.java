package com.bricker.framework;

public interface OnPriceResultListenner {
	void onPriceResult(boolean succeed,CoinID from,CoinID to,double rate,long timeStamp);
}
