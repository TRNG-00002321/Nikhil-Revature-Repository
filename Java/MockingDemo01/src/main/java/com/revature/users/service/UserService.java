package com.revature.users.service;

import com.revature.users.dao.UserRepository;
import com.revature.users.model.User;

public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id)
    {
        return userRepository.findbyId(id);
    }

    public boolean register(User user)
    {
        if(userRepository.findByEmail(user.getEmail()) != null)
        {
            return false;
        }
        userRepository.save(user);
        return  true;
    }
}
