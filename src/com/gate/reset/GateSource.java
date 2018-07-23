package com.gate.reset;

import com.bricker.framework.AbsPlatefromSource;
import com.bricker.framework.CoinID;
import com.bricker.framework.OnPriceRefreshedListenner;
import com.bricker.framework.PlateformApi;
import com.bricker.framework.SourceID;

public class GateSource extends AbsPlatefromSource {
	
	
	@Override
	public SourceID getID() {
		// TODO Auto-generated method stub
		return SourceID.GATE;
	}

	@Override
	protected PlateformApi createPlateform() {
		// TODO Auto-generated method stub
		return new GatePlateform();
	}

}
