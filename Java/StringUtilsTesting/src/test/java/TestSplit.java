import org.junit.jupiter.api.Test;

import static com.revature.utils.StringUtils.split;
import static org.junit.jupiter.api.Assertions.*;

class TestSplit
{

    @Test
    void testSplitWithCommaDelimiter()
    {
        String[] result = split("apple,banana,cherry", ",");
        assertArrayEquals(new String[]{"apple", "banana", "cherry"}, result);
    }

    @Test
    void testSplitWithNullInput()
    {
        String[] result = split(null, ",");
        assertArrayEquals(new String[0], result);
    }

    @Test
    void testSplitWithNoDelimiterPresent()
    {
        String[] result = split("apple", ",");
        assertArrayEquals(new String[]{"apple"}, result);
    }

    @Test
    void testSplitWithEmptyString()
    {
        String[] result = split("", ",");
        assertArrayEquals(new String[]{""}, result);
    }

    @Test
    void testSplitWithSpaceDelimiter()
    {
        String[] result = split("hello world test", " ");
        assertArrayEquals(new String[]{"hello", "world", "test"}, result);
    }

    @Test
    void testSplitWithMultipleConsecutiveDelimiters()
    {
        String[] result = split("apple,,cherry", ",");
        assertArrayEquals(new String[]{"apple", "", "cherry"}, result);
    }

    @Test
    void testSplitWithDelimiterAtStart()
    {
        String[] result = split(",apple,banana", ",");
        assertArrayEquals(new String[]{"", "apple", "banana"}, result);
    }

    @Test
    void testSplitWithDelimiterAtEnd()
    {
        String[] result = split("apple,banana,", ",");
        assertArrayEquals(new String[]{"apple", "banana"}, result);
    }

    @Test
    void testSplitWithMultiCharacterDelimiter()
    {
        String[] result = split("apple::banana::cherry", "::");
        assertArrayEquals(new String[]{"apple", "banana", "cherry"}, result);
    }

    @Test
    void testSplitWithRegexSpecialCharacter()
    {
        String[] result = split("apple.banana.cherry", "\\.");
        assertArrayEquals(new String[]{"apple", "banana", "cherry"}, result);
    }

    @Test
    void testSplitWithPipeDelimiter()
    {
        String[] result = split("apple|banana|cherry", "\\|");
        assertArrayEquals(new String[]{"apple", "banana", "cherry"}, result);
    }

    @Test
    void testSplitSingleCharacter()
    {
        String[] result = split("a", ",");
        assertArrayEquals(new String[]{"a"}, result);
    }
}