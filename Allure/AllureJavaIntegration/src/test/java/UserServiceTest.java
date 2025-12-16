import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("User Management")
@Feature("User Lifecycle")
class UserServiceTest
{

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    @Story("Create user with valid data")
    @Description("Verify that a user is created successfully when valid name and email are provided")
    @Severity(SeverityLevel.CRITICAL)
    void createUser_validData_returnsUser() {
        User user = stepCreateUser("John", "john@test.com");

        assertNotNull(user.getId());
        assertEquals("John", user.getName());
    }

    @Test
    @Story("Reject user with null name")
    @Description("Verify that user creation fails when name is null")
    @Severity(SeverityLevel.NORMAL)
    void createUser_nullName_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> stepCreateUser(null, "test@test.com"));
    }

    @Test
    @Story("Reject user with invalid email")
    @Description("Verify that user creation fails when email format is invalid")
    @Severity(SeverityLevel.NORMAL)
    void createUser_invalidEmail_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> stepCreateUser("John", "invalid"));
    }

    @Test
    @Story("Retrieve existing user")
    @Description("Verify that an existing user can be retrieved by ID")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "User Story", url = "https://jira.company.com/browse/USER-101")
    void getUser_existingUser_returnsUser() throws UserNotFoundException {
        User created = stepCreateUser("Jane", "jane@test.com");

        User found = stepGetUser(created.getId());
        assertEquals(created, found);
    }

    @Test
    @Story("Fail to retrieve non-existing user")
    @Description("Verify that requesting a non-existing user throws UserNotFoundException")
    @Severity(SeverityLevel.NORMAL)
    @Issue("BUG-404")
    void getUser_nonExistingUser_throwsException() {
        assertThrows(UserNotFoundException.class,
                () -> stepGetUser(99999L));
    }

    @Test
    @Story("Update existing user")
    @Description("Verify that user details can be updated successfully")
    @Severity(SeverityLevel.CRITICAL)
    void updateUser_validData_updatesUser() throws UserNotFoundException {
        User user = stepCreateUser("Old", "old@test.com");

        stepUpdateUserName(user, "New");

        User updated = stepGetUser(user.getId());
        assertEquals("New", updated.getName());
    }

    @Test
    @Story("Delete existing user")
    @Description("Verify that a user is removed from the system after deletion")
    @Severity(SeverityLevel.CRITICAL)
    void deleteUser_existingUser_removesUser() throws UserNotFoundException {
        User user = stepCreateUser("ToDelete", "delete@test.com");

        stepDeleteUser(user.getId());

        assertThrows(UserNotFoundException.class,
                () -> stepGetUser(user.getId()));
    }

    // Allure Steps
    @Step("Create user with name={name}, email={email}")
    User stepCreateUser(String name, String email) {
        return userService.createUser(name, email);
    }

    @Step("Get user by id={id}")
    User stepGetUser(long id) throws UserNotFoundException {
        return userService.getUser(id);
    }

    @Step("Update user name to {newName}")
    void stepUpdateUserName(User user, String newName) throws UserNotFoundException {
        user.setName(newName);
        userService.updateUser(user);
    }

    @Step("Delete user with id={id}")
    void stepDeleteUser(long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }
}
