import org.junit.jupiter.api.Test;
import static com.revature.utils.StringUtils.findFirst;
import static org.junit.jupiter.api.Assertions.*;

class TestFindFirst
{

    @Test
    void testFindFirstWithMatchingPrefix() {
        String[] items = {"apple", "banana", "apricot", "cherry"};
        assertEquals("apple", findFirst(items, "app"));
    }

    @Test
    void testFindFirstReturnsFirstMatch() {
        String[] items = {"banana", "apple", "apricot"};
        assertEquals("apple", findFirst(items, "ap"));
    }

    @Test
    void testFindFirstWithNoMatch() {
        String[] items = {"apple", "banana", "cherry"};
        assertNull(findFirst(items, "xyz"));
    }

    @Test
    void testFindFirstWithNullArray() {
        assertNull(findFirst(null, "test"));
    }

    @Test
    void testFindFirstWithEmptyArray() {
        String[] items = {};
        assertNull(findFirst(items, "test"));
    }

    @Test
    void testFindFirstWithNullItemsInArray() {
        String[] items = {null, "apple", "banana"};
        assertEquals("apple", findFirst(items, "app"));
    }

    @Test
    void testFindFirstWithAllNullItems() {
        String[] items = {null, null, null};
        assertNull(findFirst(items, "test"));
    }

    @Test
    void testFindFirstWithEmptyPrefix() {
        String[] items = {"apple", "banana", "cherry"};
        assertEquals("apple", findFirst(items, ""));
    }

    @Test
    void testFindFirstWithExactMatch() {
        String[] items = {"apple", "banana", "cherry"};
        assertEquals("apple", findFirst(items, "apple"));
    }

    @Test
    void testFindFirstCaseSensitive() {
        String[] items = {"Apple", "banana", "apricot"};
        assertNull(findFirst(items, "app"));
    }

    @Test
    void testFindFirstWithEmptyStringInArray() {
        String[] items = {"", "apple", "banana"};
        assertEquals("", findFirst(items, ""));
    }
}