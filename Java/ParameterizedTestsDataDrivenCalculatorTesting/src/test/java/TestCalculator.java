import com.revature.parameterized.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.platform.commons.util.StringUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestCalculator
{
    /*
    Before:
        @Test void isEven_2_returnsTrue() { assertTrue(calc.isEven(2)); }
        @Test void isEven_4_returnsTrue() { assertTrue(calc.isEven(4)); }
        @Test void isEven_100_returnsTrue() { assertTrue(calc.isEven(100)); }

     */

    Calculator calculator = new Calculator();

    //After:
    @ParameterizedTest(name = "{0} is Even")
    @ValueSource(ints = {2, 4, 6, 100, 0, -2})
    void isEven_evenNumbers_returnsTrue(int number)
    {
        assertTrue(calculator.isEven(number));
    }

    @ParameterizedTest(name = "{0} is Odd")
    @ValueSource(ints = {1, 3, 5, 99})
    void isOdd_oddNumbers_returnsFalse(int number)
    {
        assertFalse(calculator.isEven(number));
    }

    @ParameterizedTest(name = "{0} is Positive")
    @ValueSource(ints = {1, 3, 5, 99})
    void isPositive_positiveNumbers_checkPositive(int number)
    {
        assertTrue(calculator.isPositive(number));
    }

    @ParameterizedTest(name= "{0} + {1} = {2}")
    @CsvSource({
            "1, 2, 3",
            "0, 0, 0",
            "-1, 1, 0",
            "100, 200, 300",
            "-4, -5, -9",
            "6, 0, 6"
    })
    void add_variousInputs_returnsCorrectSum(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
            "1, 2, -1",
            "0, 0, 0",
            "-1, 1, -2",
            "200, 100, 100",
    })
    void sub_variousInputs_returnsCorrectResult(int a, int b, int expected) {
        assertEquals(expected, calculator.subtract(a, b));
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "1, 2, 2",
            "0, 0, 0",
            "-1, 0, 0",
            "7, 8, 56",
    })
    void mul_variousInputs_returnsCorrectResult(int a, int b, int expected) {
        assertEquals(expected, calculator.multiply(a, b));
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "10, 2, 5",
            "20, 2, 10",
            "20, 1, 20",
            "0, 8, 0",
    })
    void div_variousInputs_returnsCorrectResult(int a, int b, int expected) {
        assertEquals(expected, calculator.divide(a, b));
        assertThrows(ArithmeticException.class, () -> calculator.divide(a, 0));
    }

    @ParameterizedTest(name = "{0} ^ {1} = {2}")
    @MethodSource("providePowerTestCases")
    void power_variousCases_returnsCorrectAnswer(int a, int b, int expected) {
        assertEquals(expected, calculator.power(a, b));
    }
    static Stream<Arguments> providePowerTestCases() {
        return Stream.of(
                Arguments.of(2, 3, 8),
                Arguments.of(9, 0, 1),
                Arguments.of(0, 0, 1),
                Arguments.of(0, 2, 0)  // Integer division
        );
    }

    @ParameterizedTest(name = "Testing blank input: {0}")
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void isBlank_blankInputs_returnsTrue(String input) {
        assertTrue(StringUtils.isBlank(input));
    }
}
