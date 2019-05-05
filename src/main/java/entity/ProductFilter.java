package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ProductFilter implements Serializable {

    private static final int DEFAULT_PRODUCT_LIMIT = 10;
    private static final int DEFAULT_OFFSET = 0;
    private static final int DEFAULT_PAGE = 1;

    private List<Integer> productIds;
    private List<Integer> categories;
    private List<Integer> manufacturers;

    private String orderBy;
    private String orderByKind;
    private Boolean descendingly;

    private Integer minPrice;
    private Integer maxPrice;

    private Integer productLimit;
    private Integer page;

    private String name;

    public ProductFilter() {
        this.productLimit = DEFAULT_PRODUCT_LIMIT;
        this.page = DEFAULT_PAGE;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public String getOrderByKind() {
        return orderByKind;
    }

    public void setOrderByKind(String orderByKind) {
        this.orderByKind = orderByKind;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<Integer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPage() {
        return page;
    }

    public int getDefaultOffset() {
        return DEFAULT_OFFSET;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public int getDefaultProductLimit() {
        return DEFAULT_PRODUCT_LIMIT;
    }

    public int getDefaultPage() {
        return DEFAULT_PAGE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(Integer productLimit) {
        this.productLimit = productLimit;
    }

    public boolean isDescendingly() {
        return descendingly;
    }

    public void setDescendingly(boolean descendingly) {
        this.descendingly = descendingly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        ProductFilter that = (ProductFilter) o;
        return Objects.equals(categories, that.categories) &&
                Objects.equals(manufacturers, that.manufacturers) &&
                orderBy.equals(that.orderBy) &&
                Objects.equals(descendingly, that.descendingly) &&
                Objects.equals(minPrice, that.minPrice) &&
                Objects.equals(maxPrice, that.maxPrice) &&
                Objects.equals(productLimit, that.productLimit) &&
                Objects.equals(page, that.page) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categories, manufacturers, orderBy, descendingly, minPrice, maxPrice, productLimit, page, name);
    }

    @Override
    public String toString() {
        return "ProductFilter{" +
                "categories=" + categories +
                ", manufacturers=" + manufacturers +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", orderBy='" + orderBy + '\'' +
                ", descendingly=" + descendingly +
                ", productLimit=" + productLimit +
                ", page=" + page +
                ", name='" + name + '\'' +
                '}';
    }
}
