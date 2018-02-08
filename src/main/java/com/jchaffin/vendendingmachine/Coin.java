package com.jchaffin.vendendingmachine;

public class Coin {

	private int weight;
	private int size;

	public Coin(int weight, int size) {
		this.setWeight(weight);
		this.setSize(size);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
