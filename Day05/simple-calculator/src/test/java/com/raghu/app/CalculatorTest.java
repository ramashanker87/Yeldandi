package com.raghu.app;

import org.junit.Test;

public class CalculatorTest {

  @Test
  public void testAdd() {
    Calculator calculator = new Calculator();
    double result=calculator.add(5,3);
    assert result==8;
  }
  @Test
  public void testSubtract() {
    Calculator calculator = new Calculator();
    double result = calculator.subtract(6,4);
    assert result==2;
  }
  @Test
  public void testMultiply() {
    Calculator calculator = new Calculator();
    double result= calculator.multiply(4,3);
    assert result==12;
  }

}
