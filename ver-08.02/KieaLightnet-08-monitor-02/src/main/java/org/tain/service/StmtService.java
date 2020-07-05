package org.tain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tain.domain.Stmt;
import org.tain.repository.StmtRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StmtService {

	@Autowired
	private StmtRepository stmtRepository;
	
	public Page<Stmt> findStmtList(Pageable pageable) {
		if (Flag.flag) {
			int pageNumber = pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1;
			int pageSize = pageable.getPageSize();
			Sort sort = pageable.getSort();
			
			//pageSize = 10;
			sort = Sort.by("id").descending();
			
			pageable = PageRequest.of(pageNumber, pageSize, sort);
			
			log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get(), pageable);
		}
		return this.stmtRepository.findAll(pageable);
	}
	
	public Stmt findStmtById(Long id) {
		return this.stmtRepository.findById(id).orElse(new Stmt());
	}
	
	public Stmt findStmtBySeqNo(Integer seqNo) {
		return this.stmtRepository.findStmtBySeqNo(seqNo).orElse(new Stmt());
	}
	
	public Page<Stmt> findStmtListByGroupNo(Pageable pageable, Integer groupNo) {
		if (Flag.flag) {
			int pageNumber = pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1;
			int pageSize = pageable.getPageSize();
			Sort sort = pageable.getSort();
			
			//pageSize = 10;
			sort = Sort.by("id").descending();
			
			pageable = PageRequest.of(pageNumber, pageSize, sort);
			
			log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get(), pageable);
		}
		return this.stmtRepository.findStmtListByGroupNo(pageable, groupNo);
	}
}
