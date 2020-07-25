package org.tain.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_trid"
	, indexes = {
			@Index(name = "trid_idx", unique = true, columnList = "trid"),
	}
)
@SequenceGenerator(name = "trid_seq"
	, sequenceName = "trid_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class Trid {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trid_seq")
	@Column(name = "id")
	private Long id;

	@Column(name = "trid", length = 32)
	private String trid;

	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "cre_date")
	private Timestamp creDate;

	@Builder
	public Trid(
			String trid
			) {
		this.trid = trid;
	}
	
	//////////////////////////////////////////////////////////////////
	
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
