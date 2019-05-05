package web.controller.impl;

import container.ContextConstant;
import container.ControllerConstant;
import web.controller.AbstractController;
import entity.User;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
@MultipartConfig
public class LoginController extends AbstractController {

    private static final String ENTER_INVALID_DATA = "User enter invalid password or login";
    private static final String IS_LOGIN = "isLogin";

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute(ContextConstant.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        forwardToAccess(req, resp, ControllerConstant.LOGIN_JSP);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String login = req.getParameter(ControllerConstant.USER_LOGIN);
        if (userService.containsLogin(login)){
            String password = req.getParameter(ControllerConstant.PASSWORD);
            Optional<User> optionalUser = userService.getUserByLoginAndPassword(login, password);
            if (optionalUser.isPresent()){
                enterUserInSite(resp, req, optionalUser.get());
            } else {
                returnUserInLoginPageByInvalidLoginOrPassword(req, resp);
            }
        } else {
            returnUserInLoginPageByInvalidLoginOrPassword(req, resp);
        }
    }

    private void enterUserInSite(HttpServletResponse response, HttpServletRequest request, User user) {
        request.getSession().setAttribute(ControllerConstant.USER_ID, user.getId());
        request.getSession().setAttribute(ControllerConstant.USER_NAME, getFullUserName(user));
        request.getSession().setAttribute(ControllerConstant.USER_AVATAR, user.getAvatarFullname());
        redirectUserToMainPage(response);
    }

    private void redirectUserToMainPage(HttpServletResponse response) {
        try {
            response.sendRedirect(ControllerConstant.REDIRECT_SHOP_MAIN);
        } catch (IOException e) {
            log.error(ControllerConstant.NO_CONNECTION_TO_PAGE + ControllerConstant.PAGE_FOLDER + ControllerConstant.MAIN_JSP, e);
        }
    }

    private void returnUserInLoginPageByInvalidLoginOrPassword(HttpServletRequest request, HttpServletResponse response) {
        log.info(ENTER_INVALID_DATA);
        request.setAttribute(IS_LOGIN, Boolean.TRUE);
        forwardToAccess(request, response, ControllerConstant.LOGIN_JSP);
    }

}
