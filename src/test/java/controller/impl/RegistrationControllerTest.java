package controller.impl;

import container.ControllerConstant;
import entity.Captcha;
import handler.captcha.CaptchaHandler;
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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import web.sender.JSPRegistrationSender;
import service.CaptchaService;
import service.UserService;
import web.controller.impl.RegistrationController;

import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    private static final int FIRST_LOG = 0;
    private static final String LOGIN = "log.in";
    private static final int CAPTCHA_ID = 10;
    private static final int ONE_TIME = 1;
    private TestAppender appender;
    private static final Logger logger = Logger.getRootLogger();

    @Mock
    private Captcha captcha;

    @Mock
    private JSPRegistrationSender sender;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private CaptchaService captchaService;

    @Mock
    private UserService userService;

    @Mock
    private HttpSession session;

    @Mock
    private CaptchaHandler captchaHandler;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private RegistrationController controller;

    @Before
    public void setUp() {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(dispatcher);
        Mockito.when(userService.containsLogin(LOGIN)).thenReturn(false);
        Mockito.when(request.getParameter(Mockito.anyString())).thenReturn(LOGIN);
        Mockito.when(captcha.getId()).thenReturn(CAPTCHA_ID);

        appender = new TestAppender();
        logger.addAppender(appender);
    }

    @Test
    public void addCaptchaAndGoToRegistrationPage_whenCallDoGet() throws NoSuchAttributeException, ServletException, IOException {
        Mockito.when(captchaService.create()).thenReturn(captcha);

        controller.doGet(request, response);

        Mockito.verify(dispatcher, Mockito.times(ONE_TIME)).forward(request, response);
    }

    @Test
    public void receiveLogError_whenCaptchaNotCreate() throws NoSuchAttributeException, ServletException, IOException {
        Mockito.when(captchaService.create()).thenThrow(new NoSuchAttributeException());

        controller.doGet(request, response);

        Mockito.verify(dispatcher, Mockito.times(ONE_TIME)).forward(request, response);
        List<LoggingEvent> log = appender.getLog();
        LoggingEvent firstLogEntry = log.get(FIRST_LOG);
        Assert.assertThat(firstLogEntry.getMessage(), CoreMatchers.is(ControllerConstant.CAPTCHA_NOT_CREATED));
    }

    @Test
    public void sendUserToRegistrationPage_whenInputLoginIsExistInDB() throws ServletException, IOException, NoSuchAttributeException {
        Mockito.when(captchaService.create()).thenReturn(captcha);
        Mockito.when(userService.containsLogin(LOGIN)).thenReturn(true);

        controller.doPost(request, response);

        Mockito.verify(dispatcher, Mockito.times(ONE_TIME)).forward(request, response);
    }

    @Test
    public void sendUserToMainShopPage_whenSuccessfulLogin() throws ServletException, IOException, NoSuchAttributeException {
        Mockito.when(captchaService.create()).thenReturn(captcha);

        controller.doPost(request, response);

        Mockito.verify(dispatcher, Mockito.times(ONE_TIME)).forward(request, response);
    }

    @Test
    public void sendUserToRegistrationPage_whenEnterInvalidCaptcha() throws ServletException, IOException, NoSuchAttributeException {
        Mockito.when(captchaService.create()).thenReturn(captcha);

        controller.doPost(request, response);

        Mockito.verify(dispatcher, Mockito.times(ONE_TIME)).forward(request, response);
    }

    @After
    public void setDown(){
        logger.removeAppender(appender);
    }

}