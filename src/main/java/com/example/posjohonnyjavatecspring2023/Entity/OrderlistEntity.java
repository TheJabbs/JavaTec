package com.example.posjohonnyjavatecspring2023.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orderlist", schema = "pos_db")
public class OrderlistEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "orderList_id")
    private int orderListId;
    @Basic
    @Column(name = "food")
    private String food;
    @Basic
    @Column(name = "orderList_time")
    private Timestamp orderListTime;

    public int getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(int orderListId) {
        this.orderListId = orderListId;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Timestamp getOrderListTime() {
        return orderListTime;
    }

    public void setOrderListTime(Timestamp orderListTime) {
        this.orderListTime = orderListTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderlistEntity that = (OrderlistEntity) o;
        return orderListId == that.orderListId && Objects.equals(food, that.food) && Objects.equals(orderListTime, that.orderListTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderListId, food, orderListTime);
    }
}
