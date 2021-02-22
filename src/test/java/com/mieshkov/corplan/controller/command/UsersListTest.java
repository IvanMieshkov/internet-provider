package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import setup.DbSetupTest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Ivan Mieshkov
 */
public class UsersListTest {
    @Before
    public void setUp() {
        DbSetupTest.setUpDataBase();
    }

    @After
    public void tearDown() {
        DbSetupTest.tearDownDataBase();
    }

    @Test
    public void getUsersListAndSetAttribute() {
        UserServiceImpl userService = mock(UserServiceImpl.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        UsersList usersList = new UsersList(userService);

        List<User> users = new ArrayList<>();
        users.add(new User());

        when(userService.showAllUsers()).thenReturn(users);
        usersList.execute(request);

        verify(userService, times(1)).showAllUsers();
        verify(request, times(1)).setAttribute("clients", users);
    }

}