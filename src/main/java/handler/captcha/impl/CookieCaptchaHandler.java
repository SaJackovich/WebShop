package handler.captcha.impl;

import container.CaptchaParameterContainer;
import entity.Captcha;
import exception.SessionTimeOutException;
import handler.captcha.AbstractCaptchaHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

public class CookieCaptchaHandler extends AbstractCaptchaHandler {

    private int oldestCookieId = 0;
    private Cookie oldestCookie;

    public CookieCaptchaHandler(Map<Integer, Captcha> captches) {
        super(captches);
    }

    @Override
    public void addCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        captches.put(captcha.getId(), captcha);
        response.addCookie(new Cookie(CaptchaParameterContainer.CAPTCHA +
                captcha.getId(),"" + captcha.getId()));
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) throws SessionTimeOutException {
        int cookieId = getOldestCaptchaIdByCookie(request);
        Captcha captcha = captches.get(cookieId);
        if (captcha.isValid()) {
            return captcha;
        }
        throw new SessionTimeOutException();
    }

    private int getOldestCaptchaIdByCookie(HttpServletRequest request) {
        Arrays.stream(request.getCookies())
                .filter(x -> x.getName().startsWith(CaptchaParameterContainer.CAPTCHA))
                .forEach(this::findValidCookie);
        return Integer.parseInt(oldestCookie.getValue());
    }

    private void findValidCookie(Cookie cookie) {
        if (oldestCookie == null) {
            firstCookie(cookie);
        } else {
            findOlderCookie(cookie);
        }
    }

    private void firstCookie(Cookie cookie) {
        oldestCookie = cookie;
        oldestCookieId = Integer.parseInt(cookie.getName()
                .replace(CaptchaParameterContainer.CAPTCHA, ""));
    }

    private void findOlderCookie(Cookie cookie) {
        int idCaptcha = Integer.parseInt(cookie.getName()
                .replace(CaptchaParameterContainer.CAPTCHA, ""));
        if (idCaptcha >= oldestCookieId) {
            oldestCookie = cookie;
            oldestCookieId = idCaptcha;
        }
    }

}
