package com.products.api.productsapi.persistence;

import java.io.IOException;
import com.products.api.productsapi.model.User;

/**
 * Defines the interface for Product object persistence
 * 
 * @author Team 1
 */
public interface UserDAO {
     /**
     * Retrieves all {@linkplain User users}
     * 
     * @return An array of {@link Product user} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    User[] getUsers() throws IOException;

    /**
     * Retrieves a {@linkplain Product user} with the given id
     * 
     * @param id The id of the {@link Product user} to get
     * 
     * @return a {@link Product user} object with the matching id
     * <br>
     * null if no {@link Product user} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    User getUser(String username) throws IOException;

    /**
     * Creates and saves a {@linkplain Product user}
     * 
     * @param user {@linkplain Product user} object to be created and saved
     * <br>
     * The id of the user object is ignored and a new uniqe id is assigned
     *
     * @return new {@link Product user} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    User createUser(User user) throws IOException;
}
