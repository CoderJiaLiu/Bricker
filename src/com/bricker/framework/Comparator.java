package com.bricker.framework;

public interface Comparator {
	void add(Source source);
	void remove(Source source);
	void setPolicy(ComparePolicy policy);
	void start();
	void stop();
	void setOnFindOpportunityListener(OnFindOpportunityListener listener);
	void setInterval(long interval);
}
