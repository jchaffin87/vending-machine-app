package com.jchaffin.vendendingmachine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VendingMachineTest {

	@Test
	public void whenIdCoinIsPassedAQuarterItReturnsQuarter() {
		VendingMachine underTest = new VendingMachine();
		Quarter newQuarter = new Quarter();
		assertEquals("quarter", underTest.idCoin(newQuarter));

	}

}
