package com.products.api.productsapi.persistence;

import java.io.IOException;

import com.products.api.productsapi.model.Cart;

public interface CartDAO {

    Cart[] getCarts() throws IOException;

    Cart getCart(int id) throws IOException;

    Cart createCart(Cart cart) throws IOException;

    Cart updateCart(Cart cart) throws IOException;

    boolean deleteCart(int id) throws IOException;
}
