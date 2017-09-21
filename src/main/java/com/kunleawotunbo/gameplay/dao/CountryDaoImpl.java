/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.Country;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Olakunle Awotunbo
 */
@Repository("countryDao")
@Transactional
public class CountryDaoImpl extends AbstractDao<Integer, Country> implements CountryDao{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public Country getCountryById(int id) {
        logger.info("id :: " +  id);

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (Country) crit.uniqueResult();
    }

    public boolean saveCountry(Country country) {
        boolean success = false;
        try {
            saveOrUpdate(country);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateCountry(Country country) {
        boolean success = false;
        try {
            update(country);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public List<Country> listCountries() {
        Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Country> countriesList = (List<Country>) criteria.list();

        return countriesList;
    }

    public void deleteCountry(Country country) {
        delete(country);
    }
    
}
