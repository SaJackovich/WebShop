package web.sender;

import entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSPRegistrationSender {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Integer captchaId = 0;
    private boolean isCaptcha = false;
    private boolean isLogin = false;
    private User oldInput;

    public JSPRegistrationSender(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public User getOldInput() {
        return oldInput;
    }

    public JSPRegistrationSender setOldInput(User oldInput) {
        this.oldInput = oldInput;
        return this;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public JSPRegistrationSender setRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public JSPRegistrationSender setResponse(HttpServletResponse response) {
        this.response = response;
        return this;
    }

    public Integer getCaptchaId() {
        return captchaId;
    }

    public JSPRegistrationSender setCaptchaId(Integer captchaId) {
        this.captchaId = captchaId;
        return this;
    }

    public boolean isCaptcha() {
        return isCaptcha;
    }

    public JSPRegistrationSender setCaptcha(boolean captcha) {
        isCaptcha = captcha;
        return this;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public JSPRegistrationSender setLogin(boolean email) {
        isLogin = email;
        return this;
    }

    public void send(){
        request.setAttribute("user", oldInput);
        request.getSession().setAttribute("sessionCaptchaId", captchaId);
        request.setAttribute("requestCaptchaId", captchaId);
        request.setAttribute("isCaptcha", isCaptcha);
        request.setAttribute("isLoginExist", isLogin);
        request.getSession().setAttribute("hiddenCaptcha", captchaId);
    }

}
