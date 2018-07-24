package com.houbi.api;

import com.bricker.framework.PlatefromSource;
import com.bricker.framework.CoinID;
import com.bricker.framework.PlateformApi;
import com.bricker.framework.SourceID;

public class HuobiSource extends PlatefromSource {
	
	public HuobiSource(CoinID from, CoinID to) {
		super(from, to);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SourceID getID() {
		// TODO Auto-generated method stub
		return SourceID.HUOBI;
	}

	@Override
	protected PlateformApi createPlateform() {
		// TODO Auto-generated method stub
		return new HuobiPlateformApi();
	}
}
