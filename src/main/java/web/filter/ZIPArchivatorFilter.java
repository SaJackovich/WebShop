package web.filter;

import org.apache.log4j.Logger;
import web.wrapper.GZipServletResponseWrapper;

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

@WebFilter(filterName = "ZIPArchivatorFilter")
public class ZIPArchivatorFilter implements Filter {

    private static final String CONTENT_ENCODING = "Content-Encoding";
    private static final String GZIP = "gzip";
    private static final String ACCEPT_ENCODING = "Accept-Encoding";
    private static final String ZIP_ARCHIVATOR_FILTER_WAS_CREATED = "Zip Archivator filter was created";
    private static final String ZIP_ARCHIVATOR_FILTER_WAS_DESTROYED = "Zip Archivator filter was destroyed";

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) {
        log.info(ZIP_ARCHIVATOR_FILTER_WAS_CREATED);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (acceptsGZipEncoding(req)) {
            resp.addHeader(CONTENT_ENCODING, GZIP);
            GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(resp);
            chain.doFilter(req, gzipResponse);
            gzipResponse.close();
        } else {
            chain.doFilter(req, resp);
        }
    }

    private boolean acceptsGZipEncoding(HttpServletRequest req) {
        String acceptEncoding = req.getHeader(ACCEPT_ENCODING);
        return acceptEncoding != null && acceptEncoding.contains(GZIP);
    }

    @Override
    public void destroy() {
        log.info(ZIP_ARCHIVATOR_FILTER_WAS_DESTROYED);
    }

}