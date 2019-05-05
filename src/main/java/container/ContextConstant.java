package container;

public final class ContextConstant {

    private ContextConstant() {
    }

    public static final String CAPTCHA_HANDLER = "captchaHandler";
    public static final String CAPTCHA_PRESERVER = "captchaPreserver";

    public static final String USER_SERVICE = "userService";
    public static final String AVATAR_SERVICE = "avatarService";
    public static final String CAPTCHA_SERVICE = "captchaService";
    public static final String PRODUCT_SERVICE = "productService";
    public static final String CATEGORY_SERVICE = "categoryService";
    public static final String MANUFACTURER_SERVICE = "manufacturerService";
    public static final String CART_PRODUCT_SERVICE = "cartProductService";
    public static final String ORDER_SERVICE = "orderService";

    static final String HIDDEN_FIELD_CAPTCHA_HANDLER = "hiddenFieldCaptchaHandler";
    static final String COOKIE_CAPTCHA_HANDLER = "cookieCaptchaHandler";
    static final String SESSION_CAPTCHA_HANDLER = "sessionCaptchaHandler";

    public static final String SERVLET_DESTROYED = "Servlet Context destroyed";

    public static final String FILTERED_PRODUCT_EXTRACTOR = "filteredProductExtractor";
    public static final String CART_PRODUCT_EXTRACTOR = "cartProductExtractor";
    public static final String ORDER_INFORMATION_EXTRACTOR = "orderInformationExtractor";

    public static final String CONNECTION_POOL_NOT_CLOSED = "Connection pool wasn't closed";

    public static final boolean DEFAULT_AUTO_COMMIT = false;
    public static final boolean ROLLBACK_ON_RETURN = true;
    public static final String DRIVER = "db.driver";
    public static final String URL = "db.url";
    public static final String USER_NAME = "db.username";
    public static final String DB_PASSWORD = "db.password";
    public static final String INIT_SIZE = "db.pool.initSize";
    public static final String MAX_SIZE = "db.pool.maxSize";

    public static final String CONNECTION_POOL_PROPERSTIES_FILE = "application.properties";

}
