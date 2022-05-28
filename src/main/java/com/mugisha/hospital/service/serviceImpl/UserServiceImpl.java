package com.mugisha.hospital.service.serviceImpl;

import com.mugisha.hospital.entity.User;
import com.mugisha.hospital.repository.UserRepo;
import com.mugisha.hospital.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public void deleteUserById(Integer userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
