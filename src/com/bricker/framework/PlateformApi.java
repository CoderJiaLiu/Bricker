package com.bricker.framework;

public interface PlateformApi {
	String getPlateformName();
	PlateformID getPlateformID();
	void requestPrice(final CoinID from,CoinID to,OnPriceResultListenner listener);
}
