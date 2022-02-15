package com.icc.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(value = { "id" })
public class Rates {
	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@JsonProperty("GBP")
	private double gbp;
	@JsonProperty("USD")
	private double usd;
	@JsonProperty("HKD")
	private double hkd;

	public Rates() {
	}

	public Rates(int id, double gbp, double usd, double hkd) {
		super();
		this.id = id;
		this.gbp = gbp;
		this.usd = usd;
		this.hkd = hkd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getGbp() {
		return gbp;
	}

	public void setGbp(double gbp) {
		this.gbp = gbp;
	}

	public double getUsd() {
		return usd;
	}

	public void setUsd(double usd) {
		this.usd = usd;
	}

	public double getHkd() {
		return hkd;
	}

	public void setHkd(double hkd) {
		this.hkd = hkd;
	}

	@Override
	public String toString() {
		return "Rates [id=" + id + ", gbp=" + gbp + ", usd=" + usd + ", hkd=" + hkd + "]";
	}

}