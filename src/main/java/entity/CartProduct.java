package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class CartProduct {

    private Product product;
    private int amount;
    private BigDecimal price;

    private CartProduct(CartProductBuilder builder) {
        this.product = builder.product;
        this.amount = builder.amount;
        this.price = builder.price;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static class CartProductBuilder {

        private Product product;
        private int amount;
        private BigDecimal price;

        public CartProductBuilder product(Product product){
            this.product = product;
            this.price = product.getPrice();
            return this;
        }

        public CartProductBuilder amount(int amount){
            this.amount = amount;
            return this;
        }

        public CartProduct build(){
            return new CartProduct(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        CartProduct that = (CartProduct) o;
        return amount == that.amount &&
                Objects.equals(product, that.product) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, amount, price);
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "product=" + product +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
