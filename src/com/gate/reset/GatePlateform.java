package com.gate.reset;

import com.bricker.framework.CoinID;
import com.bricker.framework.OnPriceResultListenner;
import com.bricker.framework.PlateformApi;
import com.bricker.framework.PlateformID;

public class GatePlateform implements PlateformApi {

	@Override
	public String getPlateformName() {
		// TODO Auto-generated method stub
		return "gate";
	}

	@Override
	public PlateformID getPlateformID() {
		// TODO Auto-generated method stub
		return PlateformID.GATE;
	}

	@Override
	public void requestPrice(CoinID from,CoinID to,OnPriceResultListenner listener) {
//		// TODO Auto-generated method stub
		String symbol = new StringBuilder().append(from.getName()).append("_").append(to.getName()).toString();
		GateClient client  = new GateClient();
		Ticker ticker = client.ticker(symbol);
		boolean result = ticker == null ? false : ticker.getResult();
		double rate = ticker == null ? 0.0f :  ticker.getLast();
		if(listener != null) {
			if(result) {
				listener.onPriceResult(result, from, to, rate, ticker.getTimeStamp());
			}else {
				listener.onPriceResult(result, from, to, 0.0f, 0);
			}
		}
	}

}
