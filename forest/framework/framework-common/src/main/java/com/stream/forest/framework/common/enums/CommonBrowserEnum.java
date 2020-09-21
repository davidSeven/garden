package com.stream.forest.framework.common.enums;

public enum CommonBrowserEnum implements BaseEnum {

	IE("IE", "IE"),
	Edge("Edge", "Edge"),
	Safari("Safari", "Safari"),
	Chrome("Chrome", "Chrome"),
	Firefox("Firefox", "Firefox"),
	;

	private final String key;
	private final String value;

	CommonBrowserEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getValue() {
		return value;
	}

}
