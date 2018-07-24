package com.bricker.framework;

public class PriceInfo {
	private SourceID mSourceID;
	private double mRate = -1f;
	private long mTimeStamp = -1L;
	
	public PriceInfo(SourceID sourceID,double rate,long timeStamp) {
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
	
	public SourceID getSourceID() {
		return mSourceID;
	}
}
