package handler.captcha;

import entity.Captcha;

import java.util.Map;

public abstract class AbstractCaptchaHandler implements CaptchaHandler {

    protected static final String CAPTCHA_ID = "captchaId";
    protected static final String HIDDEN_CAPTCHA = "hiddenCaptcha";

    protected Map<Integer, Captcha> captches;

    protected AbstractCaptchaHandler(Map<Integer, Captcha> captches) {
        this.captches = captches;
    }
}
