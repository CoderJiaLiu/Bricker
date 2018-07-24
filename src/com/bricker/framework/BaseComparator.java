package com.bricker.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import com.bricker.util.log.Log;
import com.bricker.util.log.LogCategory;
import com.bricker.util.log.Tag;

public abstract class BaseComparator implements Comparator {
	private static final Tag TAG = new Tag("BaseComparator", LogCategory.CAMPARE);
	private static final long DEFAULT_INERVAL = 5000;
	private long mInterval = DEFAULT_INERVAL;
	private List<Source> mSources = new ArrayList<>();
	protected ComparePolicy mPolicy;
	protected OnCompareResultListener mOnCompareResultListener;
	protected OnFindOpportunityListener mOnFindOpportunityListener;

	public BaseComparator(ComparePolicy policy) {
		super();
		init(policy);
	}

	protected void init(ComparePolicy policy) {
		if (policy == null) {
			Log.e(TAG, "policy cant be null");
			throw new NullPointerException();
		}
		mPolicy = policy;
		mOnCompareResultListener = new OnCompareResultListener() {

			@Override
			public void onCompareResult(boolean findOpportunity, SourceID buy, SourceID sale, PriceInfo buyPrice, PriceInfo salePrice) {
				// TODO Auto-generated method stub
				if (findOpportunity) {
					if (mOnFindOpportunityListener != null) {
						mOnFindOpportunityListener.OnFindOpportunity(buy, sale, buyPrice, salePrice);
					}
				} else {
					Log.d(TAG, "dont find opportunity");
				}
			}
		};
		Log.i(TAG, "Comparator init policy is \n " + mPolicy.getDescription());
	}

	@Override
	public void add(Source source) {
		// TODO Auto-generated method stub
		if (source == null) {
			Log.w(TAG, "Cant add a null source");
			throw new NullPointerException();
		}

		synchronized (mSources) {
			if (isContain(source)) {
				Log.w(TAG, "this source already in list");
				throw new IllegalArgumentException();
			}

			mSources.add(source);
		}
	}

	@Override
	public void remove(Source source) {
		// TODO Auto-generated method stub
		if (source == null) {
			Log.w(TAG, "Cant add a null source");
			throw new NullPointerException();
		}

		synchronized (mSources) {
			if (!isContain(source)) {
				Log.w(TAG, "this source not in source list");
				throw new IllegalArgumentException();
			}

			mSources.remove(source);
		}
	}

	@Override
	public void setOnFindOpportunityListener(OnFindOpportunityListener listener) {
		// TODO Auto-generated method stub
		mOnFindOpportunityListener = listener;
	}

	public boolean isContain(Source source) {
		if (source == null) {
			return false;
		}

		for (Source ssource : mSources) {
			if (ssource == source || ssource.getID() == source.getID()) {
				return true;
			}
		}

		return false;
	}

	public List<Source> getSources() {
		synchronized (mSources) {
			return mSources;
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		Log.i(TAG, "start compare");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		Log.i(TAG, "stop compare");
	}
	
	public long getInterval() {
		return mInterval;
	}

	public void setInterval(long interval) {
		mInterval = interval;
	}
}
