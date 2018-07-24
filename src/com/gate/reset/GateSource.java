package com.gate.reset;

import com.bricker.framework.PlatefromSource;
import com.bricker.framework.CoinID;
import com.bricker.framework.OnNewPriceFromSource;
import com.bricker.framework.PlateformApi;
import com.bricker.framework.SourceID;

public class GateSource extends PlatefromSource {
	public GateSource(CoinID from, CoinID to) {
		super(from, to);
		// TODO Auto-generated constructor stub
	}

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
