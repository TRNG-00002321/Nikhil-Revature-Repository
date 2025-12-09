package com.revature.users;

import com.revature.users.dao.UserRepository;
import com.revature.users.model.User;
import com.revature.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
    @Mock //Creates the mocked UserRepository
    private UserRepository repository;
    @InjectMocks //Injects the mocked UserRepository into UserService
    private UserService service;
    private User existingUser;
    private User newUser;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp()
    {
        existingUser = new User(1L, "Imram R", "imram@email.com");
        newUser = new User(null, "Sunni M", "sunni@email.com");
    }

    @Test
    public void testUserById_positive()
    {
        // Arrange
        when(repository.findbyId(1L)).thenReturn(existingUser);
        // Act
        User foundUser = service.getUserById(1L);
        // Assert
        assertEquals("Imram R", foundUser.getName());
    }
    @Test
    void register_WhenEmailDoesNotExist_ShouldReturnTrueAndSaveUser() {
        // Arrange
        User user = new User();
        user.setEmail("sunni@email.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

        // Act
        boolean result = userService.register(user);

        // Assert
        assertTrue(result);
        verify(userRepository).findByEmail(user.getEmail());
        verify(userRepository).save(user);
    }

    @Test
    void register_WhenEmailAlreadyExists_ShouldReturnFalseAndNotSaveUser() {
        // Arrange
        User newUser = new User();
        newUser.setEmail("imram@email.com");

        User existingUser = new User();
        existingUser.setEmail("imram@email.com");  // Same email as newUser

        when(userRepository.findByEmail(newUser.getEmail())).thenReturn(existingUser);

        // Act
        boolean result = userService.register(newUser);

        // Assert
        assertFalse(result);
        verify(userRepository).findByEmail(newUser.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }
}
