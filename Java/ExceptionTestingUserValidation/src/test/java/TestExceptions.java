import com.revature.exception.UserValidation;
import com.revature.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestExceptions
{
    private final UserValidation validator = new UserValidation();

    // Task 1
    @ParameterizedTest
    @ValueSource(strings = { "", "noatsign", "@nodomain" })
    void invalidEmails_throwException(String email) {
        assertThrows(IllegalArgumentException.class, () -> validator.validateEmail(email));
    }

    @Test
    void nullEmail_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateEmail(null));
    }

    @Test
    void method_invalidInput_Null() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateEmail(null)
        );
        assertTrue(ex.getMessage().contains("Email cannot be null"));
    }

    @Test
    void method_invalidInput_Empty() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateEmail("")
        );
        assertTrue(ex.getMessage().contains("Email cannot be empty"));
    }

    @Test
    void method_invalidInput_NoAtSign() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateEmail("abcdef.com")
        );
        assertTrue(ex.getMessage().contains("Email must contain @"));
    }

    @Test
    void method_invalidInput_NoDomain() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateEmail("@google.com")
        );
        assertTrue(ex.getMessage().contains("Email has invalid format"));
    }

    // Task 2
    @ParameterizedTest
    @ValueSource(strings = {"short", "nouppercase", "NOLOWERCASE" })
    void invalidPasswords_throwException(String password) {
        assertThrows(ValidationException.class, () -> validator.validatePassword(password));
    }

    @Test
    void nullPassword_throwsException() {
        assertThrows(ValidationException.class, () -> validator.validatePassword(null));
    }

    @Test
    void password_invalidInput_Null() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> validator.validatePassword(null)
        );
        assertTrue(ex.getMessage().contains("Password cannot be null"));
    }

    @Test
    void password_invalidInput_Short() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> validator.validatePassword("s")
        );
        assertTrue(ex.getMessage().contains("Password must be at least 8 characters"));
    }

    @Test
    void password_invalidInput_NoUpperCase() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> validator.validatePassword("nouppercase")
        );
        assertTrue(ex.getMessage().contains("Password must contain an uppercase letter"));
    }

    @Test
    void password_invalidInput_NoLowerCase() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> validator.validatePassword("NOLOWERCASE")
        );
        assertTrue(ex.getMessage().contains("Password must contain a lowercase letter"));
    }

    @Test
    void age_negative_throwsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateAge(-1)
        );
        assertTrue(ex.getMessage().contains("Age cannot be negative"));
    }

    @Test
    void age_over150_throwsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateAge(151)
        );
        assertTrue(ex.getMessage().contains("Age cannot exceed 150"));
    }

    @Test
    void age_valid_doesNotThrow()
    {
        assertDoesNotThrow(() -> validator.validateAge(0));
        assertDoesNotThrow(() -> validator.validateAge(25));
        assertDoesNotThrow(() -> validator.validateAge(150));
    }

    @Test
    void validateEmail_multipleInvalidInputs_allThrowExceptions() {
        assertAll("Email validation exceptions",
                () -> assertThrows(IllegalArgumentException.class,
                        () -> validator.validateEmail(null)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> validator.validateEmail("")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> validator.validateEmail("invalid"))
        );
    }

    @Test
    void validatePasswords_multipleInvalidInputs_allThrowExceptions() {
        assertAll("Email validation exceptions",
                () -> assertThrows(ValidationException.class,
                        () -> validator.validatePassword("short")),
                () -> assertThrows(ValidationException.class,
                        () -> validator.validatePassword("nouppercase")),
                () -> assertThrows(ValidationException.class,
                        () -> validator.validatePassword("NOLOWERCASE"))
        );
    }

    @Test
    void validateAge_multipleInvalidInputs_allThrowExceptions() {
        assertAll("Age validation exceptions",
                () -> assertThrows(IllegalArgumentException.class,
                        () -> validator.validateAge(-1)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> validator.validateAge(151)),
                () -> assertDoesNotThrow(() -> validator.validateAge(50))
        );
    }
}
