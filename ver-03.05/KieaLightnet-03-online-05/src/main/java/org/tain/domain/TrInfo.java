package org.tain.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tb_trinfo"
	, indexes = {
			@Index(name = "trinfo_idx01", unique = true, columnList = "division,trid"),
	}
)
@SequenceGenerator(name = "trinfo_seq"
	, sequenceName = "trinfo_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@JsonIgnoreProperties(value = {})
public class TrInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trinfo_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "division", length = 16)
	private String division;
	
	@Column(name = "trid", length = 16)
	private String trid;
	
	@Lob
	@Column(name = "req_stream")
	private String reqStream;
	
	@Lob
	@Column(name = "req_json")
	private String reqJson;
	
	@Lob
	@Column(name = "res_json")
	private String resJson;
	
	@Lob
	@Column(name = "res_stream")
	private String resStream;
	
	@Column(name = "status_code", length = 16)
	private String statusCode;
	
	@Column(name = "status_message", length = 128)
	private String statusMessage;
	
	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "cre_date")
	private Timestamp creDate;
	
	@Builder
	public TrInfo(
			String division,
			String trid,
			String reqStream,
			String reqJson,
			String resJson,
			String resStream,
			String statusCode,
			String statusMessage
			) {
		this.division = division;
		this.trid = trid;
		this.reqStream = reqStream;
		this.reqJson = reqJson;
		this.resJson = resJson;
		this.resStream = resStream;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	///////////////////////////////////////////////////////////////////
	
	public String toJson() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			return "{}";
		}
	}
	
	public String toPrettyJson() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (Exception e) {
			return "{}";
		}
	}
}
