package com.om.examples.example4.pojo;

import org.om.core.api.annotation.Entity;
import org.om.core.api.annotation.Id;
import org.om.core.api.annotation.Property;

/**
 * 
 * @author tome
 * 
 */
@Entity
public class MyPojo {
	@Id
	@Property
	private String id;

	@Property
	private double rate;

	@Property
	private int count;

	public int getCount() {
		return count;
	}

	public String getId() {
		return id;
	}

	public double getRate() {
		return rate;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
