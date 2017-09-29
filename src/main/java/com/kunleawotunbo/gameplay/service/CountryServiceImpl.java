/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.service;

import com.kunleawotunbo.gameplay.dao.CountryDao;
import com.kunleawotunbo.gameplay.model.Country;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olakunle Awotunbo
 */
@Service("countryService")
public class CountryServiceImpl implements CountryService{
    
    @Autowired
    private CountryDao countryDao;

    public Country getCountryById(int id) {
        return countryDao.getCountryById(id);
    }
    
    public Country getCountryByCountryCode(String countryCode) {
        return countryDao.getCountryByCountryCode(countryCode);
    }

    public boolean saveCountry(Country country) {
        return countryDao.saveCountry(country);
    }

    public boolean updateCountry(Country country) {
        return countryDao.updateCountry(country);
    }

    public List<Country> listCountries() {
        return countryDao.listCountries();
    }

    public void deleteCountry(Country country) {
         countryDao.deleteCountry(country);
    }    
    
}
