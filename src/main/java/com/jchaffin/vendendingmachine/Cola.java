package com.jchaffin.vendendingmachine;

public class Cola implements Item {

	private double price = 1.00;
	private String type = "cola";

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
