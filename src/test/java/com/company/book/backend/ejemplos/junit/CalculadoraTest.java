package com.company.book.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	@BeforeAll
	public static void primero() {
		System.out.println("Primero");
	}
	
	@AfterAll
	public static void ultimo() {
		System.out.println("Ultimo");
	}

	@Test
	public void calculadorAssert() {
		Calculadora calc = new Calculadora();
		assertEquals(2, calc.sumar(1, 1) );
		assertEquals(3, calc.restar(4, 1) );
		assertEquals(5, calc.dividir(10, 2) );
		assertEquals(1, calc.multiplicar(1, 1) );
		System.out.println("calculadorAssert");
	}
	
	@Test
	public void calcularTrueFalse() {
		
		Calculadora calc = new Calculadora();
		assertTrue(calc.sumar(1, 5) == 6);
		assertTrue(calc.restar(11, 5) == 6);
		assertTrue(calc.multiplicar(1, 5) == 5);
		assertTrue(calc.dividir(50, 5) == 10);
		System.out.println("calcularTrueFalse");

	}
}
