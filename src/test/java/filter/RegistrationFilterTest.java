//package filter;
//
//import com.example.demo1.controller.filter.RegistrationFilter;
//import com.example.demo1.model.dao.UserDao;
//import com.example.demo1.model.exceptions.LoginAlreadyExistsException;
//import com.example.demo1.model.services.UserService;
//import com.example.demo1.model.services.impl.UserServiceImpl;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import setup.DbSetupTest;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import static org.mockito.Mockito.*;
//
///**
// * @author Ivan Mieshkov
// */
//public class RegistrationFilterTest {
//
//    HttpServletRequest request = mock(HttpServletRequest.class);
//    HttpServletResponse response = mock(HttpServletResponse.class);
//    FilterChain filterChain = mock(FilterChain.class);
//    UserDao userDao = mock(UserDao.class);
//    RegistrationFilter registrationFilter = new RegistrationFilter();
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
//    public void shouldRegisterNewUser() throws IOException, ServletException {
//
//        when(request.getParameter("login")).thenReturn("000003");
//        when(request.getParameter("nameEn")).thenReturn("Ivan Ivanov");
//        when(request.getParameter("nameUkr")).thenReturn("Іван Іванов");
//        when(request.getParameter("password")).thenReturn("123321");
//        when(request.getParameter("email")).thenReturn("ivanov@googlemail.com");
//        when(request.getParameter("address")).thenReturn("Solar system, Earth");
//        when(request.getParameter("phone")).thenReturn("+178963345956");
//
//        when(request.setAttribute("clients")).thenReturn()
////        registrationFilter.doFilter(request, response, filterChain);
//        userService.registerUser();
//        Assert.assertEquals("000003", userService.findByLogin("000003").getLogin());
////        verify(request, times(1))
////                        .getRequestDispatcher("/WEB-INF/view/admin-processing/users-list.jsp");
//        Assert.assertEquals("/WEB-INF/view/admin-processing/users-list.jsp",response.getLastRedirect());
//    }
//
//    @Test(expected = LoginAlreadyExistsException.class)
//    public void registerNewUserFailed() throws IOException, ServletException{
//
//        when(request.getParameter("login")).thenReturn("000003");
//        when(request.getParameter("nameEn")).thenReturn("Ivan Ivanov");
//        when(request.getParameter("nameUkr")).thenReturn("Іван Іванов");
//        when(request.getParameter("password")).thenReturn("123321");
//        when(request.getParameter("email")).thenReturn("ivanov@googlemail.com");
//        when(request.getParameter("address")).thenReturn("Solar system, Earth");
//        when(request.getParameter("phone")).thenReturn("+178963345956");
//
//        registrationFilter.doFilter(request, response, filterChain);
//
//    }
//
//}
