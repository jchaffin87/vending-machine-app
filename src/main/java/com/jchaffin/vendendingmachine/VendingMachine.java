package com.jchaffin.vendendingmachine;

public class VendingMachine {

	public String idCoin(Coin coin) {
		if (coin.getSize() == 3 && coin.getWeight() == 3) {
			return "nickle";
		}
		if (coin.getSize() == 1 && coin.getWeight() == 1) {
			return "dime";
		}
		return "quarter";
	}

}
