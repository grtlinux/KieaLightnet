package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.Lns01;

@RepositoryRestResource
public interface Lns01Repository extends JpaRepository<Lns01, Long>{

}
