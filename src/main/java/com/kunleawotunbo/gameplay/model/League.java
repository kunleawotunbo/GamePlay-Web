/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Olakunle Awotunbo
 */
@Entity
@Table(name = "leagues")
public class League implements Serializable{    
    
    private static Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;    
      
    
    @Basic(optional = false)
    @Column(name = "country_id")
    private Integer countryId;  
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "country_name")
    private String countryName;
    
     @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "country_Code")
    private String countryCode;
     
     @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "league_name")
    private String leagueName;
    
     @Basic(optional = false)
    @NotNull
    @Size(max = 100)
    @Column(name = "league_Code")
    private String leagueCode;
    
    @Column(name = "created_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedDate;

    //@Basic(optional = false)
    //@NotNull
    @Size(max = 100)
    @Column(name = "created_by")
    private String createdBy;
    
    //@Basic(optional = false)
    //@NotNull
    @Size(max = 100)
    @Column(name = "modified_by")
    private String modifiedBy;

    /**
     * @return the serialVersionUID
     */
    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(Long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    
    /**
     * @return the countryId
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
    
    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the leagueName
     */
    public String getLeagueName() {
        return leagueName;
    }

    /**
     * @param leagueName the leagueName to set
     */
    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    /**
     * @return the leagueCode
     */
    public String getLeagueCode() {
        return leagueCode;
    }

    /**
     * @param leagueCode the leagueCode to set
     */
    public void setLeagueCode(String leagueCode) {
        this.leagueCode = leagueCode;
    }


}
