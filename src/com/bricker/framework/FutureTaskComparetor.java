package com.bricker.framework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.bricker.util.TimeUtils;
import com.bricker.util.log.Log;
import com.bricker.util.log.LogCategory;
import com.bricker.util.log.Tag;

public class FutureTaskComparetor extends BaseComparator {
	private static final Tag TAG = new Tag("FutureTaskComparetor", LogCategory.CAMPARE);
	private static final long TIMEOUT = 5000;
	
	private ScheduledExecutorService mService;

	private Map<SourceID, PriceInfo> mPriceMap = new HashMap<>();

	public FutureTaskComparetor(ComparePolicy policy) {
		super(policy);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init(ComparePolicy policy) {
		// TODO Auto-generated method stub
		super.init(policy);
		mService = Executors.newScheduledThreadPool(8);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		Log.i(TAG, "start compare");
		super.start();
		List<Source> sources = getSources();
		List<ScheduledFuture<?>> futures = new ArrayList<>();
		while (true) {
			for (final Source source : sources) {
				ScheduledFuture<?> future = mService.schedule(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						source.requestPrice(new OnNewPriceFromSource() {

							@Override
							public void onNewPrice(Source source, boolean succeed, double price, long timeStamp) {
								// TODO Auto-generated method stub
								if(succeed && source != null && isContain(source)) {
									SourceID id = source.getID();
									PriceInfo info = new PriceInfo(id, price, timeStamp);
									
									Log.d(TAG, "onNewPrice source = " + source.getName() + " succeed = " + succeed + " price = " + price + " timeStamp = " + TimeUtils.DateToString(timeStamp));
									
									synchronized (mPriceMap) {
										mPriceMap.put(id, info);
									}
								}
							}
						});
					}
				}, 0, TimeUnit.MILLISECONDS);

				futures.add(future);
			}

			for (ScheduledFuture<?> future : futures) {
				try {
					future.get(TIMEOUT, TimeUnit.MILLISECONDS);
				} catch (InterruptedException | TimeoutException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			Log.d(TAG, " all source price fetched start compare");
			
			List<PriceInfo> valueList = new ArrayList<>();
			synchronized (mPriceMap) {
				Collection<PriceInfo> values = mPriceMap.values();
				valueList.addAll(values);
			}
			
			if(mPolicy != null) {
				mPolicy.compare(valueList, mOnCompareResultListener);
			}
			
			synchronized (mPriceMap) {
				mPriceMap.clear();
			}
			long interval  = getInterval();
			Log.d(TAG, " sleep " + interval + " ms...");
			try {
				Thread.sleep(getInterval());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
		mService.shutdownNow();
		Log.i(TAG, "start compare");
	}

	@Override
	public void setPolicy(ComparePolicy policy) {
		// TODO Auto-generated method stub
		mPolicy = policy;
	}
}
