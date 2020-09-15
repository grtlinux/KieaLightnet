package org.tain.domain.word;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Word {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "word", length = 32)
	private String word;
	
	@Column(name = "mean", length = 256)
	private String mean;
	
	@Builder
	public Word(
			//Long id,
			String word,
			String mean
			) {
		//this.id = id;
		this.word = word;
		this.mean = mean;
	}
}
