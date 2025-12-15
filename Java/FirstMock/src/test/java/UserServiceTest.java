import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)  // Enables Mockito annotations
class UserServiceTest
{
    @Mock
    private UserRepository repository;  // Mock the dependency

    @Mock
    private EmailClient emailClient;  // Mock the dependency

    @InjectMocks
    private UserService userService;  // Inject mocks automatically


    @Test
    void getUser_existingUser_returnsUser()
    {
        // Arrange: Configure the mock
        User expectedUser = new User(1L, "John", "john@test.com", true);
        expectedUser.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(expectedUser));

        // Act: Call the method under test
        User actualUser = userService.getUser(1L);

        // Assert: Verify the result
        assertEquals(expectedUser, actualUser);
        assertEquals("John", actualUser.getName());
    }

    @Test
    void getUser_nonExistentUser_throwsException()
    {
        // Arrange: Mock returns empty Optional
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserService.UserNotFoundException.class, () ->
        {
            userService.getUser(999L);
        });
    }

    @Test
    void createUser_validInput_returnsUserWithId()
    {
        // Arrange
        String name = "John";
        String email = "john@test.com";
        User savedUser = new User(name, email);
        savedUser.setId(1L);

        when(repository.existsByEmail(email)).thenReturn(false);
        when(repository.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = userService.createUser(name, email);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());
        assertTrue(result.isActive());

        verify(repository).existsByEmail(email);
        verify(repository).save(any(User.class));
        verify(emailClient).send(eq(email), anyString(), anyString());
    }

    @Test
    void createUser_duplicateEmail_throwsDuplicateUserException()
    {
        // Arrange
        String email = "john@test.com";
        when(repository.existsByEmail(email)).thenReturn(true);

        // Act & Assert
        assertThrows(UserService.DuplicateUserException.class, () -> {
            userService.createUser("John", email);
        });

        verify(repository).existsByEmail(email);
        verify(repository, never()).save(any(User.class));
        verify(emailClient, never()).send(anyString(), anyString(), anyString());
    }

    @Test
    void createUser_nullName_throwsIllegalArgumentException()
    {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(null, "john@test.com");
        });

        verify(repository, never()).existsByEmail(anyString());
        verify(repository, never()).save(any(User.class));
        verify(emailClient, never()).send(anyString(), anyString(), anyString());
    }

    @Test
    void createUser_emptyName_throwsIllegalArgumentException()
    {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser("", "john@test.com");
        });

        verify(repository, never()).existsByEmail(anyString());
        verify(repository, never()).save(any(User.class));
        verify(emailClient, never()).send(anyString(), anyString(), anyString());
    }

    @Test
    void createUser_nullEmail_throwsIllegalArgumentException()
    {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser("John", null);
        });

        verify(repository, never()).existsByEmail(anyString());
        verify(repository, never()).save(any(User.class));
        verify(emailClient, never()).send(anyString(), anyString(), anyString());
    }

    @Test
    void createUser_invalidEmail_throwsIllegalArgumentException()
    {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser("John", "invalid-email");
        });

        verify(repository, never()).existsByEmail(anyString());
        verify(repository, never()).save(any(User.class));
        verify(emailClient, never()).send(anyString(), anyString(), anyString());
    }

    @Test
    void getAllActiveUsers_hasActiveUsers_returnsActiveUsersList()
    {
        // Arrange
        User user1 = new User("Alice", "alice@test.com");
        user1.setId(1L);
        user1.setActive(true);

        User user2 = new User("Bob", "bob@test.com");
        user2.setId(2L);
        user2.setActive(true);

        User user3 = new User("Charlie", "charlie@test.com");
        user3.setId(3L);
        user3.setActive(true);

        java.util.List<User> activeUsers = java.util.Arrays.asList(user1, user2, user3);
        when(repository.findAllActive()).thenReturn(activeUsers);

        // Act
        java.util.List<User> result = userService.getAllActiveUsers();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(User::isActive));
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());
        assertEquals("Charlie", result.get(2).getName());

        verify(repository).findAllActive();
    }

    @Test
    void getAllActiveUsers_noActiveUsers_returnsEmptyList()
    {
        // Arrange
        when(repository.findAllActive()).thenReturn(java.util.Collections.emptyList());

        // Act
        java.util.List<User> result = userService.getAllActiveUsers();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        assertTrue(result.isEmpty());

        verify(repository).findAllActive();
    }

    // Test: Get user count - has users
    @Test
    void getUserCount_hasUsers_returnsCount()
    {
        // Arrange
        when(repository.count()).thenReturn(5L);

        // Act
        long count = userService.getUserCount();

        // Assert
        assertEquals(5L, count);
        verify(repository).count();
    }

    @Test
    void getUserCount_noUsers_returnsZero()
    {
        // Arrange
        when(repository.count()).thenReturn(0L);

        // Act
        long count = userService.getUserCount();

        // Assert
        assertEquals(0L, count);
        verify(repository).count();
    }
}
