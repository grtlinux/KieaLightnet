package org.tain.object;

import lombok.Data;

@Data
@Deprecated
public class Packet {

	private int length;
	private String data;
}
