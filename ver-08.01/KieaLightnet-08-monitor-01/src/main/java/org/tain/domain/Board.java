package org.tain.domain;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_board"
	, indexes = {
			@Index(name = "board_title_idx", unique = false, columnList = "title"),
			@Index(name = "board_userid_idx", unique = false, columnList = "user_id"),
	}
)
@SequenceGenerator(name = "board_seq"
	, sequenceName = "board_seq"
	, initialValue = 1
	, allocationSize = 1
)
@NoArgsConstructor
@Data
@JsonIgnoreProperties({})
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq")
	@JsonIgnore
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title", length = 256)
	private String title;
	
	@Column(name = "sub_title", length = 256)
	private String subTitle;
	
	@Column(name = "content", length = 1024)
	private String content;
	
	@Column(name = "user_id", length = 16)
	private String userId;
	
	//@JsonIgnore
	//@JsonProperty(access = Access.WRITE_ONLY)
	//@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_date")
	private LocalDateTime createdDate = LocalDateTime.now();
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "job_date")
	private Date jobDate;
	
	@Builder
	public Board(
			String title,
			String subTitle,
			String content,
			String userId
			) {
		this.title = title;
		this.subTitle = subTitle;
		this.content = content;
		this.userId = userId;
		this.jobDate = new Date();
	}
}
