package web.controller.impl;

import container.ControllerConstant;
import web.controller.AbstractController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute(ControllerConstant.USER_ID, ControllerConstant.NO_AVATAR_ID);
        req.getSession().setAttribute(ControllerConstant.USER_AVATAR, ControllerConstant.NO_AVATAR_NAME);
        forwardToPage(resp, req, ControllerConstant.MAIN_JSP);
    }
}
