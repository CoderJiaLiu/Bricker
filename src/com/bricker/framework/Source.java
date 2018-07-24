package com.bricker.framework;

public interface Source {
	SourceID getID();
	String getName();
	int subscribe(final long period,final OnPriceRefreshedListenner listener);
	void requestPrice(final OnPriceRefreshedListenner listener);
	void unsubscribe(int subscribeId);
}
