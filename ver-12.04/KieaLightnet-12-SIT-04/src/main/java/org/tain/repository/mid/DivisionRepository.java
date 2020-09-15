package org.tain.repository.mid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.mid.Division;

@RepositoryRestResource
public interface DivisionRepository extends JpaRepository<Division, String>{

}
