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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CookieCaptchaHandlerTest {

    private static final String[] CAPTCHA_NAMES = new String[]{
            "captcha2", "captcha6", "captcha12", "captcha0",
            "asfs43", "captcha5"
    };
    private static final String[] CAPTCHA_VALUES = new String[]{
            "5", "4", "10", "23", "11", "45"
    };
    private Cookie[] cookies;

    @Mock
    Captcha captcha;

    @Mock
    Map<Integer, Captcha> captchaMap;

    @Mock
    HttpServletRequest request;

    @InjectMocks
    CookieCaptchaHandler captchaHandler;

    @Before
    public void setUp() {
        Mockito.when(captchaMap.get(any())).thenReturn(captcha);
        cookieInit();
        Mockito.when(request.getCookies()).thenReturn(cookies);
    }

    private void cookieInit() {
        cookies = new Cookie[CAPTCHA_NAMES.length];
        for (int i = 0; i < CAPTCHA_NAMES.length; i++) {
            cookies[i] = new Cookie(CAPTCHA_NAMES[i], CAPTCHA_VALUES[i]);
        }
    }

    @Test
    public void getCaptchaByOldestCookieValue_whenCallGetCaptcha() throws SessionTimeOutException {
        Mockito.when(captcha.isValid()).thenReturn(true);
        Assert.assertEquals(captcha, captchaHandler.getCaptcha(request));
    }

    @Test(expected = SessionTimeOutException.class)
    public void getException_whenFindCaptchaButTimedOut() throws SessionTimeOutException {
        Mockito.when(captcha.isValid()).thenReturn(false);
        captchaHandler.getCaptcha(request);
    }
}