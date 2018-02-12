package com.jchaffin.vendendingmachine;

import static org.junit.Assert.assertEquals;

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
	public void whenIdCoinIsPassedANickleItReturnsNickle() {
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
	public void acceptCoinPutsCoinInHoldIfItIsANickle() {
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

}
