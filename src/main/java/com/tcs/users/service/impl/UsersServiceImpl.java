package com.tcs.users.service.impl;

import com.tcs.users.controller.UsersController;
import com.tcs.users.dto.UsersEntry;
import com.tcs.users.model.Users;
import com.tcs.users.repository.UsersRepository;
import com.tcs.users.service.UsersService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    UsersRepository usersRepository;

    private static final Logger log = LoggerFactory. getLogger(UsersServiceImpl.class);

    public List<Users> getUsers(){
    return usersRepository.findAll();
    }


    public void createUser(UsersEntry request){
        if(request.getAge() != null) {
            Users users = mapper.map(request, Users.class);
            usersRepository.save(users);
        }else {
            request.setAge(calculateAge(request.getDateOfBirth()));
            log.info("Age is null... calculating Age with your Birth day");
            Users users = mapper.map(request, Users.class);
            usersRepository.save(users);

        }
        log.info("User Created Succesfully");
    }

    public Users updateUser(Long id, UsersEntry request){
        Optional<Users> userData = usersRepository.findById(id);
        if(!usersRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"NO USERS FOUND WITH THE GIVEN ID ");
        }else {
            if (userData.isPresent()) {
                Users userNewData = userData.get();
                userNewData.setName(request.getName());
                return usersRepository.save(userNewData);
            }
        }
        log.info("User Updated");
        return null;
    }

    public Optional<Users> getUserById(Long id) {

        log.info("Searching for the User with the Given Id");
        if(!usersRepository.existsById(id)){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "NO USERS FOUND WITH THE GIVEN ID");
        }else {
            return usersRepository.findById(id);
        }
    }

    public void deleteById(Long id){
        usersRepository.deleteById(id);
        System.out.println("User with " + id + "deleted");
    }

    public static int calculateAge(LocalDate date){

        LocalDate currentDate = LocalDate.now();

        if(date != null && currentDate != null){
            return Period.between(date, currentDate).getYears();
        }else return 0;
    }

}
