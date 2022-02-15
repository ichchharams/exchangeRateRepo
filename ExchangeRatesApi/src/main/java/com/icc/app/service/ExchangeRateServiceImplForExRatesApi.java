package com.icc.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.icc.app.entity.ExchangeRate;
import com.icc.app.repo.ExchangeRateRepository;

@Service
public class ExchangeRateServiceImplForExRatesApi implements ExchangeRateService {
	@Autowired
	ExchangeRateRepository exchangeRateRepository;

	@Autowired
	RestTemplate restTemplate;

	@Value("${exchange.rates.api.base.url}")
	private String baseUrl;

	@Value("${exchange.rates.api.key}")
	private String apiKey;

	@Value("${exchange.rates.api.base.currency}")
	private String baseCurrency;

	@Value("${exchange.rates.api.symbols}")
	private String symbols;

	public String loadsHistoricalExchangeRateData() {

		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		LocalDate date = LocalDate.now().minusMonths(11).withDayOfMonth(1);
		for (int i = 1; i <= 12; i++) {
			String url = baseUrl + date + "?access_key=" + apiKey + "&base=" + baseCurrency + "&symbols=" + symbols;
			ExchangeRate er = restTemplate.getForObject(url, ExchangeRate.class);
			exchangeRateList.add(er);
			date = date.plusMonths(1);
		}
		exchangeRateRepository.saveAll((Iterable<ExchangeRate>) exchangeRateList);
		return "Data loaded succesfully";

	}

	public Iterable<ExchangeRate> getAllExchangeRatesFromDate(LocalDate fromDate) {
		return exchangeRateRepository.findByDateBetween(fromDate, LocalDate.now());

	}

}
