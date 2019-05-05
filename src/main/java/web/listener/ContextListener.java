package web.listener;

import container.CaptchaHandlerContainer;
import container.ContextConstant;
import db.dao.impl.DefaultAvatarDAO;
import db.dao.impl.DefaultUserDAO;
import db.dao.transaction.TransactionManager;
import db.repository.Repository;
import db.repository.impl.SQLManufacturerRepository;
import db.repository.impl.SQLOrderRepository;
import db.repository.impl.SQLProductRepository;
import db.repository.impl.SQlCategoryRepository;
import entity.Category;
import entity.Manufacturer;
import entity.Order;
import entity.OrderInformation;
import entity.Product;
import entity.ProductFilter;
import handler.captcha.CaptchaHandler;
import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import repository.CartRepository;
import repository.impl.CaptchaRepository;
import repository.impl.DefaultCartRepository;
import service.AvatarService;
import service.CaptchaService;
import service.CartProductService;
import service.CategoryService;
import service.ManufacturerService;
import service.OrderService;
import service.ProductService;
import service.UserService;
import service.impl.DefaultAvatarService;
import service.impl.DefaultCaptchaService;
import service.impl.DefaultCartProductService;
import service.impl.DefaultCategoryService;
import service.impl.DefaultManufacturerService;
import service.impl.DefaultOrderService;
import service.impl.DefaultProductService;
import service.impl.DefaultUserService;
import util.AppUtil;
import web.extractor.impl.MiniCartProductExtractor;
import web.extractor.impl.FilteredProductExtractor;
import web.extractor.RequestExtractor;
import web.extractor.impl.OrderInformationExtractor;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {

    private final Logger log = Logger.getLogger(this.getClass());
    private BasicDataSource dataSource = new BasicDataSource();
    private TransactionManager transactionManager;

    private Repository<Product> productRepository;
    private Repository<Category> categoryRepository;
    private Repository<Manufacturer> manufacturerRepository;
    private CartRepository<Product> cartRepository;
    private Repository<Order> orderRepository;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        captchaHandlerInit(context);
        connectionPoolInit();

        transactionInit();

        repositoriesInit();
        serviceInit(context);
        extractorInit(context);

    }

    private void repositoriesInit() {
        productRepository = new SQLProductRepository();
        categoryRepository = new SQlCategoryRepository();
        manufacturerRepository = new SQLManufacturerRepository();
        cartRepository = new DefaultCartRepository();
        orderRepository = new SQLOrderRepository();
    }

    private void extractorInit(ServletContext context) {
        RequestExtractor<ProductFilter> extractor = new FilteredProductExtractor();
        RequestExtractor<Map<Integer, Integer>> cartExtractor = new MiniCartProductExtractor();
        RequestExtractor<OrderInformation> orderInformationExtrator = new OrderInformationExtractor();
        context.setAttribute(ContextConstant.FILTERED_PRODUCT_EXTRACTOR, extractor);
        context.setAttribute(ContextConstant.CART_PRODUCT_EXTRACTOR, cartExtractor);
        context.setAttribute(ContextConstant.ORDER_INFORMATION_EXTRACTOR, orderInformationExtrator);

    }

    private void captchaHandlerInit(ServletContext context) {
        String handlerName = context.getInitParameter(ContextConstant.CAPTCHA_HANDLER);
        CaptchaHandler handler = new CaptchaHandlerContainer().getCaptchaHandler(handlerName);
        context.setAttribute(ContextConstant.CAPTCHA_PRESERVER, handler);
    }

    private void serviceInit(ServletContext context) {
        ProductService productService = new DefaultProductService(transactionManager, productRepository);
        UserService userService = new DefaultUserService(transactionManager, new DefaultUserDAO());
        CaptchaService captchaService = new DefaultCaptchaService(new CaptchaRepository());
        AvatarService avatarService = new DefaultAvatarService(transactionManager, new DefaultAvatarDAO());
        CategoryService categoryService = new DefaultCategoryService(transactionManager, categoryRepository);
        ManufacturerService manufacturerService = new DefaultManufacturerService(transactionManager, manufacturerRepository);
        CartProductService cartProductService = new DefaultCartProductService(cartRepository);
        OrderService orderService = new DefaultOrderService(transactionManager, orderRepository);

        context.setAttribute(ContextConstant.USER_SERVICE, userService);
        context.setAttribute(ContextConstant.CAPTCHA_SERVICE, captchaService);
        context.setAttribute(ContextConstant.AVATAR_SERVICE, avatarService);
        context.setAttribute(ContextConstant.PRODUCT_SERVICE, productService);
        context.setAttribute(ContextConstant.CATEGORY_SERVICE, categoryService);
        context.setAttribute(ContextConstant.MANUFACTURER_SERVICE, manufacturerService);
        context.setAttribute(ContextConstant.CART_PRODUCT_SERVICE, cartProductService);
        context.setAttribute(ContextConstant.ORDER_SERVICE, orderService);
    }

    private void transactionInit() {
        transactionManager = new TransactionManager(dataSource);
    }

    private void connectionPoolInit() {
        Properties properties = getConnectionPoolProperties();
        dataSource.setDefaultAutoCommit(ContextConstant.DEFAULT_AUTO_COMMIT);
        dataSource.setRollbackOnReturn(ContextConstant.ROLLBACK_ON_RETURN);
        dataSource.setDriverClassName(properties.getProperty(ContextConstant.DRIVER));
        dataSource.setUrl(properties.getProperty(ContextConstant.URL));
        dataSource.setUsername(properties.getProperty(ContextConstant.USER_NAME));
        dataSource.setPassword(properties.getProperty(ContextConstant.DB_PASSWORD));
        dataSource.setInitialSize(Integer.parseInt(properties.getProperty(ContextConstant.INIT_SIZE)));
        dataSource.setMaxTotal(Integer.parseInt(properties.getProperty(ContextConstant.MAX_SIZE)));
    }

    private Properties getConnectionPoolProperties() {
        Properties properties = new Properties();
        AppUtil.loadProperties(properties, ContextConstant.CONNECTION_POOL_PROPERSTIES_FILE);
        return properties;
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        connectionPoolClose();
        log.info(ContextConstant.SERVLET_DESTROYED);
    }

    private void connectionPoolClose() {
        try {
            dataSource.close();
        } catch (SQLException e) {
            log.error(ContextConstant.CONNECTION_POOL_NOT_CLOSED);
        }
    }

}