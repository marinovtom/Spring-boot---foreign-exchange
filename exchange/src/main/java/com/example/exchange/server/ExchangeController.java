package com.example.exchange.server;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchange.exception.ApiRequestException;
import com.example.exchange.model.Conversion;
import com.example.exchange.model.ConversionResponse;
import com.example.exchange.model.CurrencyPair;
import com.example.exchange.model.Exchange;
import com.example.exchange.openfeign.CurrencyPairClient;
import com.example.exchange.repo.ConversionRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExchangeController {
	
	@Autowired
	private final CurrencyPairClient client;
	
	@Autowired
	private ConversionRepository repository;
	
	public ExchangeController(CurrencyPairClient client)
	{
		this.client = client;
	}
	
	private String parameterMessage(String value)
	{
		return String.format("Parameter %s cannot be empty!", value);
	}
	
	@GetMapping("/exchange")
	public Exchange status(@RequestParam(required=false) String base, @RequestParam(required=false) String exchangeString)
	{
		if(base == null && exchangeString == null)
		{
			throw new ApiRequestException("/exchange must have 2 parameters (base, exchangeString)!");
		}
		if(base == null)
		{
			throw new ApiRequestException(parameterMessage("base"));
		}
		if(exchangeString == null)
		{
			throw new ApiRequestException(parameterMessage("exchangeString"));
		}
		
		CurrencyPair pair;
		try {
			pair = client.findCurrencyPair(base, exchangeString);
		} catch(Exception e)
		{
			throw new ApiRequestException(e.getMessage());
		}
		Exchange exchange = new Exchange();
		exchange.setExchangeRate(pair.getRate().getRate());
		
		return exchange;
	}
	
	@GetMapping("/exchange/convert")
	public ConversionResponse statusConvert(@RequestParam(required=false) Double amount, @RequestParam(required=false) String base, @RequestParam(required=false) String exchangeString)
	{
		if(amount == null && base == null && exchangeString == null)
		{
			throw new ApiRequestException("/exchange must have 3 parameters (amount, base, exchangeString)!");
		}
		if(amount == null)
		{
			throw new ApiRequestException(parameterMessage("amount"));
		}
		if(base == null)
		{
			throw new ApiRequestException(parameterMessage("base"));
		}
		if(exchangeString == null)
		{
			throw new ApiRequestException(parameterMessage("exchangeString"));
		}
		
		CurrencyPair pair;
		try {
			pair = client.findCurrencyPair(base, exchangeString);
		} catch(Exception e) {
			throw new ApiRequestException(e.getMessage());
		}
		
		Exchange exchange = new Exchange();
		exchange.setExchangeRate(pair.getRate().getRate() * amount);
		Conversion conversion = new Conversion(new Date(), base, exchangeString, exchange.getExchangeRate());
		//long v = ThreadLocalRandom.current().nextLong(1400000000);
		String uniqueID = UUID.randomUUID().toString();
		conversion.setTransactionId(uniqueID);
		
		repository.save(conversion);
		
		ConversionResponse response = new ConversionResponse(conversion.getTransactionId(), conversion.getTargetAmount());
		
		return response;
	}
}
