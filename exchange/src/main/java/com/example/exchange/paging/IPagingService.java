package com.example.exchange.paging;

import java.util.List;

import com.example.exchange.model.Conversion;

public interface IPagingService {
	List<Conversion> findPaginated(int pageNo);
}
