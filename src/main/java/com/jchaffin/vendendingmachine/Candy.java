package com.jchaffin.vendendingmachine;

public class Candy implements Item {

	private double price = 0.65;
	private String type = "candy";

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
