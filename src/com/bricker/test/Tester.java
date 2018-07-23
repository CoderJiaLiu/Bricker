package com.bricker.test;

import java.io.IOException;


import com.bricker.framework.CoinID;
import com.bricker.framework.OnPriceRefreshedListenner;
import com.bricker.framework.OnPriceResultListenner;
import com.bricker.framework.Source;
import com.bricker.util.log.Log;
import com.bricker.util.log.LogCategory;
import com.bricker.util.log.LogLevel;
import com.gate.reset.GateSource;
import com.houbi.api.HuobiSource;


public class Tester {
	public static void main(String[] args) {
		Log.config(false, LogLevel.v, LogCategory.NET);
		Source source = new HuobiSource();
		source.subscribe(CoinID.BTC, CoinID.USDT, 5000, new OnPriceRefreshedListenner() {
			
			@Override
			public void onPriceRefreshed(String sourceName, boolean succeed, CoinID from, CoinID to, double rate,
					long timeStamp) {
				System.out.println("sourceName = " + sourceName + " succeed = " + succeed + " " + from.name() + "_" + to.name() + " time = " + timeStamp +  " rate = " + rate);
				// TODO Auto-generated method stub
				
			}
		});
		
		Source gateSource = new GateSource();
		gateSource.subscribe(CoinID.BTC, CoinID.USDT, 5000, new OnPriceRefreshedListenner() {
			
			@Override
			public void onPriceRefreshed(String sourceName, boolean succeed, CoinID from, CoinID to, double rate,
					long timeStamp) {
				System.out.println("sourceName = " + sourceName + " succeed = " + succeed + " " + from.name() + "_" + to.name() + " time = " + timeStamp +  " rate = " + rate);
				// TODO Auto-generated method stub
				
			}
		});
	}
}
