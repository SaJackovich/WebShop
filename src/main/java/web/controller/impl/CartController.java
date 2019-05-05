package web.controller.impl;

import container.ContextConstant;
import container.ControllerConstant;
import entity.CartProduct;
import entity.Product;
import service.CartProductService;
import service.ProductService;
import web.controller.AbstractController;
import web.extractor.RequestExtractor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@WebServlet("/cart")
public class CartController extends AbstractController {

    private static final String PRODUCTS = "products";

    private RequestExtractor<Map<Integer, Integer>> cartExtractor;
    private ProductService productService;
    private CartProductService<Product> cartProductService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        cartExtractor = (RequestExtractor<Map<Integer, Integer>>) context.getAttribute(ContextConstant.CART_PRODUCT_EXTRACTOR);
        productService = (ProductService) context.getAttribute(ContextConstant.PRODUCT_SERVICE);
        cartProductService = (CartProductService<Product>) context.getAttribute(ContextConstant.CART_PRODUCT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Map<Integer, Integer> idsAndCountProducts = cartExtractor.extractFromRequest(req);
        List<CartProduct> products = productService.getProductsByIds(idsAndCountProducts);
        cartProductService.add(products);
        List<CartProduct> cartProducts = cartProductService.getAll();
        req.setAttribute(PRODUCTS, cartProducts);
        forwardToPage(resp, req, ControllerConstant.CHECKOUT_JSP);
    }

}
