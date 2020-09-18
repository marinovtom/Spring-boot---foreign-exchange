package com.example.exchange.server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchange.exception.ApiRequestException;
import com.example.exchange.model.Conversion;
import com.example.exchange.repo.ConversionRepository;
import com.sun.el.parser.ParseException;

@RestController
public class ListController {

	@Autowired
	private ConversionRepository repository;
	
	@GetMapping("/exchange/list")
	public List<Conversion> getConversionListFor(@RequestParam(required=false) Long id, @RequestParam(required=false) String date, @RequestParam(required=false, defaultValue="0") int pageNo) throws java.text.ParseException
	{
		Pageable pageWithTwoElements = PageRequest.of(pageNo, 2); // pageSize = 2 for easier test
		
		if(id != null)
		{
			List<Conversion> list = new ArrayList<Conversion>();
			Optional<Conversion> optional = repository.findById(id);
			list.add(optional.get());
			
			return list;
		}
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCurrent;
		dateCurrent = formatter.parse(date);
		try {
			dateCurrent = formatter.parse(date);
		} catch(Exception e)
		{
			throw new ApiRequestException(e.getMessage() + ". Date must be in format: dd/MM/yyyy");
		}
		
		
		List<Conversion> list = new ArrayList<Conversion>();
		for(Conversion c : repository.findByTransactionDate(dateCurrent, pageWithTwoElements))
		{
			list.add(c);
		}
		
		return list;
	}
}
