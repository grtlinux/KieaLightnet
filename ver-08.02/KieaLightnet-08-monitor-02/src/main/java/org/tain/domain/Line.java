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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_line"
	, indexes = {
			@Index(name = "line_userid_idx", unique = false, columnList = "user_id"),
	}
)
@SequenceGenerator(name = "line_seq"
	, sequenceName = "line_seq"
	, initialValue = 1
	, allocationSize = 1
)
@NoArgsConstructor
@Data
@JsonIgnoreProperties({})
public class Line {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "line_seq")
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
	
	@Column(name = "res_ret_json", length = 10240)
	private String resRetJson;
	
	@Column(name = "res_ret_stream", length = 10240)
	private String resRetStream;
	
	//@JsonIgnore
	@JsonProperty(access = Access.READ_ONLY)
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
	public Line(
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
}