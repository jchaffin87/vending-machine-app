package com.jchaffin.vendendingmachine;

public class VendingMachine {

	public String idCoin(Coin coin) {
		if (coin.getSize() == 3 && coin.getWeight() == 3) {
			return "nickle";
		}
		return "quarter";
	}

}
