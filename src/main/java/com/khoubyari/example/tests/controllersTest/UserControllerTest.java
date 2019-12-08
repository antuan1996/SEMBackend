package com.khoubyari.example.tests.controllersTest;

import com.khoubyari.example.api.rest.UserController;
import com.khoubyari.example.dao.jpa.UserRepository;
import com.khoubyari.example.domain.Task;
import com.khoubyari.example.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserController userController;

    @Test
    public void testGetUsersList(){
        User user = new User();
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);
        ArrayList<User> result = userController.getUsers();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testCreateUser(){
        User sendUser = new User();
        String password = "password";
        sendUser.setId(10);
        sendUser.setPassword(password);
        User responseUser = new User();
        when(userRepository.save(any(User.class))).thenReturn(responseUser);
        when(passwordEncoder.encode(anyString())).thenReturn("123");
        userController.createUser(sendUser);
        assertEquals(sendUser.getPassword(), "123");
    }

    @Test
    public void testRetrieveUser(){
        long id = 10;
        User user = new User();
        user.setId(id);
        when(userRepository.findOne(id)).thenReturn(user);
        User result = userController.retrieveUser(id);
        assertEquals(result.getId(), id);
    }

    @Test
    public void testUpdateUser(){
        long searchId = 10;
        User requestUser = new User();
        requestUser.setId(searchId);
        requestUser.setPassword("123");
        User updatedUser = new User();
        updatedUser.setId(searchId);
        updatedUser.setPassword("456");
        when(userRepository.findOne(searchId)).thenReturn(requestUser);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
        when(passwordEncoder.encode(anyString())).thenReturn("456");
        User result = userController.updateUsers(searchId, requestUser);
        assertEquals(result.getPassword(), updatedUser.getPassword());
        verify(passwordEncoder).encode("123");
    }

    @Test
    public void testDeleteUser(){
        long id = 10;
        User user = new User();
        user.setId(id);
        when(userRepository.findOne(id)).thenReturn(user);
        userController.deleteUsers(id);
        verify(userRepository).delete(id);
    }
}
