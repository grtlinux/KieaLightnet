package org.tain.service.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tain.domain.word.Word;
import org.tain.repository.word.WordRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WordService {

	@Autowired
	private WordRepository wordRepository;
	
	public Page<Word> findWordList(Pageable pageable) {
		if (Flag.flag) {
			int pageNumber = pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1;
			int pageSize = pageable.getPageSize();
			Sort sort = pageable.getSort();
			
			pageSize = 15;
			sort = Sort.by("id").ascending();
			
			pageable = PageRequest.of(pageNumber, pageSize, sort);
		}
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		return this.wordRepository.findAll(pageable);
	}
	
	public Word findWordById(Long id) {
		log.info("KANG-20200808 >>>>> {} {}", CurrentInfo.get());
		return this.wordRepository.findById(id).orElse(new Word());
	}
}
