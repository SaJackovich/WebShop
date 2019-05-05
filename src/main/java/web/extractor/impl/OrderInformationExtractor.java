package web.extractor.impl;

import container.ControllerConstant;
import entity.OrderInformation;
import web.extractor.RequestExtractor;

import javax.servlet.http.HttpServletRequest;

public class OrderInformationExtractor implements RequestExtractor<OrderInformation> {

    private static final String PAYMENT_KIND = "paymentKind";
    private static final String BANK_CARD = "bankCard";
    private static final String DELIVERY = "delivery";

    @Override
    public OrderInformation extractFromRequest(HttpServletRequest req) {
        OrderInformation orderInformation = new OrderInformation();
        String paymentKind = req.getParameter(PAYMENT_KIND);
        orderInformation.setPaymentKind(paymentKind);
        if (paymentKind.equals(BANK_CARD)){
                orderInformation.setBankCard(Long.parseLong(req.getParameter(BANK_CARD)));
        }
        orderInformation.setDelivery(req.getParameter(DELIVERY));
        orderInformation.setUserId((Integer) req.getSession().getAttribute(ControllerConstant.USER_ID));
        return orderInformation;
    }
}
