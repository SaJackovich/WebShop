package handler.captcha;

import entity.Captcha;
import exception.SessionTimeOutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaHandler {

    void addCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha);

    Captcha getCaptcha(HttpServletRequest request) throws SessionTimeOutException;

}
