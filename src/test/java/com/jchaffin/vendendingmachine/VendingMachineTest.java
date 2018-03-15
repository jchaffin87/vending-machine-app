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
		assertEquals(true, underTest.checkHoldContainsCoin(newCoin));
	}

	@Test
	public void acceptCoinPutsCoinInHoldIfItIsAQuarter() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertEquals(true, underTest.checkHoldContainsCoin(newQuarter));
		assertEquals(false, underTest.checkHoldContainsCoin(newPenny));
	}

	@Test
	public void acceptCoinPutsCoinInHoldIfItIsANickel() {
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertEquals(true, underTest.checkHoldContainsCoin(newNickel));
		assertEquals(false, underTest.checkHoldContainsCoin(newPenny));
	}

	@Test
	public void acceptCoinPutsCoinInHoldIfItIsADime() {
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertEquals(true, underTest.checkHoldContainsCoin(newDime));
		assertEquals(false, underTest.checkHoldContainsCoin(newPenny));
	}

	@Test
	public void acceptCoinPutsCoinInCoinReturnIfItsAPenny() {
		Penny newPenny = new Penny();
		underTest.acceptCoin(newPenny);
		assertEquals(true, underTest.checkCoinReturnContainsCoin(newPenny));
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
		assertEquals(true, underTest.checkStockForItem(newCola));
	}

	@Test
	public void stockItemAddsChipsToStock() {
		Chips newChips = new Chips();
		underTest.stockItem(newChips);
		assertEquals(true, underTest.checkStockForItem(newChips));
	}

	@Test
	public void stockItemAddsCandyToStock() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		assertEquals(true, underTest.checkStockForItem(newCandy));
	}

	@Test
	public void checkStockReturnsTrueWhenPassedCandyAndCandyItemIsStocked() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		assertEquals(true, underTest.checkStockForItemType("candy"));
	}

	@Test
	public void checkStockReturnsFalseWhenPassedColaAndOnlyCandyItemIsStocked() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		assertEquals(false, underTest.checkStockForItemType("cola"));
	}

	@Test
	public void checkStockReturnsTrueWhenPassedCandyAndBothCandyItemAndColaItemsAreStocked() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		Cola newCola = new Cola();
		underTest.stockItem(newCola);
		assertEquals(true, underTest.checkStockForItemType("candy"));
	}

	@Test
	public void checkStockReturnsTrueBothWhenPassedCandyAndWhenPassedColaWhenCandyItemAndColaItemAreStocked() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		Cola newCola = new Cola();
		underTest.stockItem(newCola);
		assertEquals(true, underTest.checkStockForItemType("candy"));
		assertEquals(true, underTest.checkStockForItemType("cola"));
	}

	@Test
	public void checkStockReturnsTrueBothWhenPassedCandyAndWhenPassedColaButFalseWhenPassedChipsWhenCandyItemAndColaItemAreStocked() {
		Candy newCandy = new Candy();
		underTest.stockItem(newCandy);
		Cola newCola = new Cola();
		underTest.stockItem(newCola);
		assertEquals(true, underTest.checkStockForItemType("candy"));
		assertEquals(true, underTest.checkStockForItemType("cola"));
		assertEquals(false, underTest.checkStockForItemType("chips"));
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
		assertEquals(false, underTest.checkStockForItemType(newCola.getType()));
	}

	@Test
	public void checkStockReturnsFalseWhenChipsItemIsStockedAndThenDispenseItemIsPassedThatChipsItem() {
		Chips newChips = new Chips();
		underTest.stockItem(newChips);
		underTest.dispenseItem(newChips);
		assertEquals(false, underTest.checkStockForItemType(newChips.getType()));
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
		assertEquals(true, underTest.checkBankContainsCoin(newQuarter));
	}

	@Test
	public void calcMoneyInHoldReturnsZeroAndCoinsInHoldArePutInBankWhenOneQuarterAndOneDimeIsAcceptedAndBankCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.bankCoins();
		assertEquals(new BigDecimal("0.00"), underTest.calcMoneyInHold());
		assertEquals(true, underTest.checkBankContainsCoin(newQuarter));
		assertEquals(true, underTest.checkBankContainsCoin(newDime));
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

	@Test
	public void returnChangeTakesQuarterFromBankAndAddsItToCoinReturnWhenChangeEqualsZeroPointTwoFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Chips newChips = new Chips();
		underTest.returnChange(newChips);
		assertEquals(false, underTest.checkBankContainsCoin(newQuarter));
		assertEquals(true, underTest.checkCoinReturnContainsCoin(newQuarter));
		assertEquals(1, underTest.getSizeOfCoinReturn());
	}

	@Test
	public void returnChangeTakesDimeFromBankAndAddsItToCoinReturnWhenChangeEqualsZeroPointOneZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		Chips newChips = new Chips();
		underTest.returnChange(newChips);
		assertEquals(false, underTest.checkBankContainsCoin(newDime));
		assertEquals(true, underTest.checkCoinReturnContainsCoin(newDime));
		assertEquals(1, underTest.getSizeOfCoinReturn());
	}

	@Test
	public void returnChangeTakesNickelFromBankAndAddsItToCoinReturnWhenChangeEqualsZeroPointZeroFive() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Chips newChips = new Chips();
		underTest.returnChange(newChips);
		assertEquals(false, underTest.checkBankContainsCoin(newNickel));
		assertEquals(true, underTest.checkCoinReturnContainsCoin(newNickel));
		assertEquals(1, underTest.getSizeOfCoinReturn());
	}

	@Test
	public void returnChangeTakesOneQuarterOneDimeAndOneNickelFromBankAndAddsThemToCoinReturnWhenChangeEqualsZeroPointFourZero() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Chips newChips = new Chips();
		underTest.returnChange(newChips);
		assertEquals(false, underTest.checkBankContainsCoin(newNickel));
		assertEquals(true, underTest.checkCoinReturnContainsCoin(newNickel));
		assertEquals(false, underTest.checkBankContainsCoin(newDime));
		assertEquals(true, underTest.checkCoinReturnContainsCoin(newDime));
		assertEquals(false, underTest.checkBankContainsCoin(newQuarter));
		assertEquals(true, underTest.checkCoinReturnContainsCoin(newQuarter));
		assertEquals(3, underTest.getSizeOfCoinReturn());
	}

	@Test
	public void countQuartersInBankReturnsOneWhenOneQuarterIsInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		assertEquals(1, underTest.countQuartersInBank());
	}

	@Test
	public void countQuartersInBankReturnsTwoWhenTwoQuartersAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		assertEquals(2, underTest.countQuartersInBank());
	}

	@Test
	public void countQuartersInBankReturnsThreeWhenThreeQuartersAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		assertEquals(3, underTest.countQuartersInBank());
	}

	@Test
	public void countNickelsInBankReturnsOneWhenOneNickelIsInBank() {
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		assertEquals(1, underTest.countNickelsInBank());
	}

	@Test
	public void countNickelsInBankReturnsTwoWhenTwoNickelsAreInBank() {
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		assertEquals(2, underTest.countNickelsInBank());
	}

	@Test
	public void countNickelsInBankReturnsThreeWhenThreeNickelsAreInBank() {
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		assertEquals(3, underTest.countNickelsInBank());
	}

	@Test
	public void countDimesInBankReturnsOneWhenOneDimeIsInBank() {
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		assertEquals(1, underTest.countDimesInBank());
	}

	@Test
	public void countDimesInBankReturnsTwoWhenTwoDimesAreInBank() {
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertEquals(2, underTest.countDimesInBank());
	}

	@Test
	public void countDimesInBankReturnsThreeWhenThreeDimesAreInBank() {
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertEquals(3, underTest.countDimesInBank());
	}

	@Test
	public void checkExactChangeNeededReturnsTrueWhenFiveQuartersFourNickelsAndFiveDimesAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertEquals(true, underTest.checkExactChangeNeeded());
	}

	@Test
	public void checkExactChangeNeededReturnsTrueWhenFiveQuartersFiveNickelsAndFourDimesAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertEquals(true, underTest.checkExactChangeNeeded());
	}

	@Test
	public void checkExactChangeNeededReturnsTrueWhenFourQuartersFiveNickelsAndFiveDimesAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertEquals(true, underTest.checkExactChangeNeeded());
	}

	@Test
	public void checkExactChangeNeededReturnsFalseWhenFiveQuartersFiveNickelsAndFiveDimesAreInBank() {
		Quarter newQuarter = new Quarter();
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		underTest.putCoinDirectlyInBank(newQuarter);
		Nickel newNickel = new Nickel();
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		underTest.putCoinDirectlyInBank(newNickel);
		Dime newDime = new Dime();
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		underTest.putCoinDirectlyInBank(newDime);
		assertEquals(false, underTest.checkExactChangeNeeded());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroWhenOneQuarterIsAcceptedAndReturnCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.returnCoins();
		assertEquals(new BigDecimal("0.00"), underTest.calcMoneyInHold());
	}

	@Test
	public void calcMoneyInHoldReturnsZeroAndQuarterIsInCoinReturnWhenOneQuarterIsAcceptedAndReturnCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.returnCoins();
		assertEquals(new BigDecimal("0.00"), underTest.calcMoneyInHold());
		assertEquals(true, underTest.checkCoinReturnContainsCoin(newQuarter));
	}

	@Test
	public void countQuartersInCoinReturnReturnsOneWhenOneQuarterIsAcceptedAndReturnCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.returnCoins();
		assertEquals(1, underTest.countQuartersInCoinReturn());
	}

	@Test
	public void countQuartersInCoinReturnReturnsTwoWhenTwoQuartersAreAcceptedAndReturnCoinsIsRun() {
		Quarter newQuarter = new Quarter();
		underTest.acceptCoin(newQuarter);
		underTest.acceptCoin(newQuarter);
		underTest.returnCoins();
		assertEquals(2, underTest.countQuartersInCoinReturn());
	}

	@Test
	public void countNickelsInCoinReturnReturnsOneWhenOneNickelIsAcceptedAndReturnCoinsIsRun() {
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		underTest.returnCoins();
		assertEquals(1, underTest.countNickelsInCoinReturn());
	}

	@Test
	public void countNickelsInCoinReturnReturnsTwoWhenTwoNickelsAreAcceptedAndReturnCoinsIsRun() {
		Nickel newNickel = new Nickel();
		underTest.acceptCoin(newNickel);
		underTest.acceptCoin(newNickel);
		underTest.returnCoins();
		assertEquals(2, underTest.countNickelsInCoinReturn());
	}

	@Test
	public void countDimesInCoinReturnReturnsOneWhenOneDimeIsAcceptedAndReturnCoinsIsRun() {
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.returnCoins();
		assertEquals(1, underTest.countDimesInCoinReturn());
	}

	@Test
	public void countDimesInCoinReturnReturnsTwoWhenTwoDimesAreAcceptedAndReturnCoinsIsRun() {
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.acceptCoin(newDime);
		underTest.returnCoins();
		assertEquals(2, underTest.countDimesInCoinReturn());
	}

	@Test
	public void emptyCoinReturnClearsCoinReturn() {
		Dime newDime = new Dime();
		underTest.acceptCoin(newDime);
		underTest.acceptCoin(newDime);
		underTest.returnCoins();
		underTest.emptyCoinReturn();
		assertEquals(0, underTest.countDimesInCoinReturn());
	}

}
