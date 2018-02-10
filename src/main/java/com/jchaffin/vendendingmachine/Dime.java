package com.jchaffin.vendendingmachine;

public class Dime implements Coin {

	private int weight = 1;
	private int size = 1;

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public int getSize() {
		return size;
	}

}
