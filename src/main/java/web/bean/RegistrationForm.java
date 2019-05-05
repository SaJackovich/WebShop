package web.bean;

import container.ControllerConstant;
import entity.User;

import javax.servlet.http.HttpServletRequest;

public class RegistrationForm {

    public User createUserByRequest(HttpServletRequest request){
        return new User.UserBuilder().setLogin(request.getParameter(ControllerConstant.USER_LOGIN))
                .setEmail(request.getParameter(ControllerConstant.EMAIL))
                .setFirstName(request.getParameter(ControllerConstant.NAME))
                .setLastName(request.getParameter(ControllerConstant.LAST_NAME))
                .setPassword(request.getParameter(ControllerConstant.PASSWORD))
                .build();
    }

}
