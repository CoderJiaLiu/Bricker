package com.bricker.framework;

public class PriceInfo {
	private SourceID mSourceID;
	private double mRate = -1f;
	private long mTimeStamp = -1L;
	private CoinID mFrom;
	private CoinID mTo;
	
	public PriceInfo(SourceID sourceID,CoinID from,CoinID to,double rate,long timeStamp) {
		mFrom = from;
		mTo = to;
		mRate = rate;
		mTimeStamp = timeStamp;
		mSourceID = sourceID;
	}

	public double getRate() {
		return mRate;
	}

	public void setRate(double rate) {
		mRate = rate;
	}

	public long getTimeStamp() {
		return mTimeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		mTimeStamp = timeStamp;
	}

	public CoinID getFrom() {
		return mFrom;
	}

	public void setFrom(CoinID from) {
		mFrom = from;
	}

	public CoinID getTo() {
		return mTo;
	}

	public void setTo(CoinID to) {
		mTo = to;
	}
	
	
}
