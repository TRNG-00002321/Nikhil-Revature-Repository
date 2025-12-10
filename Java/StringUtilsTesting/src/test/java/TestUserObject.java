import static com.revature.utils.StringUtils.parseUser;
import static org.junit.jupiter.api.Assertions.*;
import com.revature.utils.User;
import org.junit.jupiter.api.Test;

class TestUserObject
{
    @Test
    void testParseUserWithAssertAll()
    {
        User result = parseUser("John,Doe,30,john@example.com");

        assertAll("User properties",
                () -> assertEquals("John", result.getFirstName(), "First name should match"),
                () -> assertEquals("Doe", result.getLastName(), "Last name should match"),
                () -> assertEquals(30, result.getAge(), "Age should match"),
                () -> assertEquals("john@example.com", result.getEmail(), "Email should match")
        );
    }
}
