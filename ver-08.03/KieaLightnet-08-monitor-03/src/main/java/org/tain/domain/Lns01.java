package org.tain.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "tb_lns01"
	, indexes = {
			@Index(name = "lns01_userid_idx", unique = false, columnList = "user_id"),
	}
)
@SequenceGenerator(name = "lns01_seq"
	, sequenceName = "lns01_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties({})
@Slf4j
public class Lns01 {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lns01_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title", length = 256)
	private String title;
	
	@Column(name = "content", length = 1024)
	private String content;
	
	@Column(name = "user_id", length = 16)
	private String userId;
	
	@Column(name = "req_url", length = 128)
	private String reqUrl;
	
	@Column(name = "req_ver", length = 8)
	private String reqVer;
	
	@Column(name = "req_path", length = 62)
	private String reqPath;
	
	@Column(name = "req_method", length = 16)
	private String reqMethod;
	
	@Column(name = "req_parameters", length = 1024)
	private String reqParameters;
	
	@Lob
	@Column(name = "res_ret_json", columnDefinition = "CLOB")
	private String resRetJson;
	
	@Lob
	@Column(name = "res_ret_stream", columnDefinition = "CLOB")
	private String resRetStream;
	
	//@JsonIgnore
	//@JsonProperty(access = Access.WRITE_ONLY)
	//@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_date")
	private LocalDateTime createdDate = LocalDateTime.now();
	
	//@JsonIgnore
	@Column(name = "updated_date")
	private Timestamp updatedDate = new Timestamp(System.currentTimeMillis());
	
	//@JsonIgnore
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "job_date")
	private Date jobDate = new Date();
	
	@Builder
	public Lns01(
			String title,
			String content,
			String userId,
			String reqUrl,
			String reqVer,
			String reqPath,
			String reqMethod,
			String reqParameters,
			String resRetJson,
			String resRetStream
			) {
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.reqUrl = reqUrl;
		this.reqVer = reqVer;
		this.reqPath = reqPath;
		this.reqMethod = reqMethod;
		this.reqParameters = reqParameters;
		this.resRetJson = resRetJson;
		this.resRetStream = resRetStream;
	}
	
	////////////////////////////////////////////////////////////////////////
	
	public String toJson() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			log.info("ERROR: " + e.getMessage());
		}
		return "{}";
	}
	
	public String toPrettyJson() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (Exception e) {
			log.info("ERROR: " + e.getMessage());
		}
		return "{}";
	}
}