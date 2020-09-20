package com.example.exchange.repo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.exchange.model.Conversion;

@Repository
public interface ConversionRepository extends PagingAndSortingRepository<Conversion, Long> {
	Conversion findByTransactionId(String transactionId);
	
	Page<Conversion> findByTransactionDate(Date transactionDate, Pageable pageable);
}
