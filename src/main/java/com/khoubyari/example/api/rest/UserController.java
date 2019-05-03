package com.khoubyari.example.api.rest;

import com.khoubyari.example.dao.jpa.UserRepository;
import com.khoubyari.example.domain.User;
import com.khoubyari.example.exception.DataFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractRestHandler {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    User createUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<User> getUsers(){
        return (ArrayList<User>) userRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody User retrieveUser(@PathVariable("id") Long id) {
        User user = userRepository.findOne(id);
        checkResourceFound(user);
        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody User updateUsers(@PathVariable("id") Long id, @RequestBody User user){
        User oldUser = userRepository.findOne(id);
        checkResourceFound(oldUser);
        if (id != user.getId()) throw new DataFormatException("ID doesn't match!");
        if(user.getPassword() == null) {
            user.setPassword(oldUser.getPassword());
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsers(@PathVariable("id") Long id){
        checkResourceFound(userRepository.findOne(id));
        userRepository.delete(id);
    }
}
