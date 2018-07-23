package com.gate.reset;

import java.util.HashMap;

import com.bricker.net.BaseClient;
import com.fasterxml.jackson.core.type.TypeReference;

public class GateClient extends BaseClient {
	private static final String QUERY_HOST = "data.gateio.io";

	private static final String TRADE_HOST = "api.gateio.io";

	private static final String SCHEME = "https://";

	private static final String PAIRS_URL = "/api2/1/pairs";

	private static final String MARKETINFO_URL = "/api2/1/marketinfo";

	private static final String MARKETLIST_URL = "/api2/1/marketlist";

	private static final String TICKERS_URL = "/api2/1/tickers";

	private static final String TICKER_URL = "/api2/1/ticker";

	private static final String ORDERBOOK_URL = "/api2/1/orderBook";

	private static final String BALANCE_URL = "/api2/1/private/balances";

	private static final String DEPOSITADDRESS_URL = "/api2/1/private/depositAddress";

	private static final String DEPOSITESWITHDRAWALS_URL = "/api2/1/private/depositsWithdrawals";

	private static final String BUY_URL = "/api2/1/private/buy";

	private static final String SELL_URL = "/api2/1/private/sell";

	private static final String CANCELORDER_URL = "/api2/1/private/cancelOrder";

	private static final String CANCELALLORDERS_URL = "/api2/1/private/cancelAllOrders";

	private static final String GETORDER_URL = "/api2/1/private/getOrder";

	private static final String OPENORDERS_URL = "/api2/1/private/openOrders";

	private static final String TRADEHISTORY_URL = "/api2/1/tradeHistory";

	private static final String WITHDRAW_URL = "/api2/1/private/withdraw";

	private static final String MYTRADEHISTORY_URL = "/api2/1/private/tradeHistory";
	
	public Ticker ticker(String symbol) {
		return get(SCHEME, QUERY_HOST, TICKER_URL + "/" + symbol, new HashMap(), null, new TypeReference<Ticker>() {
		});
	}
}
