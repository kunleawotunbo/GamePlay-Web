/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.model.User;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author olakunle
 */
public interface UserService {
     
    User findById(int id);
     
    User findByUsername(String username);
     
    boolean saveUser(User user);
     
    void updateUser(User user);
     
    void deleteUserByUsername(String username);
 
    List<User> findAllUsers(); 
     
    boolean isUserUsernameUnique(Integer id, String username);
    
    public boolean isUserExist(User user);    
    
    User getUser(String verificationToken); 

    User getUserByID(long id);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    String validateVerificationToken(String token);

    String generateQRUrl(User user) throws UnsupportedEncodingException;

    User updateUser2FA(boolean use2FA);

    List<String> getUsersFromSessionRegistry();
    
    User findUserByEmail(String email);
    
    void createPasswordResetTokenForUser(User user, String token);
 
}