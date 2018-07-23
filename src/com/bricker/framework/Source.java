package com.bricker.framework;

public interface Source {
	SourceID getID();
	String getName();
	int subscribe(final CoinID from,CoinID to,final long period,final OnPriceRefreshedListenner listener);
	void requestPrice(final CoinID from,CoinID to,final OnPriceRefreshedListenner listener);
	void unsubscribe(int subscribeId);
}
