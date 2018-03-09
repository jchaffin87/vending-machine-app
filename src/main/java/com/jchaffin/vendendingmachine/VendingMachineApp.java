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

			BigDecimal moneyInserted = myMachine.calcMoneyInHold();
			boolean exactChangeNeeded = myMachine.checkExactChangeNeeded();

			if (moneyInserted.equals(new BigDecimal("0.00")) && exactChangeNeeded) {
				System.out.println("EXACT CHANGE ONLY");
			} else if (moneyInserted.equals(new BigDecimal("0.00"))) {
				System.out.println("INSERT COIN");
			} else {
				System.out.println("$" + moneyInserted);
			}

			userResponse = input.next();

		}

	}

}
