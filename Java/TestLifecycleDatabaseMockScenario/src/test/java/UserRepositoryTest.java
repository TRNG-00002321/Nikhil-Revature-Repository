import com.revature.mock.MockDatabase;
import com.revature.mock.User;
import com.revature.mock.UserRepository;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest
{

    private static MockDatabase database;
    private UserRepository repository;

    @BeforeAll
    static void setUpDatabase()
    {
        System.out.println("1. @BeforeAll: Setting up database");
        // TODO: Initialize the database connection
        // This runs ONCE before all tests
        System.out.println("Connecting to database...");
        database = new MockDatabase();
        database.connect();
    }

    @AfterAll
    static void tearDownDatabase()
    {
        System.out.println("5. @AfterAll: Closing database");
        // TODO: Close the database connection
        // This runs ONCE after all tests
        System.out.println("Disconnecting from database...");
        database.disconnect();
    }

    @BeforeEach
    void setUpTest()
    {
        System.out.println("  2. @BeforeEach: Preparing test");
        // TODO: Clear all data from database
        // TODO: Create a new repository instance
        // TODO: Insert any test fixtures needed
        database.clearAll();
        repository = new UserRepository(database);

        // Optional: Insert baseline test data
        database.insert(new User(1, "Admin", "admin@test.com"));
    }

    @AfterEach
    void tearDownTest()
    {
        System.out.println("  4. @AfterEach: Cleaning up test");
        // TODO: Any per-test cleanup
        // Note: The database is cleared in setUpTest anyway
        System.out.println("Test completed, data will be reset");
    }

    @Test
    @DisplayName("Test 1: Add user and verify")
    void test1_addUser()
    {
        System.out.println("    3. @Test: Running test");
        // Add a user
        repository.save(new User(2, "John", "john@test.com"));

        // Verify it exists
        assertEquals(2, repository.count());  // Admin + John
    }

    @Test
    @DisplayName("Test 2: Should have fresh state")
    void test2_freshState()
    {
        System.out.println("    3. @Test: Running test");
        // This test should ONLY see the Admin user
        // NOT the John user from test1
        assertEquals(1, repository.count());  // Only Admin
    }

    @Test
    @DisplayName("Test 3: Database operations work independently")
    void test3_independentOperations()
    {
        System.out.println("    3. @Test: Running test");
        repository.save(new User(3, "Jane", "jane@test.com"));
        repository.save(new User(4, "Bob", "bob@test.com"));

        // Should have Admin + 2 new users
        assertEquals(3, repository.count());
    }

}