package com.jchaffin.vendendingmachine;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VendingMachineTest {

	@Test
	public void whenIdCoinIsPassedACoinWithWeightOfFourAndSizeOfFourItReturnsQuarter() {
		VendingMachine underTest = new VendingMachine();
		Coin newCoin = new Coin(4, 4);
		assertEquals("quarter", underTest.idCoin(newCoin));

	}

}
