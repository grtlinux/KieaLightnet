package org.tain.domain.mid;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Division {

	@Id
	private String code;
	
	private String title;
	
	private String content;
	
	@Builder
	public Division(
			String code,
			String title,
			String content
			) {
		this.code = code;
		this.title = title;
		this.content = content;
	}
}
