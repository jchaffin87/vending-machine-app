package com.jchaffin.vendendingmachine;

import java.util.ArrayList;

public class VendingMachine {

	ArrayList<Coin> coinHold = new ArrayList<Coin>();
	ArrayList<Coin> coinReturn = new ArrayList<Coin>();
	ArrayList<Item> stock = new ArrayList<Item>();

	public String idCoin(Coin coin) {
		if (coin.getSize() == 4 && coin.getWeight() == 4) {
			return "quarter";
		}
		if (coin.getSize() == 3 && coin.getWeight() == 3) {
			return "nickel";
		}
		if (coin.getSize() == 2 && coin.getWeight() == 2) {
			return "penny";
		}
		if (coin.getSize() == 1 && coin.getWeight() == 1) {
			return "dime";
		}
		return "";
	}

	public void acceptCoin(Coin coin) {
		String coinType = idCoin(coin);
		if (coinType.equals("quarter") || coinType.equals("nickel") || coinType.equals("dime")) {
			coinHold.add(coin);
		}
		coinReturn.add(coin);
	}

	public double calcMoneyInHold() {
		double moneyInHold = 0;
		for (Coin coin : coinHold) {
			String coinType = idCoin(coin);
			if (coinType.equals("quarter")) {
				moneyInHold += 0.25;
			}
			if (coinType.equals("nickel")) {
				moneyInHold += 0.05;
			}
			if (coinType.equals("dime")) {
				moneyInHold += 0.10;
			}
		}
		return moneyInHold;
	}

	public void stockItem(Item newItem) {
		stock.add(newItem);
	}

	public boolean checkStock(String itemType) {
		return true;
	}

}
