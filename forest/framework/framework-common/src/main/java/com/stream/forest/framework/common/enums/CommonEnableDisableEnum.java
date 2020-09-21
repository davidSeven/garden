package com.stream.forest.framework.common.enums;

@SuppressWarnings("unused")
public enum CommonEnableDisableEnum implements BaseEnum {

	enable("enable", "启用"), //
	disable("disable", "禁用"),//
	;

	private final String key;
	private final String value;

	CommonEnableDisableEnum(String key, String value) {
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
