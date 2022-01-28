package com.cg.nsa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.UniqueConstraint;

import org.checkerframework.common.aliasing.qual.Unique;

@Entity
public class Officer extends UserDetails{
	
	private String name;
	@Column(unique = true)
	private String state;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
