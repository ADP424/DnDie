package com.products.api.productsapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.products.api.productsapi.model.Cart;

@Component 
public class CartFileDAO implements CartDAO{
    private static final Logger LOG = Logger.getLogger(CartFileDAO.class.getName());
    Map<Integer,Cart> carts;   // Provides a local cache of the hero objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Hero
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new hero
    private String filename = "data/carts.json";    // Filename to read from and write to

    public CartFileDAO(@Value("${carts.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the heroes from the file
    }

    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    private Cart[] getCartsArray() {
        return getCartsArray(null);
    }

    private Cart[] getCartsArray(String containsText) { // if containsText == null, no filter
        ArrayList<Cart> cartsArrayList = new ArrayList<>();

        for (Cart cart : carts.values()) {
            if (containsText == null) {
                cartsArrayList.add(cart);
            }
        }

        Cart[] cartArray = new Cart[cartsArrayList.size()];
        cartsArrayList.toArray(cartArray);
        return cartArray;
    }

    private boolean save() throws IOException {
        Cart[] cartArray = getCartsArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),cartArray);
        return true;
    }

    private boolean load() throws IOException {
        carts = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of heroes
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Cart[] cartArray = objectMapper.readValue(new File(filename),Cart[].class);

        // Add each hero to the tree map and keep track of the greatest id
        for (Cart cart : cartArray) {
            carts.put(cart.getId(),cart);
            if (cart.getId() > nextId)
                nextId = cart.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Cart[] getCarts() {
        synchronized(carts) {
            return getCartsArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Cart getCart(int id) {
        synchronized(carts) {
            if (carts.containsKey(id))
                return carts.get(id);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Cart createCart(Cart cart) throws IOException {
        synchronized(carts) {
            Cart newCart = new Cart(nextId(),cart.getName(), cart.getCartProducts());
            carts.put(newCart.getId(),newCart);
            save(); // may throw an IOException
            return newCart;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Cart updateCart(Cart cart) throws IOException {
        synchronized(carts) {
            if (carts.containsKey(cart.getId()) == false)
                return null;  // hero does not exist

            carts.put(cart.getId(),cart);
            save(); // may throw an IOException
            return cart;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteCart(int id) throws IOException {
        synchronized(carts) {
            if (carts.containsKey(id)) {
                carts.remove(id);
                return save();
            }
            else
                return false;
        }
    }
    
}
