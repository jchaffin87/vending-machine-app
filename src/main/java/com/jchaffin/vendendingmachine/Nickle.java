package com.jchaffin.vendendingmachine;

public class Nickle implements Coin {

	int weight = 3;
	int size = 3;

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
