package com.products.api.productsapi.controller;

import java.io.IOException;

import com.products.api.productsapi.model.Cart;
import com.products.api.productsapi.persistence.CartDAO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("cart")
@CrossOrigin(origins = "http://mdbook.me:4200", maxAge = 3600)
public class CartController {
    private static final Logger LOG = Logger.getLogger(CartController.class.getName());
    private CartDAO cartDao;

    public CartController(CartDAO cartDao) {
        this.cartDao = cartDao;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable int id) {
        LOG.info("GET /cart/" + id);
        try {
            Cart cart = cartDao.getCart(id);
            if (cart != null)
                return new ResponseEntity<Cart>(cart,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<Cart[]> getCarts() {
        LOG.info("GET /cart");

        // Replace below with your implementation
        try{
            Cart cart[] = cartDao.getCarts();
            if(cart != null){
                return new ResponseEntity<Cart[]>(cart,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        LOG.info("POST /cart " + cart);

        // Replace below with your implementation
        try{
            cart = cartDao.createCart(cart);
            if(cart != null){
                return new ResponseEntity<Cart>(cart,HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PutMapping("")
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        LOG.info("PUT /cart " + cart);

        // Replace below with your implementation
        try{
            cart = cartDao.updateCart(cart);
            if(cart != null){
                return new ResponseEntity<Cart>(cart,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
        LOG.log(Level.SEVERE,e.getLocalizedMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable int id) {
        LOG.info("DELETE /collections/" + id);

        // Replace below with your implementation
        try{
            if(cartDao.deleteCart(id)){
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
