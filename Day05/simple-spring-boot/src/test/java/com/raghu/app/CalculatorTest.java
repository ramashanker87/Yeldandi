package com.raghu.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {
    @InjectMocks
    Calculator calculator;

    @Mock
    private CalculateArea mockCalculateArea;

    @Test
    public void testAdd() {
        int result=calculator.add(1,2);
        assert (result == 3);
    }
    @Test
    public void testSubtract() {
        int result=calculator.subtract(5,2);
        assert (result == 3);
    }
    @Test
    public void testMultiply() {
        int result=calculator.multiply(5,2);
        assert (result == 10);
    }
    @Test
    public void testDivide() {
        int result=calculator.divide(8,2);
        assert (result == 4);
    }

    @Test
    public void testCalculateArea() {
        float actualValue=55.6F;
        when(mockCalculateArea.circleArea(anyFloat())).thenReturn(actualValue);
        float expepectedValue=calculator.areaCalculateCircle(7);
        assert (actualValue == expepectedValue);
        verify( mockCalculateArea, atLeast(1)).circleArea(anyFloat());
    }

    @Test
    public void testCalculateSquare() {
        float actualValue=144F;
        when(mockCalculateArea.squareArea(anyFloat())).thenReturn(actualValue);
        float expectedValue= calculator.areaCalculateSquare(12);
        assert (actualValue == expectedValue);
        verify( mockCalculateArea, atLeast(1)).squareArea(anyFloat());
    }
}
