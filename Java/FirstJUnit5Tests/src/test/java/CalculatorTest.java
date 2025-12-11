import com.revature.junit.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest
{
    private final Calculator calculator = new Calculator();

    @Test
    @DisplayName("Adding two positive numbers")
    void test_add_twoPositive()
    {
        // Arrange
        Calculator calculator = new Calculator();
        int a = 5;
        int b = 3;
        int expectedResult = 8;
        // Act
        int actualResult = calculator.add(a, b);
        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The sum of 5 and 3 should be 8");
    }

    @Test
    @DisplayName("Adding one positive number and one negative number")
    void test_add_positiveNegative()
    {
        // Arrange
        Calculator calculator = new Calculator();
        int a = 10;
        int b = -3;
        int expectedResult = 7;
        // Act
        int actualResult = calculator.add(a, b);
        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The sum of 10 and -3 should be 7");
    }

    @Test
    @DisplayName("Adding two negative numbers")
    void test_add_twoNegative()
    {
        // Arrange
        Calculator calculator = new Calculator();
        int a = -5;
        int b = -3;
        int expectedResult = -8;
        // Act
        int actualResult = calculator.add(a, b);
        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The sum of -5 and -3 should be -8");
    }

    @Test
    @DisplayName("Adding a number with zero")
    void test_add_zero()
    {
        // Arrange
        Calculator calculator = new Calculator();
        int a = 42;
        int b = 0;
        int expectedResult = 42;
        // Act
        int actualResult = calculator.add(a, b);
        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The sum of 42 and 0 should be 42");
    }

    @Test
    @DisplayName("Basic Subtraction")
    void test_sub_basicSub()
    {
        // Arrange
        Calculator calculator = new Calculator();
        int a = 10;
        int b = 3;
        int expectedResult = 7;
        // Act
        int actualResult = calculator.subtract(a, b);
        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The subtraction of 10 and 3 should be 7");
    }

    @Test
    @DisplayName("Subtraction of bigger number")
    void test_sub_subBigNum()
    {
        // Arrange
        Calculator calculator = new Calculator();
        int a = 10;
        int b = 20;
        int expectedResult = -10;
        // Act
        int actualResult = calculator.subtract(a, b);
        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The subtraction of 10 and 20 should be -10");
    }

    @Test
    @DisplayName("Subtraction of zero")
    void test_sub_subZero()
    {
        // Arrange
        Calculator calculator = new Calculator();
        int a = 10;
        int b = 0;
        int expectedResult = 10;
        // Act
        int actualResult = calculator.subtract(a, b);
        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The subtraction of 10 and 0 should be 10");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 100})
    void test_isEven_Even(int x)
    {
        assertTrue(calculator.isEven(x));
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 99})
    void test_isEven_Odd(int x)
    {
        assertFalse(calculator.isEven(x));
    }

    @Test
    void test_isEven_Zero()
    {
        assertTrue(calculator.isEven(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -99})
    void test_isEven_Negative(int x)
    {
        assertFalse(calculator.isEven(x));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void test_isPositive_Nums(int x)
    {
        assertTrue(calculator.isPositive(x));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3})
    void test_isPositive_negativeNums(int x)
    {
        assertFalse(calculator.isPositive(x));
    }

    @Test
    void test_isPositive_Zero()
    {
        assertTrue(calculator.isEven(0));
    }
}