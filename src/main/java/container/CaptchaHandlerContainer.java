package container;

import entity.Captcha;
import handler.captcha.CaptchaHandler;
import handler.captcha.impl.CookieCaptchaHandler;
import handler.captcha.impl.HiddenFieldCaptchaHandler;
import handler.captcha.impl.SessionCaptchaHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CaptchaHandlerContainer {

    private Map<String, CaptchaHandler> handlers = new HashMap<>();
    private final Map<Integer, Captcha> captches = new LinkedHashMap<>();

    public CaptchaHandlerContainer() {
        handlersInit();
    }

    private void handlersInit() {
        handlers.put(ContextConstant.HIDDEN_FIELD_CAPTCHA_HANDLER, new HiddenFieldCaptchaHandler(captches));
        handlers.put(ContextConstant.COOKIE_CAPTCHA_HANDLER, new CookieCaptchaHandler(captches));
        handlers.put(ContextConstant.SESSION_CAPTCHA_HANDLER, new SessionCaptchaHandler(captches));
    }

    public CaptchaHandler getCaptchaHandler(String handlerName){
        return handlers.get(handlerName);
    }
}
