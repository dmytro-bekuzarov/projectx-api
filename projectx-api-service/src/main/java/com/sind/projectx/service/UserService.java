package com.sind.projectx.service;

import com.sind.projectx.domain.User;
import com.sind.projectx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Dmytro Bekuzarov
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User add(User user) {
        user.setId(UUID.randomUUID().toString());
        return userRepository.insert(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void deleteById(String userId) {
        userRepository.delete(userId);
    }

    public boolean exists(String userId) {
        return userRepository.exists(userId);
    }

}
