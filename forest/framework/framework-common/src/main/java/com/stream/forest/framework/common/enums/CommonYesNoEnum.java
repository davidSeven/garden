package com.stream.forest.framework.common.enums;

@SuppressWarnings("unused")
public enum CommonYesNoEnum implements BaseEnum {

	yes("yes", "是"), //
	no("no", "否"),//
	;

	private final String key;
	private final String value;

	CommonYesNoEnum(String key, String value) {
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
