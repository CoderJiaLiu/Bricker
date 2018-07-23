package com.bricker.util.net;

import java.util.Map;
import java.util.stream.Collectors;

import com.bricker.net.Encoder;
import com.bricker.util.TextUtils;
import com.bricker.util.log.Log;
import com.bricker.util.log.LogCategory;
import com.bricker.util.log.Tag;

public class NetUtil {
	private static final Tag TAG = new Tag("NetUtil", LogCategory.NET);
	
	public static final String assembleUrl(String scheme,String host,String path,String params) {
		if(TextUtils.isEmpty(scheme) || TextUtils.isEmpty(host)) {
			throw (new IllegalArgumentException());
		}
		String url = new StringBuilder().append(scheme).append(host).append(path).append("?").append(params).toString();
		return url;
	}
	
	public static String paramToString(Map<String, String> params,Encoder encoder) {
		if(params == null) {
			Log.w(TAG, "params = null");
			return null;
		}
		return String.join("&", params.entrySet().stream().map((entry) -> {
            String ttt = entry.getKey() + "=" + (encoder == null ? entry.getValue() : encoder.encode(entry.getValue()));
            return ttt;
        }).collect(Collectors.toList()));
	}
}
