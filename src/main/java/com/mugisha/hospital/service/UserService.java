package com.mugisha.hospital.service;

import com.mugisha.hospital.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createUser(User user);
    List<User> getUsers();
    User updateUser(User user);
    User findUserById(Integer userId);
    void deleteUserById(Integer userId);
    User findUserByEmail(String email);
}
