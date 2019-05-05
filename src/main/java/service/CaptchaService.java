package service;

import entity.Captcha;
import handler.captcha.CaptchaHandler;

import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.http.HttpServletRequest;

public interface CaptchaService extends Service<Captcha>{

    Captcha create() throws NoSuchAttributeException;

    void removeOldCaptcha();

    boolean checkCaptchaOnValid(HttpServletRequest request, CaptchaHandler handler);

}
