/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.PasswordResetToken;
import com.kunleawotunbo.gameplay.model.User;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.ldap.repository.Query;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface PasswordResetTokenDao {
    
    boolean save(PasswordResetToken passwordResetToken);
    
    PasswordResetToken getPasswordResetToken(String token);
    User getUserByPasswordResetToken(String token);
    
    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    //@Modifying
    @Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
    
      String validatePasswordResetToken(long id, String token);
}
