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

	// Tests for idCoin method:
	@Test
	public void whenIdCoinIsPassedAQuarterItReturnsQuarter() {
		Quarter newQuarter = new Quarter();
		assertEquals("quarter", underTest.idCoin(newQuarter));
	}

	@Test
	public void whenIdCoinIsPassedANickleItReturnsNickle() {
		Nickle newNickle = new Nickle();
		assertEquals("nickle", underTest.idCoin(newNickle));
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

}
