package org.tain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.Line;

@RepositoryRestResource
public interface LineRepository extends JpaRepository<Line, Long>{

}
