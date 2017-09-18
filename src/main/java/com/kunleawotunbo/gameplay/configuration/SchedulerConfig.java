/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.configuration;

import com.kunleawotunbo.gameplay.schedulling.GameWinnersScheduler;
import com.kunleawotunbo.gameplay.schedulling.MatchPredictionProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author Olakunle Awotunbo
 */
@Configuration
@EnableScheduling
public class SchedulerConfig {
    
    @Bean
    public GameWinnersScheduler bean(){
        return new GameWinnersScheduler();
    }
    
    @Bean
    public MatchPredictionProcessing MatchPredictionProcessingBean(){
        return new MatchPredictionProcessing();
    }
    
}
