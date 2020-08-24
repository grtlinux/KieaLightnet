package org.tain.object.trid.res;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.tain.annotation.AbstractStream;
import org.tain.annotation.StreamAnnotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class _TridResData extends AbstractStream {

	@StreamAnnotation(length = 10)
	private String code = "TRID.RES";
	
	@StreamAnnotation
	private _TridResTrid trid = new _TridResTrid();
	
	@StreamAnnotation(length = 19)
	private String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	
	@StreamAnnotation(length = 10)
	private String status;
	
	@StreamAnnotation(length = 50)
	private String message;
}
