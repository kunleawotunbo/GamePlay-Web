/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.dao;

import com.kunleawotunbo.gameplay.model.Country;
import java.util.List;

/**
 *
 * @author Olakunle Awotunbo
 */
public interface CountryDao {

    public Country getCountryById(int id);

    boolean saveCountry(Country country);

    boolean updateCountry(Country country);

    public List<Country> listCountries();
    
    public void deleteCountry(Country country);

}
