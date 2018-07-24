package com.bricker.framework;

public interface OnFindOpportunityListener {
	void OnFindOpportunity(SourceID buy,SourceID sale, PriceInfo buyPrice, PriceInfo salePrice);
}
