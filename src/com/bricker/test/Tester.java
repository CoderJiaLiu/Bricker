package com.bricker.test;

import com.bricker.framework.CoinID;
import com.bricker.framework.DigtalCashRateSource;
import com.bricker.framework.OnNewPriceFromSource;
import com.bricker.framework.Source;
import com.bricker.util.TimeUtils;
import com.bricker.util.log.Log;
import com.bricker.util.log.LogCategory;
import com.bricker.util.log.LogLevel;
import com.gate.reset.GateSource;
import com.houbi.api.HuobiSource;

public class Tester {
	public static void main(String[] args) {
		Log.config(false, LogLevel.v, LogCategory.NET);
		Source source = new HuobiSource(CoinID.BTC, CoinID.USDT);
		source.subscribe(5000, new OnNewPriceFromSource() {

			@Override
			public void onNewPrice(Source source, boolean succeed, double price, long timeStamp) {
				DigtalCashRateSource digtalCashRateSource = (DigtalCashRateSource)source;
				CoinID from = digtalCashRateSource.getFrom();
				CoinID to = digtalCashRateSource.getTo();
				System.out.println("sourceName = " + source.getName() + "\tsucceed = " + succeed + " " + from.name() + "_"
						+ to.name() + " time = " + TimeUtils.DateToString(timeStamp) + " price = " + price);
				// TODO Auto-generated method stub

			}
		});

		Source gateSource = new GateSource(CoinID.BTC, CoinID.USDT);
		gateSource.subscribe(5000, new OnNewPriceFromSource() {

			@Override
			public void onNewPrice(Source source, boolean succeed, double price, long timeStamp) {
				DigtalCashRateSource digtalCashRateSource = (DigtalCashRateSource)source;
				CoinID from = digtalCashRateSource.getFrom();
				CoinID to = digtalCashRateSource.getTo();
				System.out.println("sourceName = " + source.getName() + "\t\tsucceed = " + succeed + " " + from.name() + "_"
						+ to.name() + " time = " + TimeUtils.DateToString(timeStamp) + " price = " + price);
				// TODO Auto-generated method stub

			}
		});
	}
}
