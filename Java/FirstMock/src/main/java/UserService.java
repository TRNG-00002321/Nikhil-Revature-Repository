import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository repository;
    private final EmailClient emailClient;

    /**
     * Custom exception for user not found scenarios
     */
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }

        public UserNotFoundException(Long id) {
            super("User not found with id: " + id);
        }
    }

    /**
     * Custom exception for duplicate user scenarios
     */
    public static class DuplicateUserException extends RuntimeException {

        public DuplicateUserException(String email) {
            super("User with email " + email + " already exists");
        }
    }

    public UserService(UserRepository repository, EmailClient emailClient) {
        this.repository = repository;
        this.emailClient = emailClient;
    }

    /**
     * Gets a user by ID
     * @throws UserNotFoundException if user not found
     */
    public User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Creates a new user and sends a welcome email
     */
    public User createUser(String name, String email) {
        // Validate input
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }

        // Check if email already exists
        if (repository.existsByEmail(email)) {
            throw new DuplicateUserException(email);
        }

        // Create and save the user
        User user = new User(name, email);
        User savedUser = repository.save(user);

        // Send welcome email
        emailClient.send(
                email,
                "Welcome to Our Platform",
                "Hello " + name + ",\n\nWelcome to our platform! Your account has been created successfully."
        );

        return savedUser;
    }

    /**
     * Simple email validation
     */
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    /**
     * Updates an existing user
     */
    public User updateUser(Long id, String name, String email) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Check if new email conflicts with another user
        if (!user.getEmail().equals(email) && repository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email " + email + " is already in use");
        }

        user.setName(name);
        user.setEmail(email);
        return repository.save(user);
    }

    /**
     * Deactivates a user instead of deleting
     */
    public void deactivateUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setActive(false);
        repository.save(user);

        // Send deactivation email
        emailClient.send(
                user.getEmail(),
                "Account Deactivated",
                "Hello " + user.getName() + ",\n\nYour account has been deactivated."
        );
    }

    /**
     * Permanently deletes a user
     */
    public void deleteUser(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }

    /**
     * Gets all active users
     */
    public List<User> getAllActiveUsers() {
        return repository.findAllActive();
    }

    /**
     * Gets total user count
     */
    public long getUserCount() {
        return repository.count();
    }

    /**
     * Reactivates a deactivated user
     */
    public void reactivateUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setActive(true);
        repository.save(user);

        // Send reactivation email
        emailClient.send(
                user.getEmail(),
                "Account Reactivated",
                "Hello " + user.getName() + ",\n\nYour account has been reactivated. Welcome back!"
        );
    }

    /**
     * Checks if a user exists by email
     */
    public boolean userExistsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}