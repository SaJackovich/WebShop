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
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class HiddenFieldCaptchaHandlerTest {

    private static final String HIDDEN_FIELD_VALUE = "10";

    @Mock
    private Captcha captcha;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Map<Integer, Captcha> captchas;

    @InjectMocks
    private HiddenFieldCaptchaHandler handler;

    @Before
    public void setUp() {
        Mockito.when(request.getParameter(any())).thenReturn(HIDDEN_FIELD_VALUE);
        Mockito.when(captchas.get(any())).thenReturn(captcha);
    }

    @Test
    public void getCaptchaByIdInHiddenField_whenCallGetCaptcha() throws SessionTimeOutException {
        Mockito.when(captcha.isValid()).thenReturn(true);
        Assert.assertEquals(captcha, handler.getCaptcha(request));
    }

    @Test(expected = SessionTimeOutException.class)
    public void throwException_getCaptchaByIdInHiddenFieldButTimeOut() throws SessionTimeOutException {
        Mockito.when(captcha.isValid()).thenReturn(false);
        handler.getCaptcha(request);
    }
}