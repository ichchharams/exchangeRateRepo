package com.icc.app.controller;

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
import org.springframework.boot.test.context.TestComponent;

import com.icc.app.entity.ExchangeRate;
import com.icc.app.service.ExchangeRateService;

@TestComponent
public class ExchangeRatesControllerTest {

	@InjectMocks
	ExchangeRatesController exchangeRatesController;

	@Mock
	ExchangeRateService exchangeRateServiceImplForFrxRates;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void loadExchangeRatesDataTest() {
		when(exchangeRateServiceImplForFrxRates.loadsHistoricalExchangeRateData())
				.thenReturn("Data loaded succesfully");
		assertEquals(exchangeRatesController.loadExchangeRatesData(), "Data loaded succesfully");

	}

	@Test
	public void getAllExchangeRatesDataFromDateTest() {
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		when(exchangeRateServiceImplForFrxRates
				.getAllExchangeRatesFromDate(LocalDate.now().minusMonths(6).withDayOfMonth(1)))
						.thenReturn(exchangeRateList);
		assertEquals(exchangeRatesController
				.getAllExchangeRatesDataFromDate(LocalDate.now().minusMonths(6).withDayOfMonth(1)), exchangeRateList);

	}

}
