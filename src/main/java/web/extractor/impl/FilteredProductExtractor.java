package web.extractor.impl;

import container.PageInputFieldConstant;
import entity.ProductFilter;
import org.apache.log4j.Logger;
import web.extractor.RequestExtractor;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FilteredProductExtractor implements RequestExtractor<ProductFilter> {

    private static final String WRONG_ORDER_BY_PARAMETER = "Wrong orderBy parameter : --> ";
    private static final String USER_DOESN_T_CHOOSE_MANUFACTURER = "User doesn't choose manufacturer";
    private static final String USER_DOESN_T_CHOOSE_CATEGORIES = "User doesn't choose categories";
    private static final String PRODUCT = "product.";
    private static final String DECREASE = "decrease";

    private List<String> categories;
    private List<String> manufacturers;
    private String orderBy;
    private String orderByKind;
    private String name;
    private int minPrice;
    private int maxPrice;
    private int limit;
    private int page;

    private ProductFilter productFilter;

    private final Logger log = Logger.getLogger(this.getClass());
    
    @Override
    public ProductFilter extractFromRequest(HttpServletRequest req) {
        ProductFilter filterBean = (ProductFilter) req.getSession().getAttribute(PageInputFieldConstant.PRODUCT_FILTER_BEAN);
        if (Objects.isNull(filterBean)) {
            filterBean = new ProductFilter();
        } else {
            filterBean = extractFilter(req, filterBean);
        }
        return filterBean;
    }

    private ProductFilter extractFilter(HttpServletRequest req, ProductFilter filterBean) {
        productFilter = filterBean;
        fieldValueInit(req);
        addCategories();
        addManufacturer();
        addOrderBy();
        addOrderByKind();
        addName();
        addMinPrice();
        addMaxPrice();
        addLimit();
        addPage();
        return productFilter;
    }

    private void addPage() {
        if (page != -1) {
            productFilter.setPage(page);
        }
    }

    private void addLimit() {
        if (limit != -1) {
            productFilter.setProductLimit(limit);
        }
    }

    private void addMaxPrice() {
        if (maxPrice != -1 && maxPrice != minPrice) {
            productFilter.setMaxPrice(maxPrice);
        }
    }

    private void addMinPrice() {
        if (minPrice != -1 && maxPrice != minPrice) {
            productFilter.setMinPrice(minPrice);
        }
    }

    private void addName() {
        if (Objects.nonNull(name) && name.length() != 0) {
            productFilter.setName(name);
        }
    }

    private void fieldValueInit(HttpServletRequest req) {
        categories = getCategories(req);
        manufacturers = getManufacturers(req);
        orderBy = req.getParameter(PageInputFieldConstant.ORDER_BY);
        orderByKind = req.getParameter(PageInputFieldConstant.ORDER_BY_KIND);
        name = req.getParameter(PageInputFieldConstant.NAME);
        minPrice = checkInteger(req.getParameter(PageInputFieldConstant.MIN_PRICE));
        maxPrice = checkInteger(req.getParameter(PageInputFieldConstant.MAX_PRICE));
        limit = checkInteger(req.getParameter(PageInputFieldConstant.LIMIT));
        page = checkInteger(req.getParameter(PageInputFieldConstant.PAGE));
    }

    private List<String> getManufacturers(HttpServletRequest req) {
        try {
            return Arrays.asList(req.getParameterValues(PageInputFieldConstant.MANUFACTURERS));
        } catch (NullPointerException e){
            log.info(USER_DOESN_T_CHOOSE_MANUFACTURER);
        }
        return Collections.emptyList();
    }

    private List<String> getCategories(HttpServletRequest req) {
        try {
            return Arrays.asList(req.getParameterValues(PageInputFieldConstant.CATEGORIES));
        } catch (NullPointerException e){
            log.info(USER_DOESN_T_CHOOSE_CATEGORIES);
        }
        return Collections.emptyList();
    }

    private void addCategories(){
        List<Integer> categoryList = parseArray(categories);
        productFilter.setCategories(categoryList);
    }

    private void addManufacturer(){
        List<Integer> manufacturerList = parseArray(manufacturers);
        productFilter.setManufacturers(manufacturerList);
    }

    private void addOrderBy(){
        if (Objects.nonNull(orderBy) && orderBy.length() != 0) {
            try {
                productFilter.setOrderBy(PRODUCT + orderBy);
            } catch (IllegalArgumentException ex) {
                log.error(WRONG_ORDER_BY_PARAMETER + orderBy);
            }
        }
    }

    private void addOrderByKind(){
        if (Objects.nonNull(orderByKind) && orderByKind.length() != 0) {
            try {
                if (orderByKind.equals(DECREASE)){
                    productFilter.setOrderByKind(PageInputFieldConstant.DESC);
                } else {
                    productFilter.setOrderByKind("");
                }
            } catch (IllegalArgumentException ex) {
                log.error(WRONG_ORDER_BY_PARAMETER + orderBy);
            }
        }
    }

    private List<Integer> parseArray(List<String> categories){
        List<Integer> categoryList = null;
        if (Objects.nonNull(categories)){
            categoryList = new ArrayList<>();
            for (String category: categories) {
                categoryList.add(Integer.parseInt(category));
            }
        }
        return categoryList;
    }

    private int checkInteger(String value) {
        try {
            int parsedValue = Integer.parseInt(value);
            if (parsedValue >= 0) {
                return parsedValue;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        return -1;
    }
}
