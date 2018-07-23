package com.houbi.api;

import com.bricker.framework.AbsPlatefromSource;
import com.bricker.framework.PlateformApi;
import com.bricker.framework.SourceID;

public class HuobiSource extends AbsPlatefromSource {
	
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
