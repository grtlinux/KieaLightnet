package org.tain.repository.word;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.word.Word;

@RepositoryRestResource
public interface WordRepository extends JpaRepository<Word, Long>{

}
