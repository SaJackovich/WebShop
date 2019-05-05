package handler.captcha.impl;

import entity.Captcha;
import exception.SessionTimeOutException;
import handler.captcha.AbstractCaptchaHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HiddenFieldCaptchaHandler extends AbstractCaptchaHandler {

    public HiddenFieldCaptchaHandler(Map<Integer, Captcha> captches) {
        super(captches);
    }

    @Override
    public void addCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        captches.put(captcha.getId(), captcha);
        request.setAttribute(CAPTCHA_ID, captcha.getId());
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) throws SessionTimeOutException {
        String captchaId = request.getParameter(HIDDEN_CAPTCHA);
        Captcha captcha = captches.get(Integer.parseInt(captchaId));
        if (captcha.isValid()){
            return captcha;
        }
        throw new SessionTimeOutException();
    }
}
