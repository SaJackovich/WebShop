package web.filter;

import org.apache.log4j.Logger;
import util.Routing;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Objects;

public class LocalizationFilter implements Filter {

    private static final String ENCODING = "UTF-8";
    private static final String URL = "URL";
    private static final String LOCALE = "locale";
    private static final String LANG = "lang";
    private static final String SESSION = "session";
    private static final String DEFAULT_LOCALE = "defaultLocale";
    private static final String DEFAULT_LOCALES = "defaultLocales";
    private static final String STORAGE_STRATEGY = "storageStrategy";
    private static final String COOKIE_TIME = "cookieTime";
    private static final String LOCALIZATION_FILTER_WAS_DESTROYED = "Localization filter was destroyed";

    private Locale customLocale;
    private Locale defaultLocale;
    private int expireTime;
    private String[] defaultLocales;
    private String storageStrategy;

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) {
        defaultLocale = Locale.forLanguageTag(filterConfig.getInitParameter(DEFAULT_LOCALE));
        defaultLocales = filterConfig.getInitParameter(DEFAULT_LOCALES).split(",");
        storageStrategy = filterConfig.getInitParameter(STORAGE_STRATEGY);
        expireTime = Integer.parseInt(filterConfig.getInitParameter(COOKIE_TIME));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        httpRequest.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        initLocale(request, httpRequest);
        checkBySessionOrCookie(httpRequest, response);
        HttpServletRequestWrapper wrapper = overridedWrapper(httpRequest, customLocale);
        setAttributeForLanguage(wrapper);
        chain.doFilter(wrapper, response);
    }

    private void initLocale(ServletRequest request, HttpServletRequest httpRequest) {
        if (Objects.isNull(request.getParameter(LANG))) {
            checkForSession(httpRequest);
        } else {
            customLocale = Locale.forLanguageTag(request.getParameter(LANG));
        }
    }

    private void checkForSession(HttpServletRequest httpRequest) {
        Locale locale = checkSessionAndCookies(httpRequest);
        if (Objects.isNull(locale)) {
            checkForBrowser(httpRequest);
        } else {
            customLocale = locale;
        }
    }

    private void checkForBrowser(HttpServletRequest httpRequest) {
        Locale browserLocale = checkBrowserLocales(httpRequest);
        if (Objects.isNull(browserLocale)) {
            customLocale = defaultLocale;
        } else {
            customLocale = browserLocale;
        }
    }

    private void checkBySessionOrCookie(HttpServletRequest httpRequest, ServletResponse response) {
        if (storageStrategy.equals(SESSION)) {
            httpRequest.getSession().setAttribute(LOCALE, customLocale);
        } else {
            saveInCookie((HttpServletResponse) response, customLocale.toString());
            httpRequest.setAttribute(LOCALE, customLocale);
        }
    }

    private Locale checkSessionAndCookies(HttpServletRequest request) {
        if (Objects.nonNull(request.getSession().getAttribute(LOCALE))) {
            customLocale = (Locale) request.getSession().getAttribute(LOCALE);
            return customLocale;
        } else {
            return initLocaleByCookie(request);
        }
    }

    private Locale initLocaleByCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.nonNull(cookies)) {
            return checkFroNecessaryCookie(cookies);
        }
        return null;
    }

    private Locale checkFroNecessaryCookie(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(LOCALE)) {
                customLocale = Locale.forLanguageTag(cookie.getValue());
                return customLocale;
            }
        }
        return null;
    }

    private Locale checkBrowserLocales(HttpServletRequest request) {
        Enumeration<Locale> locales = request.getLocales();
        while (locales.hasMoreElements()) {
            Locale locale = locales.nextElement();
            if (Arrays.asList(defaultLocales).contains(locale.toString())) {
                return locale;
            }
        }
        return null;
    }

    private HttpServletRequestWrapper overridedWrapper(HttpServletRequest request, Locale locale) {
        return new HttpServletRequestWrapper(request) {
            @Override
            public Locale getLocale() {
                return locale;
            }

            @Override
            public Enumeration<Locale> getLocales() {
                return Collections.enumeration(Collections.singletonList((locale)));
            }
        };
    }

    private void setAttributeForLanguage(HttpServletRequest req) {
        if (!new Routing().getPathFromURL(req.getRequestURL().toString()).isEmpty()) {
            if (req.getQueryString() == null) {
                req.setAttribute(URL, new Routing().getPathFromURL(req.getRequestURL().toString()) + "?");
            } else {
                req.setAttribute(URL, new Routing().getPathFromURL(req.getRequestURL().toString()) + "?"
                        + req.getQueryString().replaceFirst("&?lang=\\w+(?=&|\\s*)", ""));
            }
        } else {
            req.setAttribute(URL, ".?");
        }
    }

    private void saveInCookie(HttpServletResponse response, String customLocale) {
        Cookie cookie = new Cookie(LOCALE, customLocale);
        cookie.setMaxAge(expireTime);
        response.addCookie(new Cookie(LOCALE, customLocale));
    }

    @Override
    public void destroy() {
        log.info(LOCALIZATION_FILTER_WAS_DESTROYED);
    }

}
