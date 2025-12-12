package com.revature.mock;

public class UserRepository
{
    private final MockDatabase database;

    public UserRepository(MockDatabase database) {
        this.database = database;
    }

    public void save(User user) {
        database.insert(user);
    }

    public User findById(int id) {
        return database.findById(id);
    }

    public int count() {
        return database.count();
    }
}

