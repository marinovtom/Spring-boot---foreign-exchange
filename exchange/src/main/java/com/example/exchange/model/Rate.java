package com.example.exchange.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
	
	@JsonAlias({"GBP", "USD", "EUR", "BGN", "JPY", "AUD", "CAD", "TRY", "CZK"}) // should be all currencies
	private double rate;

	@JsonAlias({"GBP", "USD", "EUR", "BGN", "JPY", "AUD", "CAD", "TRY", "CZK"})
	public double getRate() {
		return rate;
	}

	@JsonAlias({"GBP", "USD", "EUR", "BGN", "JPY", "AUD", "CAD", "TRY", "CZK"})
	public void setRate(double rate) {
		this.rate = rate;
	}
}
