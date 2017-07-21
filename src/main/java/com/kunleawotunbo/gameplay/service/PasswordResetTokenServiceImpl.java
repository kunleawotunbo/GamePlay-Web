/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.PasswordResetTokenDao;
import com.kunleawotunbo.gameplay.model.PasswordResetToken;
import com.kunleawotunbo.gameplay.model.User;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("passwordResetTokenService")
@Transactional
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService{
    
    @Autowired
    private PasswordResetTokenDao passwordResetTokenDao;

    public boolean save(PasswordResetToken passwordResetToken) {
        return passwordResetTokenDao.save(passwordResetToken);
    }

    public PasswordResetToken getPasswordResetToken(String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User getUserByPasswordResetToken(String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PasswordResetToken findByToken(String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PasswordResetToken findByUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteByExpiryDateLessThan(Date now) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAllExpiredSince(Date now) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String validatePasswordResetToken(long id, String token) {
        return passwordResetTokenDao.validatePasswordResetToken(id, token);
    }
    
}
