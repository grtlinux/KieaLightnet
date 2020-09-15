package org.tain.service.mid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tain.info.mid.DivisionCompInfo;
import org.tain.repository.mid.CompRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MidService {

	@Autowired
	private CompRepository compRepository;
	
	public List<DivisionCompInfo> getAll() {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		return this.compRepository.getAll();
	}
	
	public Page<DivisionCompInfo> getPage(Pageable pageable) {
		if (Flag.flag) {
			int pageNumber = pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1;
			int pageSize = pageable.getPageSize();
			Sort sort = pageable.getSort();
			
			pageSize = 12;
			
			pageable = PageRequest.of(pageNumber, pageSize, sort);
		}
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get(), pageable);
		return this.compRepository.getPage(pageable);
	}
	
	public DivisionCompInfo getById(Long id) {
		log.info("KANG-20200730 >>>>> {} {}", CurrentInfo.get());
		return this.compRepository.getById(id);
	}
}
