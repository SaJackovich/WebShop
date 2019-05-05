package web.extractor.impl;

import web.extractor.RequestExtractor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MiniCartProductExtractor implements RequestExtractor<Map<Integer, Integer>> {

    private static final String QUANTITY = "quantity_";
    private static final String ITEM_NUMBER = "item_number_";

    @Override
    public Map<Integer, Integer> extractFromRequest(HttpServletRequest req) {
        Map<Integer, Integer> cartProducts = new HashMap<>();
        int count = 1;
        while (true) {
            String productId = req.getParameter(QUANTITY + count);
            if (Objects.isNull(productId)) {
                break;
            }
            int cartProductId = Integer.parseInt(productId);
            int productCount = Integer.parseInt(req.getParameter(ITEM_NUMBER + count++));
            cartProducts.put(productCount, cartProductId);
        }
        return cartProducts;
    }

}
