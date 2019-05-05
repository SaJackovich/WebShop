package entity;

import java.util.Objects;

public class OrderInformation {

    private String paymentKind;
    private String delivery;
    private long bankCard;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPaymentKind() {
        return paymentKind;
    }

    public void setPaymentKind(String paymentKind) {
        this.paymentKind = paymentKind;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public long getBankCard() {
        return bankCard;
    }

    public void setBankCard(long bankCard) {
        this.bankCard = bankCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        OrderInformation that = (OrderInformation) o;
        return bankCard == that.bankCard &&
                userId == that.userId &&
                Objects.equals(paymentKind, that.paymentKind) &&
                Objects.equals(delivery, that.delivery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentKind, delivery, bankCard, userId);
    }

    @Override
    public String toString() {
        return "OrderInformation{" +
                "paymentKind='" + paymentKind + '\'' +
                ", delivery='" + delivery + '\'' +
                ", bankCard=" + bankCard +
                ", userId=" + userId +
                '}';
    }
}
