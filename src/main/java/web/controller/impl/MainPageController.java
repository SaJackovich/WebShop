package web.controller.impl;

import container.ContextConstant;
import container.ControllerConstant;
import container.PageInputFieldConstant;
import entity.Category;
import entity.Manufacturer;
import entity.Product;
import entity.ProductFilter;
import service.CartProductService;
import service.CategoryService;
import service.ManufacturerService;
import service.ProductService;
import web.controller.AbstractController;
import web.extractor.impl.FilteredProductExtractor;
import web.extractor.RequestExtractor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/main")
public class MainPageController extends AbstractController {

    private static final String ENTER_SITE_LIKE_ANON = "User enter site like anon";

    private RequestExtractor<ProductFilter> productExtractor;
    private ProductService productService;
    private CategoryService categoryService;
    private ManufacturerService manufacturerService;
    private CartProductService<Product> cartProductService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        productExtractor = (FilteredProductExtractor) context.getAttribute(ContextConstant.FILTERED_PRODUCT_EXTRACTOR);
        productService = (ProductService) context.getAttribute(ContextConstant.PRODUCT_SERVICE);
        categoryService = (CategoryService) context.getAttribute(ContextConstant.CATEGORY_SERVICE);
        manufacturerService = (ManufacturerService) context.getAttribute(ContextConstant.MANUFACTURER_SERVICE);
        cartProductService = (CartProductService<Product>) context.getAttribute(ContextConstant.CART_PRODUCT_SERVICE);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        cartProductService.removeAll();
        checkForAnonUser(req);
        ProductFilter productFilter = productExtractor.extractFromRequest(req);
        addProductsToPage(req, productFilter);
        addFilterCategoriesToPage(req);
        addPaginationToPage(req, productFilter);
        req.getSession().setAttribute(PageInputFieldConstant.PRODUCT_FILTER_BEAN, productFilter);
        forwardToPage(resp, req, ControllerConstant.MAIN_JSP);
    }

    private void addPaginationToPage(HttpServletRequest req, ProductFilter productFilter) {
        int countProducts = productService.getFilteredProductsCount(productFilter);
        displayDesiredProductsPage(req, countProducts, productFilter);
    }

    private void addFilterCategoriesToPage(HttpServletRequest req) {
        List<Category> categories = categoryService.getAllCategories();
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        req.getSession().setAttribute(ControllerConstant.CATEGORIES, categories);
        req.getSession().setAttribute(ControllerConstant.MANUFACTURERS, manufacturers);
    }

    private void addProductsToPage(HttpServletRequest req, ProductFilter productFilter) {
        List<Product> filteredProducts = productService.getFilteredProducts(productFilter);
        req.getSession().setAttribute(ControllerConstant.PRODUCTS, filteredProducts);
    }

    private void checkForAnonUser(HttpServletRequest req){
        if (req.getSession().getAttribute(ControllerConstant.USER_ID) == null){
            req.getSession().setAttribute(ControllerConstant.USER_ID, ControllerConstant.NO_AVATAR_ID);
            req.getSession().setAttribute(ControllerConstant.USER_AVATAR, ControllerConstant.NO_AVATAR_NAME);
            log.info(ENTER_SITE_LIKE_ANON);
        }
    }

}
