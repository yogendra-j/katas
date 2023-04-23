package com.tddkatas.stringcalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    @Test
    public void cannotInstatiateClass() {
        assertThrows(InstantiationException.class, () -> new StringCalculator());
    }

    @Test
    public void addEmptyString() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void addOneNumber() {
        assertEquals(0, StringCalculator.add("0"));
        assertEquals(1, StringCalculator.add("1"));
        assertEquals(10, StringCalculator.add("10"));
        assertEquals(290, StringCalculator.add("290"));
    }

    @Test
    public void addTwoNumber() {
        assertEquals(0, StringCalculator.add("0,0"));
        assertEquals(1, StringCalculator.add("1,0"));
        assertEquals(10, StringCalculator.add("1,9"));
        assertEquals(290, StringCalculator.add("280,10"));
    }

    @Test
    public void addManyNumber() {
        assertEquals(0, StringCalculator.add("0,0,0"));
        assertEquals(1, StringCalculator.add("1,0,0"));
        assertEquals(10, StringCalculator.add("1,2,6,1"));
        assertEquals(290, StringCalculator.add("20,260,0,1,0,9"));
    }

    @Test
    public void addManyNumberAndAllowNewLines() {
        assertEquals(0, StringCalculator.add("0\n0,0"));
        assertEquals(1, StringCalculator.add("1\n0\n0"));
        assertEquals(10, StringCalculator.add("1,2\n6,1"));
        assertEquals(290, StringCalculator.add("20\n260\n0,1,0,9"));
    }

    @Test
    public void addManyNumberAndAllowManyDelimeters() {
        assertEquals(0, StringCalculator.add("\\;\n0;0;0"));
        assertEquals(5, StringCalculator.add("\\v\n1v2v2"));
    }

    @Test
    public void addNegativeNumberNotAllowed() {
        Exception exception;
        exception = assertThrows(IllegalArgumentException.class, () -> StringCalculator.add("\\;\n-1;0;1"));
        assertEquals("-1,", exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class, () -> StringCalculator.add("5,0,-1"));
        assertEquals("-1,", exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class, () -> StringCalculator.add("\\v\n1v14v-10v-2"));
        assertEquals("-10,-2,", exception.getMessage());
    }

    @Test
    public void addManyNumberAndIgnoreBiggerNumbersThan1000() {
        assertEquals(0, StringCalculator.add("\\;\n0;0;1000"));
        assertEquals(5, StringCalculator.add("\\v\n1v2v2v2000"));
        assertEquals(8, StringCalculator.add("1,2,2,2000\n5000,3"));
    }

    @Test
    public void addManyAndAllowDelimeterofAnyLength() {
        assertEquals(0, StringCalculator.add("\\[;]\n0;0;1000"));
        assertEquals(5, StringCalculator.add("\\[vb;.]\n1vb;.2vb;.2vb;.2000"));
    }

    @Test
    public void addManyAndAllowManyDelimeterofAnyLength() {
        assertEquals(0, StringCalculator.add("\\[;][,j]\n0;0,j1000"));
        assertEquals(5, StringCalculator.add("\\[vb;.][nn][.,.]\n1vb;.2nn2.,.2000"));
        assertEquals(6, StringCalculator.add("\\[*][%]\n1*2%3"));
    }
}
