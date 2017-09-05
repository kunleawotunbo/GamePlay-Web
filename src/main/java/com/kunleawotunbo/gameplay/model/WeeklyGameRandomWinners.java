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
 * @author BELLO
 */
@Entity
@Table(name = "weeklygamerandomwinners")

public class WeeklyGameRandomWinners implements Serializable {
    
    private static final long serialVersionUID = 1L;
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "winnerNumber")
    private String winnerNumber;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "gameName")
    private String gameName;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "weeklyGameId")
    private int weeklyGameId;

    

    @Column(name="dateAnswered")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateAnswered;

    


    public WeeklyGameRandomWinners() {
        super();
        //this.enabled = false;
        // this.creationDate(new Date());
    }

    
}
