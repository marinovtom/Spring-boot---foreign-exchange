package com.example.exchange.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchange.model.CurrencyPair;
import com.example.exchange.model.Exchange;
import com.example.exchange.openfeign.CurrencyPairClient;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExchangeController {
	
	@Autowired
	private final CurrencyPairClient client;
	
	public ExchangeController(CurrencyPairClient client)
	{
		this.client = client;
	}
	
	@GetMapping("/exchange")
	public Exchange status(@RequestParam(required=false) String base, @RequestParam(required=false) String exchangeString)
	{
		//if(one == null) throw new ApiRequestException("Parameter one cannot be empty!");
		//if(two == null) throw new ApiRequestException("Parameter two cannot be empty!");
		CurrencyPair pair;
		//try {
			pair = client.findCurrencyPair(base, exchangeString);
		//} catch(Exception e)
		//{
		//	throw new ApiRequestException(e.getMessage());
		//}
		Exchange exchange = new Exchange();
		exchange.setExchangeRate(pair.getRate().getRate());
		
		return exchange;
	}
}
