package com.bricker.framework;

public enum CoinID {
	BTC("btc"),
	ETC("etc"),
	ETH("eth"),
	USDT("usdt");
	
	private String mName;
	private CoinID(String name) {
		mName = name;
	}
	
	public String getName() {
		return mName;
	}
}
