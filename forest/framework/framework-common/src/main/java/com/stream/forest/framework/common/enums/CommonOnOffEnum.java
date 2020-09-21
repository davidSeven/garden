package com.stream.forest.framework.common.enums;

@SuppressWarnings("unused")
public enum CommonOnOffEnum implements BaseEnum {

	on("on", "开启"), //
	off("off", "关闭"),//
	;

	private final String key;
	private final String value;

	CommonOnOffEnum(String key, String value) {
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
