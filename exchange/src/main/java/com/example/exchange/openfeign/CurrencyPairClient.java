package com.example.exchange.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.exchange.model.CurrencyPair;

@FeignClient(value="CurrencyPair", url="https://api.exchangeratesapi.io/")
public interface CurrencyPairClient {

	@RequestMapping(value="/latest?base={base}&symbols={exchange}", method = RequestMethod.GET)
	public CurrencyPair findCurrencyPair(@PathVariable("base") String base, @PathVariable("exchange") String exchange);
}
