/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.bean;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Olakunle Awotunbo
 */
public class GameBean {
 
    private Integer id;
    private String gameName;
    private String gameCode;
    private boolean enabled;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;
    private String color;
    private String gameRules;
    private String gameImage;
    private String gameImgLocation;
     private MultipartFile[] files;
    private MultipartFile file;

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
     * @return the gameName
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * @param gameName the gameName to set
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * @return the gameCode
     */
    public String getGameCode() {
        return gameCode;
    }

    /**
     * @param gameCode the gameCode to set
     */
    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
     * @return the lastModificationDate
     */
    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    /**
     * @param lastModificationDate the lastModificationDate to set
     */
    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    /**
     * @return the lastModifiedBy
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * @param lastModifiedBy the lastModifiedBy to set
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the gameRules
     */
    public String getGameRules() {
        return gameRules;
    }

    /**
     * @param gameRules the gameRules to set
     */
    public void setGameRules(String gameRules) {
        this.gameRules = gameRules;
    }

    /**
     * @return the gameImage
     */
    public String getGameImage() {
        return gameImage;
    }

    /**
     * @param gameImage the gameImage to set
     */
    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }

    /**
     * @return the gameImgLocation
     */
    public String getGameImgLocation() {
        return gameImgLocation;
    }

    /**
     * @param gameImgLocation the gameImgLocation to set
     */
    public void setGameImgLocation(String gameImgLocation) {
        this.gameImgLocation = gameImgLocation;
    }

    /**
     * @return the files
     */
    public MultipartFile[] getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
