package com.houbi.api;

import java.io.IOException;
import java.util.List;


import com.bricker.framework.CoinID;
import com.bricker.framework.CoinUtil;
import com.bricker.framework.OnPriceResultListenner;
import com.bricker.framework.PlateformApi;
import com.bricker.framework.PlateformID;

public class HuobiPlateformApi implements PlateformApi {
	private static final String HOST = "https://api.huobipro.com";
	private static final String TICKER_PATH = "/market/detail/merged";

	@Override
	public String getPlateformName() {
		// TODO Auto-generated method stub
		return "huobi";
	}

	@Override
	public PlateformID getPlateformID() {
		// TODO Auto-generated method stub
		return PlateformID.HUOBI;
	}

	@Override
	public void requestPrice(CoinID from, CoinID to, OnPriceResultListenner listener) {
		// TODO Auto-generated method stub
			String symbol = new StringBuilder().append(CoinUtil.coinIDtoSymbol(from)).append(CoinUtil.coinIDtoSymbol(to)).toString();
		HuobiClient client = new HuobiClient();
		MergedResponse<Merged> response = client.getMerged(symbol);
		if (response == null || !"ok".equals(response.getStatus())) {
			if (listener != null) {
				listener.onPriceResult(false, from, to, 0.0f, 0);
			}
		} else {
			if (listener != null) {
				Merged merged = response.checkAndReturn();
				List<Double> ask = merged.getAsk();
				listener.onPriceResult(true, from, to, merged.getAsk().get(0), response.getTs());
			}
		}
	}

}
