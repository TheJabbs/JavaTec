package com.example.posjohonnyjavatecspring2023.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "token", schema = "pos_db")
public class TokenEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "token")
    private String token;
    @Basic
    @Column(name = "token_expire")
    private Timestamp tokenExpire;
    @OneToMany(mappedBy = "tokenByRestaurantToken")
    private Collection<RestaurantEntity> restaurantsByToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Timestamp tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenEntity that = (TokenEntity) o;
        return Objects.equals(token, that.token) && Objects.equals(tokenExpire, that.tokenExpire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, tokenExpire);
    }

    public Collection<RestaurantEntity> getRestaurantsByToken() {
        return restaurantsByToken;
    }

    public void setRestaurantsByToken(Collection<RestaurantEntity> restaurantsByToken) {
        this.restaurantsByToken = restaurantsByToken;
    }
}
