import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class UserRepositoryImplementation implements UserRepository
{
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            // Create new user
            user.setId(idGenerator.getAndIncrement());
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        users.remove(id);
    }

    @Override
    public List<User> findAllActive() {
        return users.values().stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByEmail(String email) {
        return users.values().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public long count() {
        return users.size();
    }

    // Example usage
    public static void main(String[] args) {
        UserRepository repo = new UserRepositoryImplementation();

        // Create users
        User user1 = new User(null, "alice@example.com", "Alice", true);
        User user2 = new User(null, "bob@example.com", "Bob", true);
        User user3 = new User(null, "charlie@example.com", "Charlie", false);

        repo.save(user1);
        repo.save(user2);
        repo.save(user3);

        // Find by ID
        System.out.println("Find by ID 1: " + repo.findById(1L));

        // Find all active users
        System.out.println("Active users: " + repo.findAllActive());

        // Check if email exists
        System.out.println("Email exists: " + repo.existsByEmail("alice@example.com"));

        // Count users
        System.out.println("Total users: " + repo.count());

        // Delete user
        repo.deleteById(2L);
        System.out.println("After deletion, total users: " + repo.count());
    }
}
