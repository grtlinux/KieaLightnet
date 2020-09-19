package org.tain.object.mid;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DivisionComp {

	private String divCode;
	
	private String divTitle;
	
	private String divContent;
	
	private Long compId;
	
	private Integer compIdx;
	
	private String compStmtKr;
	
	private String compStmtEn;
	
	@Builder
	public DivisionComp(
			String divCode,
			String divTitle,
			String divContent,
			Long compId,
			Integer compIdx,
			String compStmtKr,
			String compStmtEn
			) {
		this.divCode = divCode;
		this.divTitle = divTitle;
		this.divContent = divContent;
		this.compId = compId;
		this.compIdx = compIdx;
		this.compStmtKr = compStmtKr;
		this.compStmtEn = compStmtEn;
	}
}
