package com.bricker.framework;

import java.util.List;

import com.bricker.util.log.Log;
import com.bricker.util.log.LogCategory;
import com.bricker.util.log.Tag;

public  class BaseComparePolicy implements ComparePolicy {
	private static final Tag TAG = new Tag("BaseComparePolicy",LogCategory.CAMPARE);
	public static final double PRICE_THRESHOLD = 2.0f;
	public static final long TIME_DIFF_THRESHOLD = 60 * 1000;

	private double mPriceThreshold = 0;
	
	public BaseComparePolicy(double priceThreshold) {
		mPriceThreshold = priceThreshold;
		Log.d(TAG, "mPriceThreshold = " + priceThreshold);
	}
	
	@Override
	public int compare(PriceInfo infoA, PriceInfo infoB) {
		// TODO Auto-generated method stub
		if (infoA == infoB) {
			return 0;
		}

		if (infoA == null) {
			return -1;
		}

		if (infoB == null) {
			return 1;
		}

		if (infoA.getSourceID() == infoB.getSourceID()) {
			return 0;
		}

		double value = infoA.getRate() - infoB.getRate();

		if (value == 0) {
			return 0;
		}

		if (value > 0) {
			return 1;
		}

		return -1;
	}

	@Override
	public boolean compare(List<PriceInfo> prices, OnCompareResultListener listener) {
		// TODO Auto-generated method stub
		PriceInfo max = findMax(prices);
		PriceInfo min = findMin(prices);

		if (compare(max, min) > 0) {
			double result = max.getRate() - min.getRate();
			long timeDiff = max.getTimeStamp() - min.getTimeStamp();
			if (result >= mPriceThreshold && Math.abs(timeDiff) <= TIME_DIFF_THRESHOLD) {
				if (listener != null) {
					listener.onCompareResult(true, min.getSourceID(), max.getSourceID(), min, max);
				}
			} else {
				if (listener != null) {
					listener.onCompareResult(false, min.getSourceID(), max.getSourceID(), null, null);
				}
			}
		}
		return false;
	}

	protected PriceInfo findMax(List<PriceInfo> prices) {
		if (prices == null || prices.isEmpty()) {
			return null;
		}

		int SIZE = prices.size();
		if (SIZE == 1) {
			return prices.get(0);
		}

		PriceInfo tempMax = prices.get(0);

		for (int i = 1; i < SIZE; i++) {
			PriceInfo info = prices.get(i);
			if (compare(info, tempMax) > 0) {
				tempMax = info;
			}
		}

		return tempMax;
	}

	protected PriceInfo findMin(List<PriceInfo> prices) {
		if (prices == null || prices.isEmpty()) {
			return null;
		}

		int SIZE = prices.size();
		if (SIZE == 1) {
			return prices.get(0);
		}

		PriceInfo tempMin = prices.get(0);

		for (int i = 1; i < SIZE; i++) {
			PriceInfo info = prices.get(i);
			if (compare(info, tempMin) < 0) {
				tempMin = info;
			}
		}

		return tempMin;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "找到最大的和最小的,判断最大的和最小的差值,如果大于阀值,且时间相差小于阀值则成功";
	}
}
