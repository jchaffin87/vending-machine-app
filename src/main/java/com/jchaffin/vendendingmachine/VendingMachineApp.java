package com.jchaffin.vendendingmachine;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineApp {

	public static Scanner input = new Scanner(System.in);
	public static String userResponse;

	public static void main(String[] args) {

		VendingMachine myMachine = new VendingMachine();

		Quarter setUpQuarter = new Quarter();
		myMachine.acceptCoin(setUpQuarter);
		myMachine.acceptCoin(setUpQuarter);
		myMachine.acceptCoin(setUpQuarter);
		myMachine.acceptCoin(setUpQuarter);
		myMachine.acceptCoin(setUpQuarter);
		Nickel setUpNickel = new Nickel();
		myMachine.acceptCoin(setUpNickel);
		myMachine.acceptCoin(setUpNickel);
		myMachine.acceptCoin(setUpNickel);
		myMachine.acceptCoin(setUpNickel);
		myMachine.acceptCoin(setUpNickel);
		Dime setUpDime = new Dime();
		myMachine.acceptCoin(setUpDime);
		myMachine.acceptCoin(setUpDime);
		myMachine.acceptCoin(setUpDime);
		myMachine.acceptCoin(setUpDime);
		myMachine.acceptCoin(setUpDime);

		myMachine.bankCoins();

		Cola stockedCola = new Cola();
		myMachine.stockItem(stockedCola);
		myMachine.stockItem(stockedCola);
		Chips stockedChips = new Chips();
		myMachine.stockItem(stockedChips);
		myMachine.stockItem(stockedChips);
		Candy stockedCandy = new Candy();
		myMachine.stockItem(stockedCandy);
		myMachine.stockItem(stockedCandy);

		while (1 < 2) {

			System.out.println("This machine sells Cola for $1.00, Chips for $0.50, and Candy for $0.65.");
			System.out.println("Press C to check the display.");
			System.out.println("Press Q to insert a quarter.");
			System.out.println("Press N to insert a nickel.");
			System.out.println("Press D to insert a dime.");
			System.out.println("Press P to insert a penny");
			System.out.println("Press R to return coins.");
			System.out.println("Press T to take coins/change.");
			System.out.println("Press 1 to choose Cola.");
			System.out.println("Press 2 to choose Chips.");
			System.out.println("Press 3 to choose Candy.");

			userResponse = input.next();

			if (userResponse.equalsIgnoreCase("C")) {
				BigDecimal moneyInserted = myMachine.calcMoneyInHold();
				boolean exactChangeNeeded = myMachine.checkExactChangeNeeded();
				if (moneyInserted.equals(new BigDecimal("0.00")) && exactChangeNeeded) {
					System.out.println("EXACT CHANGE ONLY");
				} else if (moneyInserted.equals(new BigDecimal("0.00"))) {
					System.out.println("INSERT COIN");
				} else {
					System.out.println("$" + moneyInserted);
				}
			} else if (userResponse.equalsIgnoreCase("Q")) {
				Quarter userQuarter = new Quarter();
				myMachine.acceptCoin(userQuarter);
			} else if (userResponse.equalsIgnoreCase("N")) {
				Nickel userNickel = new Nickel();
				myMachine.acceptCoin(userNickel);
			} else if (userResponse.equalsIgnoreCase("D")) {
				Dime userDime = new Dime();
				myMachine.acceptCoin(userDime);
			} else if (userResponse.equalsIgnoreCase("P")) {
				Penny userPenny = new Penny();
				myMachine.acceptCoin(userPenny);
			} else if (userResponse.equalsIgnoreCase("R")) {
				myMachine.returnCoins();
			} else if (userResponse.equalsIgnoreCase("T")) {
				int countQs = 0;
				int countNs = 0;
				int countDs = 0;
				int countPs = 0;
				for (Coin coin : myMachine.coinReturn) {
					String coinType = myMachine.idCoin(coin);
					if (coinType.equals("quarter")) {
						countQs++;
					}
					if (coinType.equals("nickel")) {
						countNs++;
					}
					if (coinType.equals("dime")) {
						countDs++;
					}
					if (coinType.equals("penny")) {
						countPs++;
					}
				}
				System.out.println(countQs + " quarters taken.");
				System.out.println(countNs + " nickels taken.");
				System.out.println(countDs + " dimes taken.");
				System.out.println(countPs + " pennies taken.");
				myMachine.coinReturn.clear();
			} else if (userResponse.equalsIgnoreCase("1")) {
				boolean colaInStock = myMachine.checkStock("cola");
				if (colaInStock) {
					for (Item item : myMachine.stock) {
						if (item.getType().equals("cola")) {
							boolean sufficientFunds = myMachine.checkSufficientFunds(item);
							if (sufficientFunds) {
								myMachine.dispenseItem(item);
								myMachine.returnChange(item);
								myMachine.bankCoins();
								System.out.println("Cola dispensed.");
							} else {
								System.out.println("$" + item.getPrice());
							}
							break;
						}
					}
				} else {
					System.out.println("SOLD OUT");
				}
			} else if (userResponse.equalsIgnoreCase("2")) {
				boolean chipsInStock = myMachine.checkStock("chips");
				if (chipsInStock) {
					for (Item item : myMachine.stock) {
						if (item.getType().equals("chips")) {
							boolean sufficientFunds = myMachine.checkSufficientFunds(item);
							if (sufficientFunds) {
								myMachine.dispenseItem(item);
								myMachine.returnChange(item);
								myMachine.bankCoins();
								System.out.println("Chips dispensed.");
							} else {
								System.out.println("$" + item.getPrice());
							}
							break;
						}
					}
				} else {
					System.out.println("SOLD OUT");
				}
			} else if (userResponse.equalsIgnoreCase("3")) {
				boolean candyInStock = myMachine.checkStock("candy");
				if (candyInStock) {
					for (Item item : myMachine.stock) {
						if (item.getType().equals("candy")) {
							boolean sufficientFunds = myMachine.checkSufficientFunds(item);
							if (sufficientFunds) {
								myMachine.dispenseItem(item);
								myMachine.returnChange(item);
								myMachine.bankCoins();
								System.out.println("Candy dispensed.");
							} else {
								System.out.println("$" + item.getPrice());
							}
							break;
						}
					}
				} else {
					System.out.println("SOLD OUT");
				}
			} else {
				System.out.println("That is not a valid response.");
			}
		}

	}

}
