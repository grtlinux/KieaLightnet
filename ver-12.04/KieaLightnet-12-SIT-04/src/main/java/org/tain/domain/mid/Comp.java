package org.tain.domain.mid;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Comp {

	@Id
	private Long id;
	
	private String code;
	
	private int idx;
	
	private String stmtKr;
	
	private String stmtEn;
	
	@ManyToOne
	private Division division;
	
	@Builder
	public Comp(
			Long id,
			String code,
			int idx,
			String stmtKr,
			String stmtEn,
			Division division
			) {
		this.id = id;
		this.code = code;
		this.idx = idx;
		this.stmtKr = stmtKr;
		this.stmtEn = stmtEn;
		this.division = division;
	}
}
