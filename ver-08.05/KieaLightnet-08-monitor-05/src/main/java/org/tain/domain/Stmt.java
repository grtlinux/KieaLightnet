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
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "tb_stmt"
	, indexes = {
			@Index(name = "stmt_seqno_idx", unique = true, columnList = "seq_no"),
	}
)
@SequenceGenerator(name = "stmt_seq"
	, sequenceName = "stmt_seq"
	, initialValue = 1
	, allocationSize = 1
)
@NoArgsConstructor
@Data
@JsonIgnoreProperties({})
@Slf4j
public class Stmt {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stmt_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "group_no")
	private Integer groupNo;
	
	@Column(name = "seq_no")
	private Integer seqNo;
	
	@Column(name = "stmt_en", length = 1024)
	private String stmtEn;
	
	@Column(name = "stmt_kr", length = 1024)
	private String stmtKr;
	
	//@JsonIgnore
	//@JsonProperty(access = Access.READ_ONLY)
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
	public Stmt(
			Integer groupNo,
			Integer seqNo,
			String stmtEn,
			String stmtKr
			) {
		this.groupNo = groupNo;
		this.seqNo = seqNo;
		this.stmtEn = stmtEn;
		this.stmtKr = stmtKr;
	}

	public Stmt orElse(Stmt stmt) {
		return stmt;
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
