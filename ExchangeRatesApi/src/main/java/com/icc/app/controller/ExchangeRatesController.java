package com.icc.app.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.icc.app.entity.ExchangeRate;
import com.icc.app.service.ExchangeRateService;

@RestController
public class ExchangeRatesController {
	@Autowired
	ExchangeRateService exchangeRateService;

	/*
	 * load last 12 months 1st date exchange rates data into in memory database
	 */
	@GetMapping(value = "/load")
	String loadExchangeRatesData() {
		return exchangeRateService.loadsHistoricalExchangeRateData();

	}

	/*
	 * get all months 1st date exchange rates data from give date to current
	 * month
	 */
	@GetMapping(value = "/all/{fromDate}")
	Iterable<ExchangeRate> getAllExchangeRatesDataFromDate(
			@PathVariable(name = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate) {
		return exchangeRateService.getAllExchangeRatesFromDate(fromDate);

	}

}
