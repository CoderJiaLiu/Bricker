package com.bricker.framework;

interface OnCompareResultListener {
	void onCompareResult(boolean findOpportunity,SourceID buy,SourceID sale, PriceInfo buyPrice, PriceInfo salePrice);
}
