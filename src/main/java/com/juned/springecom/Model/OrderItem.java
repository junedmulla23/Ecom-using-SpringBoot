package com.juned.springecom.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Builder;

@Entity
public class OrderItem {

    @Id
    private int id;

    @ManyToOne
    private Product product;

    private int quantity;

    private BigDecimal totalprice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    public OrderItem() {
    }

    // Private constructor for Builder
    private OrderItem(Builder builder) {
        this.id = builder.id;
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.totalprice = builder.totalprice;
        this.order = builder.order;
    }

    // All-args constructor (optional)
    public OrderItem(int id, Product product, int quantity, BigDecimal totalprice, Order order) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.order = order;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getTotalprice() { return totalprice; }
    public void setTotalprice(BigDecimal totalprice) { this.totalprice = totalprice; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    // Manual Builder
    public static class Builder {
        private int id;
        private Product product;
        private int quantity;
        private BigDecimal totalprice;
        private Order order;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder totalprice(BigDecimal totalprice) {
            this.totalprice = totalprice;
            return this;
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
