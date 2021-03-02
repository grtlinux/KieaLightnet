package org.tain.utils.enums;

public enum OptionType {
	RM_NULL(1),
	RM_SPACE(2),
	
	OPTION3(4),
	OPTION4(8),
	OPTION5(16),
	OPTION6(32),
	OPTION7(64),
	OPTION8(128),
	OPTION9(256),
	OPTION10(512),
	OPTION11(1024),
	OPTION12(2048),
	OPTION13(4096),
	OPTION14(8192),
	OPTION15(16384),
	OPTION16(32768);

	/*
	 * 0000 0000  0000 0000
	 * 0000 0000  0000 0000  0000 0000  0000 0000
	 */
	private int value;

	OptionType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
