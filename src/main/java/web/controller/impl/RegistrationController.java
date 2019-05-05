package web.controller.impl;

import container.ContextConstant;
import container.ControllerConstant;
import web.controller.AbstractController;
import web.bean.RegistrationForm;
import entity.Avatar;
import entity.Captcha;
import entity.User;
import handler.captcha.CaptchaHandler;
import service.AvatarService;
import web.sender.JSPRegistrationSender;
import service.CaptchaService;
import service.UserService;

import javax.naming.directory.NoSuchAttributeException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@MultipartConfig
@WebServlet("/registration")
public class RegistrationController extends AbstractController {

    private CaptchaService captchaService;
    private UserService userService;
    private CaptchaHandler captchaHandler;
    private AvatarService avatarService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        captchaService = (CaptchaService) context.getAttribute(ContextConstant.CAPTCHA_SERVICE);
        userService = (UserService) context.getAttribute(ContextConstant.USER_SERVICE);
        avatarService = (AvatarService) context.getAttribute(ContextConstant.AVATAR_SERVICE);
        captchaHandler = (CaptchaHandler) config.getServletContext().getAttribute(ContextConstant.CAPTCHA_PRESERVER);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        captchaService.removeOldCaptcha();
        getSenderWithNewCaptcha(request, response).send();
        forwardToAccess(request, response, ControllerConstant.REGISTRATION_JSP);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        captchaService.removeOldCaptcha();
        String userInputLogin = request.getParameter(ControllerConstant.USER_LOGIN);
        if (userService.containsLogin(userInputLogin)) {
            returnToRegistrationByLoginExist(request, response);
        } else if (captchaService.checkCaptchaOnValid(request, captchaHandler)) {
            addUserInDatabase(request, response);
        } else {
            returnToRegistrationByInvalidCaptcha(request, response);
        }
    }

    private void addUserInDatabase(HttpServletRequest request, HttpServletResponse response) {
        Optional<Avatar> avatar = avatarService.create(request);
        if (avatar.isPresent()){
            addUserWithAvatar(request, avatar.get());
        } else {
            addUserWithoutAvatar(request);
        }
        forwardToPage(response, request, ControllerConstant.MAIN_JSP);
    }

    private void addUserWithoutAvatar(HttpServletRequest request) {
        Optional<User> optionalUser = userService.add(request, "");
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            request.getSession().setAttribute(ControllerConstant.USER_ID, user.getId());
            request.getSession().setAttribute(ControllerConstant.USER_NAME, getFullUserName(user));
            request.getSession().setAttribute(ControllerConstant.USER_AVATAR, ControllerConstant.NO_AVATAR_NAME);
        }
    }

    private void addUserWithAvatar(HttpServletRequest request, Avatar avatar){
        Optional<User> optionalUser = userService.add(request, avatar.getFullFileName());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String fullName = avatar.getFullFileName().replace("\\", "/");
            request.getSession().setAttribute(ControllerConstant.USER_NAME, getFullUserName(user));
            request.getSession().setAttribute(ControllerConstant.USER_ID, user.getId());
            request.getSession().setAttribute(ControllerConstant.USER_AVATAR, fullName);
        }
    }

    private void returnToRegistrationByInvalidCaptcha(HttpServletRequest request, HttpServletResponse response) {
        getSenderWithNewCaptcha(request, response)
                .setCaptcha(Boolean.TRUE)
                .send();
        User oldDataUser = new RegistrationForm().createUserByRequest(request);
        request.setAttribute(ControllerConstant.USER, oldDataUser);
        forwardToAccess(request, response, ControllerConstant.REGISTRATION_JSP);
    }

    private void returnToRegistrationByLoginExist(HttpServletRequest request, HttpServletResponse response){
        getSenderWithNewCaptcha(request, response)
                .setLogin(Boolean.TRUE)
                .send();
        User oldDataUser = new RegistrationForm().createUserByRequest(request);
        request.setAttribute(ControllerConstant.USER, oldDataUser);
        forwardToAccess(request, response, ControllerConstant.REGISTRATION_JSP);
    }

    private JSPRegistrationSender getSenderWithNewCaptcha(HttpServletRequest request, HttpServletResponse response) {
        JSPRegistrationSender sender = new JSPRegistrationSender(request, response);
        try {
            Captcha captcha = captchaService.create();
            captchaHandler.addCaptcha(request, response, captcha);
            sender.setCaptchaId(captcha.getId());
        } catch (NoSuchAttributeException e) {
            log.error(ControllerConstant.CAPTCHA_NOT_CREATED);
        }
        User oldDataUser = new RegistrationForm().createUserByRequest(request);
        sender.setOldInput(oldDataUser);
        return sender;
    }

}
