package controller.impl;

import container.ControllerConstant;
import entity.Product;
import entity.ProductFilter;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.CartProductService;
import service.CategoryService;
import service.ManufacturerService;
import service.ProductService;
import web.controller.impl.MainPageController;
import web.extractor.RequestExtractor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPageControllerTest {

    private static final int FIRST_LOG = 0;
    private static final String ENTER_SITE_LIKE_ANON = "User enter site like anon";
    private static final String USER_WITH_ID_2 = "2";
    private TestAppender appender;
    private static final Logger logger = Logger.getRootLogger();

    @Mock
    private ManufacturerService manufacturerService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductFilter productFilter;

    @Mock
    private ProductService productService;

    @Mock
    private RequestExtractor<ProductFilter> productExtractor;

    @Mock
    private CartProductService<Product> cartProductService;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private HttpSession httpSession;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private MainPageController mainPage;

    @Before
    public void setUp() {
        when(request.getSession()).thenReturn(httpSession);
        appender = new TestAppender();
        logger.addAppender(appender);
    }

    @Test
    public void userEnterSiteWithoutAvatar_whenNotLogin(){
        when(httpSession.getAttribute(ControllerConstant.USER_ID)).thenReturn(null);
        when(request.getRequestDispatcher(any())).thenReturn(requestDispatcher);
        when(productExtractor.extractFromRequest(request)).thenReturn(productFilter);
        when(productService.getFilteredProducts(productFilter)).thenReturn(new ArrayList<>());
        when(categoryService.getAllCategories()).thenReturn(new ArrayList<>());
        when(manufacturerService.getAllManufacturers()).thenReturn(new ArrayList<>());

        mainPage.doGet(request, response);

        List<LoggingEvent> log = appender.getLog();
        LoggingEvent firstLogEntry = log.get(FIRST_LOG);
        Assert.assertThat(firstLogEntry.getMessage(), CoreMatchers.is(ENTER_SITE_LIKE_ANON));
    }

    @After
    public void setDown(){
        logger.removeAppender(appender);
    }
}
