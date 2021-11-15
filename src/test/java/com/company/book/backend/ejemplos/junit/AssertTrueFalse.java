package com.company.book.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AssertTrueFalse {
	
	@Test
	public void test1() {
	
		assertTrue(true);
		//assertTrue(false);
		
	}
	
	@Test
	public void test2() {
	 boolean expresion = 4 == 4;
	 boolean expresion2 = 3 == 4;

	 assertTrue(expresion);	
	 assertFalse(expresion2);
	}
}
