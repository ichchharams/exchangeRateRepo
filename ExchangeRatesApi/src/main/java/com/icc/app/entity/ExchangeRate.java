package com.icc.app.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRate {
	@Id
	private LocalDate date;
	@OneToOne(cascade = { CascadeType.ALL })
	private Rates rates;

	public ExchangeRate() {
		super();
	}

	public ExchangeRate(LocalDate date, Rates rates) {
		super();
		this.date = date;
		this.rates = rates;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Rates getRates() {
		return rates;
	}

	public void setRates(Rates rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "ExchangeRate [date=" + date + ", rates=" + rates + "]";
	}

}