package com.jchaffin.vendendingmachine;

public class Chips implements Item {

	private double price = 0.50;
	private String type = "chips";

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}

}
