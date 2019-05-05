package handler.captcha.impl;

import entity.Captcha;
import exception.SessionTimeOutException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SessionCaptchaHandlerTest {

    private static final int CAPTCHA_ID = 5;

    @Mock
    private HttpSession session;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Captcha captcha;

    @Mock
    private Map<Integer, Captcha> captches;

    @InjectMocks
    private SessionCaptchaHandler handler;

    @Before
    public void setUp() {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(any())).thenReturn(CAPTCHA_ID);
        Mockito.when(captches.get(any())).thenReturn(captcha);
    }

    @Test
    public void getCaptchaByIdInSession_whenCallGetCaptcha() throws SessionTimeOutException {
        Mockito.when(captcha.isValid()).thenReturn(true);
        Assert.assertEquals(captcha, handler.getCaptcha(request));
    }

    @Test(expected = SessionTimeOutException.class)
    public void throwException_whenFindCaptchaByIdInSessionBUtTimeOut() throws SessionTimeOutException {
        Mockito.when(captcha.isValid()).thenReturn(false);
        handler.getCaptcha(request);
    }

}