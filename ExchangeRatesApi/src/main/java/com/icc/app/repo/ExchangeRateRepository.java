package com.icc.app.repo;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import com.icc.app.entity.ExchangeRate;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, LocalDate> {

	Iterable<ExchangeRate> findByDateBetween(LocalDate dateFrom, LocalDate today);

}
