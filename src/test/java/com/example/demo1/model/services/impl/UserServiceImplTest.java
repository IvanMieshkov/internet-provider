package com.example.demo1.model.services.impl;

import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.exceptions.EmailAlreadyExistsException;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import setup.DbSetupTest;

import java.util.List;

public class UserServiceImplTest {

    UserServiceImpl userService = new UserServiceImpl();

    @Before
    public void setUp() {
        DbSetupTest.setUpDataBase();
    }

    @After
    public void tearDown() {
        DbSetupTest.tearDownDataBase();
    }

    @Test
    public void showAllUsers() {
        List<User> users = userService.showAllUsers();
        Assert.assertEquals(2, users.size());
    }

    @Test
    public void registerUser() {
        User user = new User();
        user.setNameEn("New User");
        user.setNameUkr("Новий Користувач");
        user.setPassword("123456");
        user.setEmail("newUser@gmail.com");
        user.setAddress("some address");
        user.setPhoneNumber("+380502357896");
        user.setRole("CLIENT");

        try {
            userService.registerUser(user);
        } catch (EmailAlreadyExistsException e) {
            e.printStackTrace();
        }
        user = userService.findById(100003L);

        Assert.assertEquals("+380502357896", user.getPhoneNumber());
    }

    @Test
    public void updateActive() {
        userService.updateActive(100002L, false);
        Assert.assertEquals(false, userService.findById(100002L).getActive());
    }

}