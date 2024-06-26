package com.tcs.users.service;

import com.tcs.users.dto.UsersEntry;
import com.tcs.users.model.Users;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<Users> getUsers();

    void createUser(UsersEntry request);

    Users updateUser(Long id,  UsersEntry request);

    Optional<Users> getUserById(Long id);

    void deleteById(Long id);
}
