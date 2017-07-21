/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.model.PasswordResetToken;
import com.kunleawotunbo.gameplay.model.User;
import java.util.Date;
import java.util.stream.Stream;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface PasswordResetTokenService {
    
    boolean save(PasswordResetToken passwordResetToken);
    
    PasswordResetToken getPasswordResetToken(String token);
    User getUserByPasswordResetToken(String token);
    
    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    void deleteAllExpiredSince(Date now);
    
    String validatePasswordResetToken(long id, String token);
}
