/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.PasswordResetToken;
import com.kunleawotunbo.gameplay.model.User;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Olakunle Awotunbo
 */
@Repository("passwordResetTokenDao")
public class PasswordResetTokenDaoImpl extends AbstractDao<Integer, PasswordResetToken> implements PasswordResetTokenDao{
    
     private final Logger logger = LoggerFactory.getLogger(getClass());
     
   public boolean save(PasswordResetToken passwordResetToken) {
        boolean success = false;
        try {
            
            persist(passwordResetToken);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public PasswordResetToken getPasswordResetToken(String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User getUserByPasswordResetToken(String token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PasswordResetToken findByToken(String token) {
       logger.info("findByToken : {}", token);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("token", token));
        PasswordResetToken passwordResetToken = (PasswordResetToken) crit.uniqueResult();
             
        return passwordResetToken;
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

    @Override
    public String validatePasswordResetToken(long id, String token) {
        final PasswordResetToken passToken = findByToken(token);
        if ((passToken == null) || (passToken.getUser()
            .getId() != id)) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate()
            .getTime() - cal.getTime()
            .getTime()) <= 0) {
            return "expired";
        }

        final User user = passToken.getUser();
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext()
            .setAuthentication(auth);
        return null;
    }

    
    
    
}
