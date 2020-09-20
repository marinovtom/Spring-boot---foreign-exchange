package com.example.exchange.repotest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.exchange.model.Conversion;
import com.example.exchange.repo.ConversionRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ConversionRepositoryTest {

	@Autowired
	private ConversionRepository repository;
	
	@Test
	public void findById()
	{
		String uniqueID = UUID.randomUUID().toString();
		
		Conversion conversion = new Conversion();
		conversion.setTransactionId(uniqueID);
		conversion.setSourceCurrency("USD");
		conversion.setTargetCurrency("BGN");
		conversion.setTransactionDate(new Date());
		conversion.setTargetAmount(100.0);
		
		repository.save(conversion);
		
		Conversion savedConversion = repository.findByTransactionId(uniqueID);
		
		assertThat(savedConversion).isNotNull();
		assertThat(savedConversion).isEqualToComparingFieldByField(conversion);
	}
	
	@Test
	public void findByTransactionDate()
	{
		String uniqueID = UUID.randomUUID().toString();
		Date date = new Date();
		
		Conversion conversion = new Conversion();
		conversion.setTransactionId(uniqueID);
		conversion.setSourceCurrency("USD");
		conversion.setTargetCurrency("BGN");
		conversion.setTransactionDate(date);
		conversion.setTargetAmount(101.0);
		
		repository.save(conversion);
		
		Pageable pageWithOneElement = PageRequest.of(0, 1);
		
		List<Conversion> savedConversionList = repository.findByTransactionDate(date, pageWithOneElement).getContent();
		
		assertThat(savedConversionList).isNotNull();
		assertEquals(1, savedConversionList.size());
		
		Conversion savedConversion = savedConversionList.get(0);
		
		assertThat(savedConversion).isNotNull();
		assertThat(savedConversion).isEqualToComparingFieldByField(conversion);
	}
	
	@Test
	public void findByTransactionDatePaging()
	{
		Date date = new Date();
		
		for(int i = 0; i < 3; i++)
		{
			String uniqueID = UUID.randomUUID().toString();
			
			Conversion conversion = new Conversion();
			conversion.setTransactionId(uniqueID);
			conversion.setSourceCurrency("USD");
			conversion.setTargetCurrency("BGN");
			conversion.setTransactionDate(date);
			conversion.setTargetAmount(100.0 + i);
			
			repository.save(conversion);
		}
		
		Pageable pageWithTwoElements = PageRequest.of(0, 2);
		
		List<Conversion> savedConversionList = repository.findByTransactionDate(date, pageWithTwoElements).getContent();
		
		assertThat(savedConversionList).isNotNull();
		assertEquals(2, savedConversionList.size());
		
		pageWithTwoElements = PageRequest.of(1, 2);
		
		savedConversionList = repository.findByTransactionDate(date, pageWithTwoElements).getContent();
		
		assertThat(savedConversionList).isNotNull();
		assertEquals(1, savedConversionList.size());
	}
}
