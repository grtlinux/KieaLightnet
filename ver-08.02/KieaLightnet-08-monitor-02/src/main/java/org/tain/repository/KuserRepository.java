package org.tain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.Kuser;

@RepositoryRestResource
public interface KuserRepository extends JpaRepository<Kuser, Long>{

	Page<Kuser> findKuserListByRole(Pageable pageable, String role);
	
	Kuser findKuserByUsername(String username);
}
