package com.tcs.users.controller;

import com.tcs.users.dto.UsersEntry;
import com.tcs.users.model.Users;
import com.tcs.users.repository.UsersRepository;
import com.tcs.users.service.UsersService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    private static final Logger log = LoggerFactory. getLogger(UsersController.class);

    @GetMapping(value = "/getUsers")
    @ResponseBody
    public List<Users> getUsers(){
        log.info("Getting all the Users from the catalog");
      return usersService.getUsers();
    }

    @PostMapping(value = "/postUser", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UsersEntry request){
        usersService.createUser(request);
        log.info("New User Was created" + HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateUserName/{id}")
    @ResponseBody
    public Users updateUser(@PathVariable Long id, @RequestBody UsersEntry request){
        log.info("Updating the user with Id " + id);
        return usersService.updateUser(id, request);
    }

    @GetMapping(value = "/getUser/{id}")
    @ResponseBody
    public Optional<Users> getUserById(@PathVariable Long id){
        log.info("Getting User with Id: " + id);
        return usersService.getUserById(id);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteUserById(@PathVariable Long id){
        usersService.deleteById(id);
        log.info("User with Id: " + id + "was successfully" + HttpStatus.GONE);
    }

    @GetMapping(value = "/version")
    public String getVersion(){return "version 1.0";}

}
