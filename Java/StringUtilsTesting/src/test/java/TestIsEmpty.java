import org.junit.jupiter.api.Test;

import static com.revature.utils.StringUtils.isEmpty;
import static com.revature.utils.StringUtils.reverse;
import static org.junit.jupiter.api.Assertions.*;

public class TestIsEmpty
{
    @Test
    void testIsEmptyWithNull() {
        assertTrue(isEmpty(null));
    }

    @Test
    void testIsEmptyWithEmptyString() {
        assertTrue(isEmpty(""));
    }

    @Test
    void testIsEmptyWithNonEmptyString() {
        assertFalse(isEmpty("hello"));
    }

    @Test
    void testIsEmptyWithSingleCharacter() {
        assertFalse(isEmpty("a"));
    }

    @Test
    void testIsEmptyWithWhitespace() {
        assertFalse(isEmpty(" "));
    }

    @Test
    void testIsEmptyWithMultipleSpaces() {
        assertFalse(isEmpty("   "));
    }

    @Test
    void testIsEmptyWithTab() {
        assertFalse(isEmpty("\t"));
    }

    @Test
    void testIsEmptyWithNewline() {
        assertFalse(isEmpty("\n"));
    }
}
