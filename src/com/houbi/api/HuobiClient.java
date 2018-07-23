package com.houbi.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.bricker.net.BaseClient;
import com.bricker.net.Encoder;
import com.fasterxml.jackson.core.type.TypeReference;

import okhttp3.Request.Builder;

public class HuobiClient extends BaseClient {
	private static final String SCHEME = "https://";
	private static final String HOST = "api.hadax.com";
	private static final String TICKER_PATH = "/market/detail/merged";
	
	private static final String SYMBOL_KEY = "symbol";
	
	@SuppressWarnings("unchecked")
	public MergedResponse<Merged> getMerged(String symbol) {
		Map<String,String> params = new HashMap<>();
		params.put(SYMBOL_KEY, symbol);
		TypeReference ref = new TypeReference<MergedResponse<Merged>>() {};
		MergedResponse<Merged> response = (MergedResponse<Merged>) get(SCHEME, HOST, TICKER_PATH, params, new Encoder() {
			
			@Override
			public String encode(String s) {
				// TODO Auto-generated method stub
				try {
		            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
		        } catch (UnsupportedEncodingException e) {
		            throw new IllegalArgumentException("UTF-8 encoding not supported!");
		        }
			}
		},ref);
		return response;
	}

	@Override
	protected void prepareHeader(Builder builder) {
		// TODO Auto-generated method stub
		super.prepareHeader(builder);
		builder.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
	}
	
	
}
