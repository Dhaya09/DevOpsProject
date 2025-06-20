package com.devops.devopsProject;

import static org.junit.Assert.*;
import org.junit.Test;

public class ProjectMainTest {

    // ---------- Valid Test Cases ----------

    @Test
    public void testValidSimpleInterest() {
        double result = ProjectMain.simpleInterest(10000, 5, 2);
        assertEquals(1000.0, result, 0.01);
    }

    @Test
    public void testValidCompoundInterest() {
        double result = ProjectMain.compoundInterest(10000, 5, 2);
        assertEquals(125.0, result, 1.0); // compound = 10000*(1+0.05)^2 - 10000 = 1025
    }

    @Test
    public void testValidEMICalculator() {
        double[] result = ProjectMain.emiCalculator(100000, 10, 1);
        assertEquals(8791.59, result[0], 1.0); // EMI per month
        assertEquals(105499.13, result[1], 1.0); // Total Payment
        assertEquals(5499.13, result[2], 1.0); // Total Interest
    }

    @Test
    public void testValidFutureValue() {
        double result = ProjectMain.futureValue(5000, 8, 3);
        assertEquals(6298.56, result, 1.0);
    }

    @Test
    public void testValidPresentValue() {
        double result = ProjectMain.presentValue(6298.56, 8, 3);
        assertEquals(5000.0, result, 1.0);
    }

    @Test
    public void testValidAnnuityValue() {
        double result = ProjectMain.annuityValue(10000, 5, 3);
        assertEquals(31525.0, result, 50.0); // rough estimation
    }

    @Test
    public void testValidInflationAdjustedValue() {
        double result = ProjectMain.inflationAdjustedValue(10000, 5, 2);
        assertEquals(9070.29, result, 1.0);
    }

    @Test
    public void testValidDoublingTime() {
        double result = ProjectMain.doublingTime(6);
        assertEquals(12.0, result, 0.01);
    }

    @Test
    public void testValidNetInterestEarned() {
        double result = ProjectMain.netInterestEarned(10000, 5, 2);
        assertEquals(1025.0, result, 1.0);
    }

    // ---------- Invalid Test Cases ----------

    @Test
    public void testInvalidSimpleInterest() {
        double result = ProjectMain.simpleInterest(5000, 5, 2);
        assertNotEquals(600.0, result, 0.01);
    }

    @Test
    public void testInvalidCompoundInterest() {
        double result = ProjectMain.compoundInterest(10000, 10, 2);
        assertNotEquals(2000.0, result, 1.0);
    }

    @Test
    public void testInvalidEMICalculator() {
        double[] result = ProjectMain.emiCalculator(10000, 10, 1);
        assertNotEquals(900.0, result[0], 1.0);
        assertNotEquals(11000.0, result[1], 1.0);
        assertNotEquals(1000.0, result[2], 1.0);
    }

    @Test
    public void testInvalidFutureValue() {
        double result = ProjectMain.futureValue(2000, 5, 2);
        assertNotEquals(3000.0, result, 1.0);
    }

    @Test
    public void testInvalidPresentValue() {
        double result = ProjectMain.presentValue(2000, 5, 2);
        assertNotEquals(1900.0, result, 1.0);
    }

    @Test
    public void testInvalidAnnuityValue() {
        double result = ProjectMain.annuityValue(5000, 8, 3);
        assertNotEquals(20000.0, result, 100.0);
    }

    @Test
    public void testInvalidInflationAdjustedValue() {
        double result = ProjectMain.inflationAdjustedValue(10000, 10, 2);
        assertNotEquals(9000.0, result, 1.0);
    }

    @Test
    public void testInvalidDoublingTime() {
        double result = ProjectMain.doublingTime(9);
        assertNotEquals(6.0, result, 0.01);
    }

    @Test
    public void testInvalidNetInterestEarned() {
        double result = ProjectMain.netInterestEarned(20000, 5, 2);
        assertNotEquals(3000.0, result, 1.0);
    }
}
