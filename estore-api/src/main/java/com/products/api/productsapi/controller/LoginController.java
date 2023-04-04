package com.products.api.productsapi.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.products.api.productsapi.persistence.UserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.api.productsapi.model.User;

/**
 * Handles the REST API requests for the User resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Team 1
 */

@RestController
@RequestMapping("login")
@CrossOrigin(origins = "http://mdbook.me:4200", maxAge = 3600)
public class LoginController {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    private UserDAO userDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param userDao The {@link UserDAO User Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public LoginController(UserDAO userDao) {
        this.userDao = userDao;
    }

    /**
     * Responds to the GET request for a {@linkplain User user} for the given id
     * 
     * @param username The username associated with the {@link User user}
     * 
     * @return ResponseEntity with {@link User user} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */

    /**
     * Creates a {@linkplain User user} with the provided user object
     * 
     * @param user - The {@link User user} to create
     * 
     * @return ResponseEntity with created {@link User user} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link User user} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<String> createUser(@RequestBody String user) {
        LOG.info("POST /login");
        JSONObject errObj = new JSONObject();
        errObj.append("success", false);
        errObj.append("username", "");
        errObj.append("first_name", "");
        errObj.append("last_name", "");
        errObj.append("cart_number", -1);
        errObj.append("is_admin", false);
        String errRet = errObj.toString();
        JSONObject userData = new JSONObject(user);
        String username, password;
        try {
            JSONObject data = (JSONObject) userData.get("body");
            username = data.getString("username");
            password = data.getString("password");
        } catch (JSONException e) {
            return new ResponseEntity<String>(errRet, HttpStatus.BAD_REQUEST);
        }
        try {
            User u = userDao.getUser(username);
            if (u == null) {
                return new ResponseEntity<String>(errRet, HttpStatus.OK);
            }
            if (password.equals(u.getPassword())) {
                User retU = u;
                JSONObject retUser = new JSONObject();
                retUser.append("username", retU.getUsername());
                retUser.append("first_name", retU.getFirstName());
                retUser.append("last_name", retU.getLastName());
                retUser.append("cart_number", retU.getCartNumber());
                retUser.append("is_admin", retU.get_is_admin());
                retUser.append("success", true);
                return new ResponseEntity<String>(retUser.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(errRet, HttpStatus.OK);
            }
        } catch (IOException e) {
            LOG.info(e.toString());
        }
        return new ResponseEntity<String>(errRet, HttpStatus.I_AM_A_TEAPOT);        
    }
}
