package controller.impl;

import container.ControllerConstant;
import entity.Avatar;
import entity.User;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.AvatarService;
import service.UserService;
import web.controller.impl.LoginController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private static final String ENTER_USER_LOGIN = "login";
    private static final String ENTER_INVALID_PASSWORD_OR_LOGIN = "User enter invalid password or login";
    private static final int FIRST_LOG = 0;
    private static final String USER_PASSWORD = "password";
    private static final String AVATAR_ID_2 = "/images/main/a.jpg";
    private static final String NO_AVATAR = "";
    private TestAppender appender;
    private static final Logger logger = Logger.getRootLogger();

    @Mock
    private Avatar avatar;

    @Mock
    private HttpSession httpSession;

    @Mock
    private User user;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AvatarService avatarService;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setUp(){
        appender = new TestAppender();
        logger.addAppender(appender);
        when(request.getRequestDispatcher(any())).thenReturn(requestDispatcher);
        when(request.getParameter(any())).thenReturn(ENTER_USER_LOGIN);
    }

    @Test
    public void returnUserInLoginPage_whenNotHaveEnterLoginInDataBase(){
        when(userService.containsLogin(ENTER_USER_LOGIN)).thenReturn(false);

        loginController.doPost(request, response);

        List<LoggingEvent> log = appender.getLog();
        LoggingEvent firstLogEntry = log.get(FIRST_LOG);
        Assert.assertThat(firstLogEntry.getMessage(), CoreMatchers.is(ENTER_INVALID_PASSWORD_OR_LOGIN));
    }

    @Test
    public void returnUserInLoginPage_whenUserEnterInvalidPassword(){
        when(userService.containsLogin(ENTER_USER_LOGIN)).thenReturn(true);
        when(request.getParameter(ControllerConstant.PASSWORD)).thenReturn(USER_PASSWORD);
        when(userService.getUserByLoginAndPassword(ENTER_USER_LOGIN, USER_PASSWORD))
                .thenReturn(Optional.empty());

        loginController.doPost(request, response);

        List<LoggingEvent> log = appender.getLog();
        LoggingEvent firstLogEntry = log.get(FIRST_LOG);
        Assert.assertThat(firstLogEntry.getMessage(), CoreMatchers.is(ENTER_INVALID_PASSWORD_OR_LOGIN));
    }

    @Test
    public void enterUserInSiteWithoutAvatar_whenUserNotHaveAvatar(){
        when(userService.containsLogin(ENTER_USER_LOGIN)).thenReturn(true);
        when(request.getParameter(ControllerConstant.PASSWORD)).thenReturn(USER_PASSWORD);
        when(userService.getUserByLoginAndPassword(ENTER_USER_LOGIN, USER_PASSWORD))
                .thenReturn(Optional.of(user));
        when(request.getSession()).thenReturn(httpSession);
        when(user.getAvatarFullname()).thenReturn(NO_AVATAR);

        loginController.doPost(request, response);

        verify(httpSession, times(3)).setAttribute(any(), any());
    }

    @Test
    public void enterUserInSiteWithAvatar_whenUserHaveAvatar(){
        when(userService.containsLogin(ENTER_USER_LOGIN)).thenReturn(true);
        when(request.getParameter(ControllerConstant.PASSWORD)).thenReturn(USER_PASSWORD);
        when(userService.getUserByLoginAndPassword(ENTER_USER_LOGIN, USER_PASSWORD))
                .thenReturn(Optional.of(user));
        when(request.getSession()).thenReturn(httpSession);
        when(user.getAvatarFullname()).thenReturn(AVATAR_ID_2);
        loginController.doPost(request, response);

        verify(httpSession, times(3)).setAttribute(any(), any());
    }

    @After
    public void setDown(){
        logger.removeAppender(appender);
    }

}
