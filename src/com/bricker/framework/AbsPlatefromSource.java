package com.bricker.framework;

import java.util.Hashtable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class AbsPlatefromSource implements Source {
	private Hashtable<Integer, ScheduledFuture<?>> mTaskTable;
	private PlateformApi mPlateform;
	private ScheduledThreadPoolExecutor mExcutor;
	public AbsPlatefromSource() {
		mExcutor = new ScheduledThreadPoolExecutor(2);
		mTaskTable = new Hashtable<>();
		mPlateform = createPlateform();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(mPlateform != null) {
			return mPlateform.getPlateformName();
		}
		return null;
	}

	
	
	@Override
	public void requestPrice(CoinID from, CoinID to, OnPriceRefreshedListenner listener) {
		// TODO Auto-generated method stub
		mPlateform.requestPrice(from, to, new OnPriceResultListenner() {
			
			@Override
			public void onPriceResult(boolean succeed, CoinID from, CoinID to, double rate, long timeStamp) {
				// TODO Auto-generated method stub
				if(listener != null) {
					listener.onPriceRefreshed(getName(), succeed, from, to, rate, timeStamp);
				}
			}
		});
	}

	@Override
	public int subscribe(final CoinID from, final CoinID to, long period,final OnPriceRefreshedListenner listener) {
		// TODO Auto-generated method stub
		ScheduledFuture<?> future = mExcutor.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mPlateform.requestPrice(from, to, new OnPriceResultListenner() {
					
					@Override
					public void onPriceResult(boolean succeed, CoinID from, CoinID to, double rate, long timeStamp) {
						// TODO Auto-generated method stub
						if(listener != null) {
							listener.onPriceRefreshed(getName(), succeed, from, to, rate, timeStamp);
						}
					}
				});
			}
		}, 0L, period, TimeUnit.MILLISECONDS);
		int code = future.hashCode();
		mTaskTable.put(code, future);
		return code;
	}

	@Override
	public void unsubscribe(int subscribeId) {
		// TODO Auto-generated method stub
		ScheduledFuture<?> future = mTaskTable.get(subscribeId);
		if(future != null) {
			future.cancel(true);
		}
	}
	
	abstract protected PlateformApi createPlateform();
}
