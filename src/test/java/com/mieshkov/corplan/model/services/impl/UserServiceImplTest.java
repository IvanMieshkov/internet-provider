//package com.mieshkov.corplan.model.services.impl;

//public class UserServiceImplTest {
//
//    UserServiceImpl userService = new UserServiceImpl();
//
//    @Before
//    public void setUp() {
//        DbSetupTest.setUpDataBase();
//    }
//
//    @After
//    public void tearDown() {
//        DbSetupTest.tearDownDataBase();
//    }
//
//    @Test
//    public void showAllUsers() {
//        List<User> users = userService.showAllUsers();
//        Assert.assertEquals(2, users.size());
//    }
//    __________________________________

    //deprecated(заменил поиск по логину на поиск по ид
//    @Test
//    public void findByLogin() {
//        User user = userService.findByLogin("000002");
//        Assert.assertEquals("user", user.getFullNameEn());
//    }

//    @Test
//    public void registerUser() {
//        User user = new User();
//        user.setLogin("123456");
//        user.setFullNameEn("New User");
//        user.setFullNameUkr("Новий Користувач");
//        user.setPassword("123456");
//        user.setEmail("newUser@gmail.com");
//        user.setAddress("some address");
//        user.setPhoneNumber("+380502357896");
//        user.setRole("CLIENT");
//
//        userService.registerUser(user);
//
//    }

//}