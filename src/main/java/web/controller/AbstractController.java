package web.controller;

import container.ControllerConstant;
import entity.ProductFilter;
import entity.User;
import org.apache.log4j.Logger;
import web.pagination.Pagination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AbstractController extends HttpServlet {

    private static final int LIMIT_JOURNEY_PER_PAGE = 10;
    private static final String PAGE = "page";

    protected final Logger log = Logger.getLogger(this.getClass());

    protected String getFullUserName(User user){
        return user.getLastName() + " " + user.getFirstName();
    }

    protected void forwardToErrorPage(HttpServletResponse response, HttpServletRequest request, String filename){
        try {
            request.setAttribute(PAGE, ControllerConstant.ERROR_FOLDER + filename);
            request.getRequestDispatcher(ControllerConstant.PAGE_TEMPLATE).forward(request, response);
        } catch (ServletException | IOException e) {
            log.error(ControllerConstant.NO_CONNECTION_TO_PAGE + ControllerConstant.PAGE_FOLDER + filename, e);
        }
    }

    protected void forwardToPage(HttpServletResponse response, HttpServletRequest request, String filename){
        try {
            request.setAttribute(PAGE, ControllerConstant.PAGE_FOLDER + filename);
            request.getRequestDispatcher(ControllerConstant.PAGE_TEMPLATE).forward(request, response);
        } catch (ServletException | IOException e) {
            log.error(ControllerConstant.NO_CONNECTION_TO_PAGE + ControllerConstant.PAGE_FOLDER + filename, e);
        }
    }

    protected void forwardToAccess(HttpServletRequest request, HttpServletResponse response, String filename){
        try {
            request.getRequestDispatcher(ControllerConstant.ACCESS_TEMPLATE + filename).forward(request, response);
        } catch (ServletException | IOException e) {
            log.error(ControllerConstant.NO_CONNECTION_TO_PAGE + ControllerConstant.ACCESS_TEMPLATE + filename, e);
        }
    }

    protected void displayDesiredProductsPage(HttpServletRequest request, int productCount, ProductFilter filter){
        String urlParameters = request.getQueryString() != null ? request.getQueryString() : "";
        String url = request.getRequestURL() + "?" + urlParameters;
        int limit = filter.getProductLimit() != 0 ? filter.getProductLimit()
                : LIMIT_JOURNEY_PER_PAGE;
        int offset = (filter.getPage() - 1) * limit;
        String parameterSeparator = url.contains("?") ? "&" : "?";
        Pagination pagination = new Pagination.Builder(url + parameterSeparator, offset,
                productCount).withLimit(limit).build();
        request.setAttribute(ControllerConstant.PAGINATION, pagination);
    }
    
}
