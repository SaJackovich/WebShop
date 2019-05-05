package web.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class LocalizationFilterTest {

    private static final String RU_RU = "ru-RU";
    private static final String ES = "es";
    private static final String VN_VN_ES_ES = "vn_VN,es_ES";
    private static final String DEFAULT_LOCALE = "defaultLocale";
    private static final String DEFAULT_LOCALES = "defaultLocales";
    private static final String STORAGE_STRATEGY = "storageStrategy";
    private static final String SESSION = "session";
    private static final String COOKIE_TIME = "cookieTime";
    private static final String TIME = "36000";
    private static final String SHOP_MAIN = "/shop/main";
    private static final String LANG = "lang";
    private static final String LOCALE = "locale";

    private static final Cookie COOKIE = new Cookie("locale", "RU_RU");
    private static final String COUNTRY_ES = "ES";

    @Mock
    private Enumeration<Locale> locales;

    @Mock
    private HttpSession httpSession;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterConfig filterConfig;

    private LocalizationFilter localizationFilter;

    @Before
    public void setUp(){
        Mockito.when(filterConfig.getInitParameter(DEFAULT_LOCALE)).thenReturn(RU_RU);
        Mockito.when(filterConfig.getInitParameter(DEFAULT_LOCALES)).thenReturn(VN_VN_ES_ES);
        Mockito.when(filterConfig.getInitParameter(STORAGE_STRATEGY)).thenReturn(SESSION);
        Mockito.when(filterConfig.getInitParameter(COOKIE_TIME)).thenReturn(TIME);
        Mockito.when(request.getRequestURL()).thenReturn(new StringBuffer(SHOP_MAIN));
        localizationFilter = new LocalizationFilter();
        localizationFilter.init(filterConfig);
    }

    @Test
    public void changeLocaleByRequest_whenRequestHaveLangParameter() throws IOException, ServletException {
        Mockito.when(request.getParameter(anyString())).thenReturn(RU_RU);
        Mockito.when(request.getSession()).thenReturn(httpSession);

        localizationFilter.doFilter(request, response, filterChain);

        Mockito.verify(request, Mockito.times(1)).setAttribute(anyString(), any());
    }

    @Test
    public void changeLocaleBySession_whenSessionHaveAttributeLocale() throws IOException, ServletException {
        Mockito.when(request.getParameter(LANG)).thenReturn(null);
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(httpSession.getAttribute(LOCALE)).thenReturn(new Locale(RU_RU));

        localizationFilter.doFilter(request, response, filterChain);

        Mockito.verify(request, Mockito.times(1)).setAttribute(anyString(), any());
    }

    @Test
    public void changeLocaleByCookie_whenCookieHaveAttributeLocale() throws IOException, ServletException {
        Mockito.when(request.getParameter(LANG)).thenReturn(null);
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(httpSession.getAttribute(LOCALE)).thenReturn(null);
        Mockito.when(request.getCookies()).thenReturn(new Cookie[]{COOKIE});

        localizationFilter.doFilter(request, response, filterChain);

        Mockito.verify(request, Mockito.times(1)).setAttribute(anyString(), any());
    }

    @Test
    public void changeLocaleByBrowserLocale_whenNotHaveAnotherLocale() throws IOException, ServletException {
        Mockito.when(request.getParameter(LANG)).thenReturn(null);
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(httpSession.getAttribute(LOCALE)).thenReturn(null);
        Mockito.when(request.getCookies()).thenReturn(null);
        Mockito.when(request.getLocales()).thenReturn(locales);
        Mockito.when(locales.hasMoreElements()).thenReturn(true);
        Mockito.when(locales.nextElement()).thenReturn(new Locale(ES, COUNTRY_ES));

        localizationFilter.doFilter(request, response, filterChain);

        Mockito.verify(request, Mockito.times(1)).setAttribute(anyString(), any());
    }

}
