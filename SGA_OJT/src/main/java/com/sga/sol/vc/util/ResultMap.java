package com.sga.sol.vc.util;

import java.util.HashMap;

import org.springframework.jdbc.support.JdbcUtils;

public class ResultMap extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;

	public Object put(String key, Object value) {
		return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(key), value);
	}
}
