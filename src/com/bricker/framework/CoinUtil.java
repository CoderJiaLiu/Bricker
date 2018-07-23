package com.bricker.framework;

public class CoinUtil {
	public static String coinIDtoSymbol(CoinID id) {
		switch (id) {
		case BTC:
			return "btc";
		case ETC:
			return "etc";
		case ETH:
			return "eth";
		case USDT:
			return "usdt";
		default:
			return "unknown";
		}
	}
}
