package com.jchaffin.vendendingmachine;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	VendingMachine underTest;

	@Before
	public void setUp() {
		underTest = new VendingMachine();
	}

	@Test
	public void whenIdCoinIsPassedAQuarterItReturnsQuarter() {
		Quarter newQuarter = new Quarter();
		assertEquals("quarter", underTest.idCoin(newQuarter));
	}

	@Test
	public void whenIdCoinIsPassedANickleItReturnsNickel() {
		Nickel newNickle = new Nickel();
		assertEquals("nickel", underTest.idCoin(newNickle));
	}

	@Test
	public void whenIdCoinIsPassedADimeItReturnsDime() {
		Dime newDime = new Dime();
		assertEquals("dime", underTest.idCoin(newDime));
	}

	@Test
	public void whenIdCoinIsPassedAPennyItReturnsPenny() {
		Penny newPenny = new Penny();
		assertEquals("penny", underTest.idCoin(newPenny));
	}

	@Test
	public void acceptCoinPutsCoinInCoinHold() {
		Quarter newCoin = new Quarter();
		underTest.idCoin(newCoin);
		underTest.acceptCoin(newCoin);
		assertEquals(true, underTest.coinHold.contains(newCoin));
	}

	@Test
	public void acceptCoinPutsCoinInHoldIfItIsAQuarter() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertEquals(true, underTest.coinHold.contains(newQuarter));
		assertEquals(false, underTest.coinHold.contains(newPenny));
	}

	@Test
	public void acceptCoinPutsCoinInHoldIfItIsANickel() {
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertEquals(true, underTest.coinHold.contains(newNickel));
		assertEquals(false, underTest.coinHold.contains(newPenny));
	}

	@Test
	public void acceptCoinPutsCoinInHoldIfItIsADime() {
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertEquals(true, underTest.coinHold.contains(newDime));
		assertEquals(false, underTest.coinHold.contains(newPenny));
	}

	@Test
	public void acceptCoinPutsCoinInCoinReturnIfItsAPenny() {
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertEquals(true, underTest.coinReturn.contains(newPenny));
	}

	@Test
	public void calcMoneyInHoldReturnsZeroPointTwoFiveWhenAcceptCoinIsPassedOneQuarter() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		assertEquals(new BigDecimal("0.25"), underTest.calcMoneyInHold());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroPointFiveWhenAcceptCoinIsPassedTwoQuarters() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		assertEquals(new BigDecimal("0.50"), underTest.calcMoneyInHold());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroPointThreeWhenAcceptCoinIsPassedOneQuarterAndOneNickel() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		assertEquals(new BigDecimal("0.30"), underTest.calcMoneyInHold());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroPointThreeFiveWhenAcceptCoinIsPassedOneQuarterAndOneDime() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		assertEquals(new BigDecimal("0.35"), underTest.calcMoneyInHold());
	}

	@Test
	public void stockItemAddsColaToStock() {
		Cola newCola = new Cola();
		underTest.stockItem(newCola);
		assertEquals(true, underTest.stock.contains(newCola));
	}

	@Test
	public void stockItemAddsChipsToStock() {
		Chips newChips = new Chips();
		underTest.stockItem(newChips);
		assertEquals(true, underTest.stock.contains(newChips));
	}

	@Test
	public void stockItemAddsCandyToStock() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		assertEquals(true, underTest.stock.contains(newCandy));
	}

	@Test
	public void checkStockReturnsTrueWhenPassedCandyAndCandyItemIsStocked() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		assertEquals(true, underTest.checkStock("candy"));
	}

	@Test
	public void checkStockReturnsFalseWhenPassedColaAndOnlyCandyItemIsStocked() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		assertEquals(false, underTest.checkStock("cola"));
	}

	@Test
	public void checkStockReturnsTrueWhenPassedCandyAndBothCandyItemAndColaItemsAreStocked() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		Cola newCola = new Cola();
		underTest.stockItem(newCola);
		assertEquals(true, underTest.checkStock("candy"));
	}

	@Test
	public void checkStockReturnsTrueBothWhenPassedCandyAndWhenPassedColaWhenCandyItemAndColaItemAreStocked() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		Cola newCola = new Cola();
		underTest.stockItem(newCola);
		assertEquals(true, underTest.checkStock("candy"));
		assertEquals(true, underTest.checkStock("cola"));
	}

	@Test
	public void checkStockReturnsTrueBothWhenPassedCandyAndWhenPassedColaButFalseWhenPassedChipsWhenCandyItemAndColaItemAreStocked() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		Cola newCola = new Cola();
		underTest.stockItem(newCola);
		assertEquals(true, underTest.checkStock("candy"));
		assertEquals(true, underTest.checkStock("cola"));
		assertEquals(false, underTest.checkStock("chips"));
	}

	@Test
	public void checkSufficientFundsReturnsTrueWhenPassedChipsItemAndTwoQuartersAreAccepted() {
		Chips newChips = new Chips();
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		assertEquals(true, underTest.checkSufficientFunds(newChips));
	}

	@Test
	public void checkSufficientFundsReturnsFalseWhenPassedChipsItemAndOneQuarterIsAccepted() {
		Chips newChips = new Chips();
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		assertEquals(false, underTest.checkSufficientFunds(newChips));
	}

	@Test
	public void checkSufficientFundsReturnsTrueWhenPassedChipsItemAndThreeQuartersAreAccepted() {
		Chips newChips = new Chips();
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		assertEquals(true, underTest.checkSufficientFunds(newChips));
	}

	@Test
	public void checkStockReturnsFalseWhenColaItemIsStockedAndThenDispenseItemIsPassedThatColaItem() {
		Cola newCola = new Cola();
		underTest.stockItem(newCola);
		underTest.dispenseItem(newCola);
		assertEquals(false, underTest.checkStock(newCola.getType()));
	}

	@Test
	public void checkStockReturnsFalseWhenChipsItemIsStockedAndThenDispenseItemIsPassedThatChipsItem() {
		Chips newChips = new Chips();
		underTest.stockItem(newChips);
		underTest.dispenseItem(newChips);
		assertEquals(false, underTest.checkStock(newChips.getType()));
	}

	@Test
	public void calcMoneyInHoldReturnsZeroWhenOneQuarterIsAcceptedAndBankCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.bankCoins();
		assertEquals(new BigDecimal("0.00"), underTest.calcMoneyInHold());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroAndCoinsInHoldArePutInBankWhenOneQuarterIsAcceptedAndBankCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.bankCoins();
		assertEquals(new BigDecimal("0.00"), underTest.calcMoneyInHold());
		assertEquals(true, underTest.bank.contains(newQuarter));
	}

	@Test
	public void calcMoneyInHoldReturnsZeroAndCoinsInHoldArePutInBankWhenOneQuarterAndOneDimeIsAcceptedAndBankCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.bankCoins();
		assertEquals(new BigDecimal("0.00"), underTest.calcMoneyInHold());
		assertEquals(true, underTest.bank.contains(newQuarter));
		assertEquals(true, underTest.bank.contains(newDime));
	}

	@Test
	public void makeChangeReturnsZeroPointTwoFiveWhenThreeQuartersAreAcceptedAndItsPassedAChipsItem() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("0.25"), underTest.makeChange(newChips));
	}

	@Test
	public void makeChangeReturnsZeroPointZeroFiveWhenTwoQuartersAndTwoDimesAreAcceptedAndItsPassedACandyItem() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.acceptCoin(newDime);
		Candy newCandy = new Candy();
		assertEquals(new BigDecimal("0.05"), underTest.makeChange(newCandy));
	}

	@Test
	public void calcNumOfQsInChangeReturnsOneWhenChangeEqualsZeroPointTwoFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("1"), underTest.calcNumOfQsInChange(newChips));
	}

	@Test
	public void calcNumOfQsInChangeReturnsTwoWhenChangeEqualsZeroPointFiveZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("2"), underTest.calcNumOfQsInChange(newChips));
	}

	@Test
	public void calcNumOfQsInChangeReturnsThreeWhenChangeEqualsZeroPointSevenFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("3"), underTest.calcNumOfQsInChange(newChips));
	}

	@Test
	public void calcNumOfQsInChangeReturnsThreeWhenChangeEqualsZeroPointNineZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("3"), underTest.calcNumOfQsInChange(newChips));
	}

	@Test
	public void calcNumOfQsInChangeReturnsZeroWhenChangeEqualsZeroPointOneZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("0"), underTest.calcNumOfQsInChange(newChips));
	}

	@Test
	public void calcNumOfDsInChangeReturnsOneWhenChangeEqualsZeroPointOneZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("1"), underTest.calcNumOfDsInChange(newChips));
	}

	@Test
	public void calcNumOfDsInChangeReturnsTwoWhenChangeEqualsZeroPointTwoZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.acceptCoin(newDime);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("2"), underTest.calcNumOfDsInChange(newChips));
	}

	@Test
	public void calcNumOfDsInChangeReturnsOneWhenChangeEqualsZeroPointThreeFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("1"), underTest.calcNumOfDsInChange(newChips));
	}

	@Test
	public void calcNumOfDsInChangeReturnsOneWhenChangeEqualsZeroPointSixFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("1"), underTest.calcNumOfDsInChange(newChips));
	}

	@Test
	public void calcNumOfDsInChangeReturnsZeroWhenChangeEqualsZeroPointZeroFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("0"), underTest.calcNumOfDsInChange(newChips));
	}

	@Test
	public void calcNumOfNsInChangeReturnsOneWhenChangeEqualsZeroPointZeroFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("1"), underTest.calcNumOfNsInChange(newChips));
	}

	@Test
	public void calcNumOfNsInChangeReturnsZeroWhenChangeEqualsZeroPointTwoFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("0"), underTest.calcNumOfNsInChange(newChips));
	}

	@Test
	public void calcNumOfNsInChangeReturnsZeroWhenChangeEqualsZeroPointThreeFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("0"), underTest.calcNumOfNsInChange(newChips));
	}

	@Test
	public void calcNumOfNsInChangeReturnsOneWhenChangeEqualsZeroPointOneFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Chips newChips = new Chips();
		assertEquals(new BigDecimal("1"), underTest.calcNumOfNsInChange(newChips));
	}

}
