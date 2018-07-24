package com.bricker.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.bricker.util.log.LogCategory;
import com.bricker.util.log.Tag;

public class FutureTaskComparetor extends BaseComparator {
	private static final Tag TAG = new Tag("FutureTaskComparetor",LogCategory.CAMPARE);
	
	private ScheduledExecutorService mService;
	
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
	public void start(CoinID from, CoinID to) {
		// TODO Auto-generated method stub
		super.start(from, to);
		List<Source> sources = getSources();
		List<ScheduledFuture<?>> futures = new ArrayList<>();
		for (final Source source : sources) {
			ScheduledFuture<?> future = mService.schedule(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					source.requestPrice(new OnNewPriceFromSource() {
						
						@Override
						public void onNewPrice(Source source, boolean succeed, double price, long timeStamp) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			}, 0, TimeUnit.MICROSECONDS);
			
			futures.add(future);
		}
		
		for(ScheduledFuture<?> future :futures) {
			try {
				future.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}



	@Override
	public void setPolicy(ComparePolicy policy) {
		// TODO Auto-generated method stub
		
	}

}
