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

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_token"
	, indexes = {
			@Index(name = "token", unique = false, columnList = "token"),
	}
)
@SequenceGenerator(name = "token_seq"
	, sequenceName = "token_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_seq")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "token", length = 128)
	private String token;
	
	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "cre_date")
	private Timestamp creDate;
	
	@Builder
	public Token(
			String token
			) {
		this.token = token;
	}
}
