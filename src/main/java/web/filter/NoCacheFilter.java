package web.filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "NoCacheFilter")
public class NoCacheFilter implements Filter {

    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String NO_CACHE_NO_STORE = "no-cache, no-store";
    private static final String PRAGMA = "Pragma";
    private static final String NO_CACHE = "no-cache";
    private static final String EXPIRES = "Expires";
    private static final String CACHE_FILTER_WAS_INIT = "Cache filter was init";
    private static final String CACHE_FILTER_WAS_DESTROYED = "Cache filter was destroyed";

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) {
        log.info(CACHE_FILTER_WAS_INIT);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader(CACHE_CONTROL, NO_CACHE_NO_STORE);
        resp.setHeader(PRAGMA, NO_CACHE);
        resp.setDateHeader(EXPIRES, 0);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        log.info(CACHE_FILTER_WAS_DESTROYED);
    }

}
