package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.Trid;

@RepositoryRestResource
public interface TridRepository extends JpaRepository<Trid, Long>{

}
