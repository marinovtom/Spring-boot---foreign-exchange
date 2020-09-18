package com.example.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConversionResponse {

	private Long transactionId;
	private Double targetAmount;
	
	public ConversionResponse(Long id, Double amount)
	{
		this.transactionId = id;
		this.targetAmount = amount;
	}
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Double getTargetAmount() {
		return targetAmount;
	}
	public void setTargetAmount(Double targetAmount) {
		this.targetAmount = targetAmount;
	}
	
	
}
