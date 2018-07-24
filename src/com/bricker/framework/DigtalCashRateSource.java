package com.bricker.framework;

public abstract class DigtalCashRateSource implements Source{
	private CoinID mFrom;
	private CoinID mTo;
	
	public DigtalCashRateSource(CoinID from,CoinID to) {
		mFrom = from;
		mTo = to;
	}

	public CoinID getFrom() {
		return mFrom;
	}

	public CoinID getTo() {
		return mTo;
	}
}
