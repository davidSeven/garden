package com.stream.forest.framework.common.enums;

@SuppressWarnings("unused")
public enum CommonWeekEnum implements BaseEnum {

	mon("monday", "星期一"), //
	tue("tuesday", "星期二"), //
	wed("wednesday", "星期三"), //
	thu("thursday", "星期四"), //
	fri("friday", "星期五"), //
	sat("saturday", "星期六"), //
	sun("sunday", "星期日"), //
	;

	private final String key;
	private final String value;

	CommonWeekEnum(String key, String value) {
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
