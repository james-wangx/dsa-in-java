package com.codicefun.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfixExpressionCalculatorTest {

    public InfixExpressionCalculator calculator = new InfixExpressionCalculator();

    @Test
    public void testCalc() {
        assertEquals(calculator.calc("(30+2)*6-20/2+7"), 189);
    }
}
