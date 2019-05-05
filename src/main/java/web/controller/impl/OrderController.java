package web.controller.impl;

import container.ContextConstant;
import container.ControllerConstant;
import entity.CartProduct;
import entity.OrderInformation;
import entity.Product;
import service.CartProductService;
import service.OrderService;
import web.controller.AbstractController;
import web.extractor.RequestExtractor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/order")
public class OrderController extends AbstractController {

    private CartProductService<Product> cartProductService;
    private OrderService orderService;
    private RequestExtractor<OrderInformation> orderInformationExtractor;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        cartProductService = (CartProductService<Product>) context.getAttribute(ContextConstant.CART_PRODUCT_SERVICE);
        orderService = (OrderService) context.getAttribute(ContextConstant.ORDER_SERVICE);
        orderInformationExtractor = (RequestExtractor<OrderInformation>) context.getAttribute(ContextConstant.ORDER_INFORMATION_EXTRACTOR);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        int userId = (int) req.getSession().getAttribute(ControllerConstant.USER_ID);
        if (userId == 0){
            forwardToAccess(req, resp, ControllerConstant.LOGIN_JSP);
        } else{
            OrderInformation orderInformation = orderInformationExtractor.extractFromRequest(req);
            createOrder(orderInformation);
            cartProductService.removeAll();
            forwardToPage(resp, req, ControllerConstant.MAIN_JSP);
        }
    }

    private void createOrder(OrderInformation orderInformation) {
        List<CartProduct> cartProducts = cartProductService.getAll();
        orderService.add(cartProducts, orderInformation);
    }
}
