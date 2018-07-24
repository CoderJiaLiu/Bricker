package com.bricker.framework;

public interface Source {
	SourceID getID();
	String getName();
	int subscribe(final long period,final OnNewPriceFromSource listener);
	void requestPrice(final OnNewPriceFromSource listener);
	void unsubscribe(int subscribeId);
}
