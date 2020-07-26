package org.tain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.Stmt;

@RepositoryRestResource
public interface StmtRepository extends JpaRepository<Stmt, Long>{

	Page<Stmt> findStmtListByGroupNo(Pageable pageable, Integer groupNo);
	
	Stmt findStmtBySeqNo(Integer seqNo);
}
