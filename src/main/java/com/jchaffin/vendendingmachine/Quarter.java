package com.jchaffin.vendendingmachine;

public class Quarter implements Coin {

	private int weight = 4;
	private int size = 4;

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
