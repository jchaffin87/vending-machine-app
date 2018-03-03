package com.jchaffin.vendendingmachine;

import java.math.BigDecimal;

public class Cola implements Item {

	private BigDecimal price = new BigDecimal("1.00");
	private String type = "cola";

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
