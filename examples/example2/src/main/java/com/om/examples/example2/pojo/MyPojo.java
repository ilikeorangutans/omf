package com.om.examples.example2.pojo;

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
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Id
	@Property
	private String id;

	@Property
	private double rate;

	@Property
	private int count;

}
