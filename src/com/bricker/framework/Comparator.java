package com.bricker.framework;

public interface Comparator {
	void add(Source source);
	void remove(Source source);
	void setPolicy(ComparePolicy policy);
	void start(CoinID from, CoinID to);
	void stop();
	void setOnFindOpportunityListener(OnFindOpportunityListener listener);
}
