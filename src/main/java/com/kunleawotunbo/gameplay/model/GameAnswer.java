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
@Table(name = "gameanswers")
public class GameAnswer implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull    
    @Column(name = "weekNo")
    private int weekNo;

    @Basic(optional = false)
    @NotNull
     @Column(name = "gameId")
    private int gameId;

    @Basic(optional = false)
    @NotNull
     @Column(name = "gameCategoryId")
    private int gameCategoryId;
    
      
     @Size( max = 1000)
    @Column(name = "gameAnswer")
    private String gameAnswer;

    @Column(name = "createdDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;
    
      @Basic(optional = false)
    @NotNull
    @Size( max = 100)
    @Column(name = "createdBy")
    private String createdBy;
    
    @Column(name = "modifiedDate")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedDate;
    
    @Column(name = "modifiedBy")
    private String modifiedBy;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "code")
    private int code;

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
     * @return the weekNo
     */
    public int getWeekNo() {
        return weekNo;
    }

    /**
     * @param weekNo the weekNo to set
     */
    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the gameCategoryId
     */
    public int getGameCategoryId() {
        return gameCategoryId;
    }

    /**
     * @param gameCategoryId the gameCategoryId to set
     */
    public void setGameCategoryId(int gameCategoryId) {
        this.gameCategoryId = gameCategoryId;
    }

    /**
     * @return the gameAnswer
     */
    public String getGameAnswer() {
        return gameAnswer;
    }

    /**
     * @param gameAnswer the gameAnswer to set
     */
    public void setGameAnswer(String gameAnswer) {
        this.gameAnswer = gameAnswer;
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
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }
   
     
    
}
