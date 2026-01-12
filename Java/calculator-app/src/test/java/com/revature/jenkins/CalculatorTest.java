package com.revature.jenkins;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest
{

    // shared instance for class-level setup
    private static Calculator sharedCalculator;

    // per-test instance
    private Calculator calculator;

    @BeforeAll
    static void beforeAll() {
        // runs once before any tests in this class
        sharedCalculator = new Calculator();
        System.out.println("[BeforeAll] created shared Calculator instance");
    }

    @AfterAll
    static void afterAll() {
        // runs once after all tests in this class
        sharedCalculator = null;
        System.out.println("[AfterAll] cleared shared Calculator instance");
    }

    @BeforeEach
    void setUp() {
        // runs before each test method
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        // runs after each test method
        calculator = null;
    }

    @ParameterizedTest
    @CsvSource({
        "2, 3, 5",        // two positives
        "5, -3, 2",       // positive + negative
        "-4, -6, -10",    // two negatives
        "0, 0, 0"         // two zeros
    })
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b), () -> a + " + " + b + " should equal " + expected);
    }

    @Test
    void testAddNormal() {
        assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
    }

    @Test
    void testAddUsingSharedInstance() {
        assertEquals(7, sharedCalculator.add(3, 4), "3 + 4 should equal 7");
    }
}
