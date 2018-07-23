package com.bricker.net;

import java.util.Map;

public interface Signer {
	String signature (String scheme,String host,String path,Map<String,String> params);
}
