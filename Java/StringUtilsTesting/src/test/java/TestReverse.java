import org.junit.jupiter.api.Test;
import static com.revature.utils.StringUtils.reverse;
import static org.junit.jupiter.api.Assertions.*;

class TestReverse
{
    @Test
    void testReverseNormalString()
    {
        assertEquals("olleh", reverse("hello"));
    }

    @Test
    void testReverseEmptyString() {
        assertEquals("", reverse(""));
    }

    @Test
    void testReverseSingleCharacter() {
        assertEquals("a", reverse("a"));
    }

    @Test
    void testReverseNullInput() {
        assertNull(reverse(null));
    }

    @Test
    void testReverseWithSpaces() {
        assertEquals("dlrow olleh", reverse("hello world"));
    }

    @Test
    void testReverseWithSpecialCharacters() {
        assertEquals("!@#$%", reverse("%$#@!"));
    }

    @Test
    void testReverseWithNumbers() {
        assertEquals("54321", reverse("12345"));
    }

    @Test
    void testReversePalindrome() {
        assertEquals("racecar", reverse("racecar"));
    }
}