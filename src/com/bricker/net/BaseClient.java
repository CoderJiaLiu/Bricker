package com.bricker.net;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.bricker.util.log.LogCategory;
import com.bricker.util.log.Log;
import com.bricker.util.log.Tag;
import com.bricker.util.net.NetUtil;
import com.fasterxml.jackson.core.type.TypeReference;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BaseClient {
	private static final Tag TAG = new Tag("BaseClient", LogCategory.NET);
	private static OkHttpClient sClient;
	private static final int DEFAULT_CONN_TIMEOUT = 5000;
	private static final int DEFAULT_READ_TIMEOUT = 5000;
	private static final int DEFAULT_WRITE_TIMEOUT = 5000;
	private static final MediaType JSON = MediaType.parse("application/json");

	public BaseClient() {
		Log.i(TAG, "BaseClient created");
		if (sClient == null) {
			sClient = createOkHttpClient(DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT, DEFAULT_WRITE_TIMEOUT);
		}
	}

	protected OkHttpClient createOkHttpClient(int connectTimeOut, int readTimeOut, int writeTimeOut) {
		Log.i(TAG, "createOkHttpClient connectTimeOut = " + connectTimeOut + " readTimeOut = " + readTimeOut
				+ " writeTimeOut = " + writeTimeOut);
		return new Builder().connectTimeout(connectTimeOut, TimeUnit.MILLISECONDS)
				.readTimeout(readTimeOut, TimeUnit.MILLISECONDS).writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS)
				.build();
	}

	protected <T> T call(String scheme, HttpMethod method, String host, String path, Object content,
			Map<String, String> params, Encoder encoder, TypeReference<T> ref) {
		return call(scheme, method, host, path, content, params, encoder, ref, false, null);
	}

	protected <T> T call(String scheme, HttpMethod method, String host, String path, Object content,
			Map<String, String> params, Encoder encoder, TypeReference<T> ref, boolean needSignatur, Signer signer) {
		Log.d(TAG, "call scheme = " + scheme + " method = " + method + " host = " + host + " path = " + path
				+ " params = " + NetUtil.paramToString(params, encoder));

		String urlStr;
		if (needSignatur) {
			if (signer == null) {
				Log.e(TAG, "needSignatur == true but signer == null");
				throw (new IllegalArgumentException("needSignatur == true but signer == null"));
			} else {
				urlStr = signer.signature(scheme, host, path, params);
			}
		} else {
			String paramString = NetUtil.paramToString(params, encoder);
			urlStr = NetUtil.assembleUrl(scheme, host, path, paramString);
		}
		
		
		try {
			Request.Builder builder;
			if (method == HttpMethod.POST) {
				RequestBody body = RequestBody.create(JSON, JsonUtil.writeValue(content));
				builder = new Request.Builder().url(urlStr).post(body);
			} else if (method == HttpMethod.GET) {
				builder = new Request.Builder().url(urlStr).get();
			} else {
				// Todo other method
				Log.e(TAG, "unsupport method mehotd = " + method);
				builder = null;
				return null;
			}
			
			prepareHeader(builder);
			
			Request request = builder.build();
			Log.d(TAG, "call request = " + request);
			
			Response response;
			response = sClient.newCall(request).execute();
			String s = response.body().string();
			Log.d(TAG, "call response = " + s);
			
			T o= JsonUtil.readValue(s, ref);
			return o;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.w(TAG, e.toString());
			return null;
		}
	}
	
	protected void prepareHeader(okhttp3.Request.Builder builder) {
		
	}
	
	public <T> T get(String scheme, String host, String path,
			Map<String, String> params, Encoder encoder, TypeReference<T> ref) {
		return call(scheme, HttpMethod.GET, host, path, null, params, encoder, ref);
	}
	
	public <T> T get(String scheme, String host, String path,
			Map<String, String> params, Encoder encoder, TypeReference<T> ref, boolean needSignatur, Signer signer) {
		return call(scheme, HttpMethod.GET, host, path, null, params, encoder, ref, needSignatur, signer);
	}
	
	public <T> T post(String scheme, String host, String path,
			Map<String, String> params, Object content,Encoder encoder, TypeReference<T> ref) {
		return call(scheme, HttpMethod.GET, host, path, content, params, encoder, ref);
	}
	
	public <T> T post(String scheme, String host, String path,
			Map<String, String> params, Object content, Encoder encoder, TypeReference<T> ref, boolean needSignatur, Signer signer) {
		return call(scheme, HttpMethod.GET, host, path, content, params, encoder, ref, needSignatur, signer);
	}
}