package com.example.exchange.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPair {
	
	@JsonProperty("rates")
	private Rate rate;
	
	@JsonProperty("base")
	private String base;
	
	@JsonProperty("date")
	private Date date;

	@JsonProperty("rates")
	public Rate getRate() {
		return rate;
	}

	@JsonProperty("rates")
	public void setRate(Rate rate) {
		this.rate = rate;
	}

	@JsonProperty("base")
	public String getBase() {
		return base;
	}

	@JsonProperty("base")
	public void setBase(String base) {
		this.base = base;
	}

	@JsonProperty("date")
	public Date getDate() {
		return date;
	}

	@JsonProperty("date")
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
