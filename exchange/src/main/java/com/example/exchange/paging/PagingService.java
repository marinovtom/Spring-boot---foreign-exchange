package com.example.exchange.paging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.exchange.model.Conversion;
import com.example.exchange.repo.ConversionRepository;

public class PagingService implements IPagingService {

	@Autowired
	private ConversionRepository repository;
	
	@Override
	public List<Conversion> findPaginated(int pageNo) {
		Pageable paging = PageRequest.of(pageNo, 2);
        Page<Conversion> pagedResult = repository.findAll(paging);

        return pagedResult.toList();
	}

}