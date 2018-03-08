package com.jchaffin.vendendingmachine;

import java.math.BigDecimal;
import java.util.ArrayList;

public class VendingMachine {

	ArrayList<Coin> coinHold = new ArrayList<Coin>();
	ArrayList<Coin> coinReturn = new ArrayList<Coin>();
	ArrayList<Coin> bank = new ArrayList<Coin>();
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
		} else {
			coinReturn.add(coin);
		}
	}

	public BigDecimal calcMoneyInHold() {
		BigDecimal moneyInHold = new BigDecimal("0.00");
		for (Coin coin : coinHold) {
			String coinType = idCoin(coin);
			if (coinType.equals("quarter")) {
				moneyInHold = moneyInHold.add(new BigDecimal("0.25"));
			}
			if (coinType.equals("nickel")) {
				moneyInHold = moneyInHold.add(new BigDecimal("0.05"));
			}
			if (coinType.equals("dime")) {
				moneyInHold = moneyInHold.add(new BigDecimal("0.10"));
			}
		}
		return moneyInHold;
	}

	public void stockItem(Item newItem) {
		stock.add(newItem);
	}

	public boolean checkStock(String itemType) {
		boolean inStock = false;
		for (Item item : stock) {
			if (item.getType().equals(itemType)) {
				inStock = true;
			}
		}
		return inStock;
	}

	public boolean checkSufficientFunds(Item selectedItem) {
		boolean sufficientFunds = false;
		if (selectedItem.getPrice().compareTo(calcMoneyInHold()) <= 0) {
			sufficientFunds = true;
		}
		return sufficientFunds;
	}

	public void dispenseItem(Item item) {
		stock.remove(item);
	}

	public void bankCoins() {
		for (Coin coin : coinHold) {
			bank.add(coin);
		}
		coinHold.clear();
	}

	public BigDecimal makeChange(Item chosenItem) {
		BigDecimal change = calcMoneyInHold().subtract(chosenItem.getPrice());
		return change;
	}

	public BigDecimal calcNumOfQsInChange(Item chosenItem) {
		BigDecimal numOfQsInChange = makeChange(chosenItem).divideToIntegralValue(new BigDecimal("0.25"));
		return numOfQsInChange;
	}

	public BigDecimal calcNumOfDsInChange(Item chosenItem) {
		BigDecimal changeMinusQuarters = makeChange(chosenItem)
				.subtract(calcNumOfQsInChange(chosenItem).multiply(new BigDecimal("0.25")));
		BigDecimal numOfDsInChange = changeMinusQuarters.divideToIntegralValue(new BigDecimal("0.10"));
		return numOfDsInChange;
	}

	public BigDecimal calcNumOfNsInChange(Item chosenItem) {
		BigDecimal changeMinusQuarters = makeChange(chosenItem)
				.subtract(calcNumOfQsInChange(chosenItem).multiply(new BigDecimal("0.25")));
		BigDecimal changeMinusQuartersAndDimes = changeMinusQuarters
				.subtract(calcNumOfDsInChange(chosenItem).multiply(new BigDecimal("0.10")));
		BigDecimal numOfNsInChange = changeMinusQuartersAndDimes.divideToIntegralValue(new BigDecimal("0.05"));
		return numOfNsInChange;
	}

	public void returnChange(Item chosenItem) {
		BigDecimal numOfQuartersInChange = calcNumOfQsInChange(chosenItem);
		BigDecimal numOfDimesInChange = calcNumOfDsInChange(chosenItem);
		BigDecimal numOfNickelsInChange = calcNumOfNsInChange(chosenItem);

		for (int i = 0; i <= numOfQuartersInChange.intValue(); i++) {
			for (Coin coin : bank) {
				String coinType = idCoin(coin);
				if (coinType.equals("quarter")) {
					coinReturn.add(coin);
					bank.remove(coin);
					break;
				}
			}
		}
		for (int i = 0; i <= numOfDimesInChange.intValue(); i++) {
			for (Coin coin : bank) {
				String coinType = idCoin(coin);
				if (coinType.equals("dime")) {
					coinReturn.add(coin);
					bank.remove(coin);
					break;
				}
			}
		}
		for (int i = 0; i <= numOfNickelsInChange.intValue(); i++) {
			for (Coin coin : bank) {
				String coinType = idCoin(coin);
				if (coinType.equals("nickel")) {
					coinReturn.add(coin);
					bank.remove(coin);
					break;
				}
			}
		}
	}

	public int countQuartersInBank() {
		int countQuarters = 0;
		for (Coin coin : bank) {
			String coinType = idCoin(coin);
			if (coinType.equals("quarter")) {
				countQuarters++;
			}
		}
		return countQuarters;
	}

	public int countNickelsInBank() {
		int countNickels = 0;
		for (Coin coin : bank) {
			String coinType = idCoin(coin);
			if (coinType.equals("nickel")) {
				countNickels++;
			}
		}
		return countNickels;
	}

	public int countDimesInBank() {
		int countDimes = 0;
		for (Coin coin : bank) {
			String coinType = idCoin(coin);
			if (coinType.equals("dime")) {
				countDimes++;
			}
		}
		return countDimes;
	}

	public boolean checkExactChangeNeeded() {
		int countQuarters = countQuartersInBank();
		int countNickels = countNickelsInBank();
		int countDimes = countDimesInBank();
		boolean exactChangeNeeded = false;
		if (countQuarters < 5 || countNickels < 5 || countDimes < 5) {
			exactChangeNeeded = true;
		}
		return exactChangeNeeded;
	}

}
