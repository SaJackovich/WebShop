package web.extractor;

import javax.servlet.http.HttpServletRequest;

public interface RequestExtractor<T> {

    T extractFromRequest(HttpServletRequest req);

}
