package org.tain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tain.domain.Link;
import org.tain.repository.LinkRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LinkService {

	@Autowired
	private LinkRepository linkRepository;
	
	public Page<Link> findLineList(Pageable pageable) {
		if (Flag.flag) {
			int pageNumber = pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1;
			int pageSize = pageable.getPageSize();
			Sort sort = pageable.getSort();
			
			pageSize = 10;
			sort = Sort.by("id").descending();
			
			pageable = PageRequest.of(pageNumber, pageSize, sort);
			
			log.info("KANG-20200705 >>>>> {} {}", CurrentInfo.get(), pageable);
		}
		return this.linkRepository.findAll(pageable);
	}
	
	public Link findLineById(Long id) {
		return this.linkRepository.findById(id).orElse(new Link());
	}
}
