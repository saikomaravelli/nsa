package com.cg.nsa.entity;

import javax.persistence.Entity;

@Entity
public class Ministry extends UserDetails {
	
	private String portfolio;

	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

}