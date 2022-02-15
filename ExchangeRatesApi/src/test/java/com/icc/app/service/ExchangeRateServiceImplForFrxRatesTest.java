package com.icc.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.web.client.RestTemplate;

import com.icc.app.entity.ExchangeRate;
import com.icc.app.entity.Rates;
import com.icc.app.repo.ExchangeRateRepository;

@TestComponent
public class ExchangeRateServiceImplForFrxRatesTest {
	@InjectMocks
	ExchangeRateServiceImplForExRatesApi exchangeRateServiceImplForFrxRates;

	@Mock
	ExchangeRateRepository exchangeRateRepository;

	@Mock
	RestTemplate restTemplate;

	@Value("${exchange.rates.api.base.url}")
	private String baseUrl;

	@Value("${exchange.rates.api.key}")
	private String apiKey;

	@Value("${exchange.rates.api.base.currency}")
	private String baseCurrency;

	@Value("${exchange.rates.api.symbols})")
	private String symbols;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void loadsHistoricalExchangeRateDataTest() {
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		LocalDate date = LocalDate.now().minusMonths(11).withDayOfMonth(1);
		for (int i = 1; i <= 12; i++) {
			String url = baseUrl + date + "?access_key=" + apiKey + "&base=" + baseCurrency + "&symbols=" + symbols;
			when(restTemplate.getForObject(url, ExchangeRate.class))
					.thenReturn(new ExchangeRate(LocalDate.now(), new Rates()));
			exchangeRateList.add(new ExchangeRate(LocalDate.now(), new Rates()));
		}
		when(exchangeRateRepository.saveAll(exchangeRateList)).thenReturn(exchangeRateList);
		assertEquals(exchangeRateServiceImplForFrxRates.loadsHistoricalExchangeRateData(), "Data loaded succesfully");
	}

	@Test
	public void getAllExchangeRatesFromDateTest() {
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		when(exchangeRateRepository.findByDateBetween(LocalDate.now().minusMonths(6).withDayOfMonth(1),
				LocalDate.now())).thenReturn(exchangeRateList);
		assertEquals(exchangeRateServiceImplForFrxRates
				.getAllExchangeRatesFromDate(LocalDate.now().minusMonths(6).withDayOfMonth(1)), exchangeRateList);

	}
}
