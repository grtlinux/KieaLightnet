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
@Table(name = "tb_kuser"
	, indexes = {
			@Index(name = "kuser_username_idx", unique = true, columnList = "username"),
	}
)
@SequenceGenerator(name = "kuser_seq"
	, sequenceName = "kuser_seq"
	, initialValue = 1
	, allocationSize = 1
)
@NoArgsConstructor
@Data
@JsonIgnoreProperties({})
@Slf4j
public class Kuser {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kuser_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username", length = 32)
	private String username;
	
	@Column(name = "password", length = 32)
	private String password;
	
	@Column(name = "role", length = 32)
	private String role;
	
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
	public Kuser(
			String username,
			String password,
			String role
			) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Kuser orElse(Kuser kuser) {
		return kuser;
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