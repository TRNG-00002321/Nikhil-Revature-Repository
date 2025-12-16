import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserService
{

    private final Map<Long, User> users = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public User createUser(String name, String email) {
        validateName(name);
        validateEmail(email);

        User user = new User();
        user.setId(idGenerator.getAndIncrement());
        user.setName(name);
        user.setEmail(email);
        user.setActive(true);

        users.put(user.getId(), user);
        return user;
    }

    public User getUser(long id) throws UserNotFoundException {
        User user = users.get(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return user;
    }

    public void updateUser(User user) throws UserNotFoundException {
        if (user == null || !users.containsKey(user.getId())) {
            throw new UserNotFoundException("User not found for update");
        }
        users.put(user.getId(), user);
    }

    public void deleteUser(long id) throws UserNotFoundException {
        if (!users.containsKey(id)) {
            throw new UserNotFoundException("User not found for deletion");
        }
        users.remove(id);
    }

    // ------------------------
    // Validation helpers
    // ------------------------

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}
