package com.jchaffin.vendendingmachine;

import java.math.BigDecimal;

public class Candy implements Item {

	private BigDecimal price = new BigDecimal("0.65");
	private String type = "candy";

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public BigDecimal getPrice() {
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
