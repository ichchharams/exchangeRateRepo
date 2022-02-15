package com.icc.app.service;

import java.time.LocalDate;

import com.icc.app.entity.ExchangeRate;

public interface ExchangeRateService {
	public String loadsHistoricalExchangeRateData();

	public Iterable<ExchangeRate> getAllExchangeRatesFromDate(LocalDate fromDate);
}
