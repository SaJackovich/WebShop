package entity;

import container.OrderStatus;
import util.IdGenerator;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Order {

    private int id;
    private OrderStatus orderStatus;
    private String statusDetailing;
    private Date date;
    private List<CartProduct> cartProducts;
    private int userId;
    private OrderInformation orderInformation;

    private Order(OrderBuilder orderBuilder) {
        id = IdGenerator.getOrderId();
        this.orderStatus = orderBuilder.orderStatus;
        this.statusDetailing = orderBuilder.statusDetailing;
        this.date = orderBuilder.date;
        this.cartProducts = orderBuilder.cartProducts;
        this.userId = orderBuilder.userId;
        this.orderInformation = orderBuilder.orderInformation;
    }

    public int getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getStatusDetailing() {
        return statusDetailing;
    }

    public int getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public OrderInformation getOrderInformation() {
        return orderInformation;
    }

    public static class OrderBuilder{

        private OrderStatus orderStatus;
        private String statusDetailing;
        private Date date;
        private List<CartProduct> cartProducts;
        private int userId;
        private OrderInformation orderInformation;

        public OrderBuilder orderInformation(OrderInformation orderInformation){
            this.orderInformation = orderInformation;
            return this;
        }

        public OrderBuilder userId(int userId){
            this.userId = userId;
            return this;
        }

        public OrderBuilder orderStatus(OrderStatus orderStatus){
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderBuilder statusDetailing(String statusDetailing){
            this.statusDetailing = statusDetailing;
            return this;
        }

        public OrderBuilder date(Date date){
            this.date = date;
            return this;
        }

        public OrderBuilder cartProducts(List<CartProduct> cartProducts){
            this.cartProducts = cartProducts;
            return this;
        }

        public Order build(){
            return new Order(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Order order = (Order) o;
        return id == order.id &&
                orderStatus == order.orderStatus &&
                Objects.equals(statusDetailing, order.statusDetailing) &&
                Objects.equals(date, order.date) &&
                Objects.equals(cartProducts, order.cartProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderStatus, statusDetailing, date, cartProducts);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", statusDetailing='" + statusDetailing + '\'' +
                ", date=" + date +
                ", cartProducts=" + cartProducts +
                '}';
    }
}
