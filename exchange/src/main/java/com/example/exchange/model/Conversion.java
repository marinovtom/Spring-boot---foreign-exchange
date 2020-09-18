package com.example.exchange.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Table(name = "Conversion")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conversion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long transactionId;
	
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	private String sourceCurrency;
	private String targetCurrency;
	private Double targetAmount;
	
	public Conversion() {}
	
	public Conversion(Date date, String sourceCurrency, String targetCurrency, Double targetAmount)
	{
		this.transactionDate = date;
		this.sourceCurrency = sourceCurrency;
		this.targetCurrency = targetCurrency;
		this.targetAmount = targetAmount;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public Double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(Double targetAmount) {
		this.targetAmount = targetAmount;
	}
	
}
