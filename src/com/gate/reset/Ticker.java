package com.gate.reset;

import com.bricker.util.TextUtils;

public class Ticker {
	private int code;
	private String message;

	private double quoteVolume;
	private double baseVolume;
	private double highestBid;
	private double high24hr;
	private double last;
	private double lowestAsk;
	private String elapsed;
	private boolean result;
	private double low24hr;
	private double spercentChange;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getQuoteVolume() {
		return quoteVolume;
	}

	public void setQuoteVolume(double quoteVolume) {
		this.quoteVolume = quoteVolume;
	}

	public double getBaseVolume() {
		return baseVolume;
	}

	public void setBaseVolume(double baseVolume) {
		this.baseVolume = baseVolume;
	}

	public double getHighestBid() {
		return highestBid;
	}

	public void setHighestBid(double highestBid) {
		this.highestBid = highestBid;
	}

	public double getHigh24hr() {
		return high24hr;
	}

	public void setHigh24hr(double high24hr) {
		this.high24hr = high24hr;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}

	public double getLowestAsk() {
		return lowestAsk;
	}

	public void setLowestAsk(double lowestAsk) {
		this.lowestAsk = lowestAsk;
	}

	public String getElapsed() {
		return elapsed;
	}

	public void setElapsed(String elapsed) {
		this.elapsed = elapsed;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public double getLow24hr() {
		return low24hr;
	}

	public void setLow24hr(double low24hr) {
		this.low24hr = low24hr;
	}

	public double getSpercentChange() {
		return spercentChange;
	}

	public void setSpercentChange(double spercentChange) {
		this.spercentChange = spercentChange;
	}

	public long getTimeStamp() {
		long current = System.currentTimeMillis();
		String elapsedString = getElapsed();
		if (TextUtils.isEmpty(elapsedString)) {
			return current;
		}

		// 将xxxms转化成xxx
		int length = elapsedString.length();
		if (length > 2) {
			elapsedString = elapsedString.substring(0, length - 3);
		}
		try {
			long elapsed = Long.parseLong(elapsedString);
			return current - elapsed;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return current;
		}
	}

}
