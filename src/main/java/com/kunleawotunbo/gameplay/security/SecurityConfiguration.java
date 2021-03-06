/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 *
 * @author olakunle
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    PersistentTokenRepository tokenRepository;

    //@Autowired
    //private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    //@Autowired
    //private LogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    // @Autowired
    // private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private static String REALM = "MY_TEST_REALM";

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "list")
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/newuser/**", "/delete-user-*")
                .access("hasRole('ADMIN')")
                .antMatchers("/edit-user-*")
                .access("hasRole('ADMIN') or hasRole('DBA')")
                .and().formLogin().loginPage("/login")
                .loginProcessingUrl("/login").usernameParameter("userName").passwordParameter("password").and()
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                .tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
    
     */
 /*
      @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "list")
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/newuser/**", "/delete-user-*")
                .access("hasRole('ADMIN')")
                .antMatchers("/edit-user-*")
                .access("hasRole('ADMIN') or hasRole('DBA')")
                
                .antMatchers("/test/**").permitAll()       
                // Permit all on api/login
                .antMatchers("/api/login/**").permitAll()        
                
                .antMatchers("/api/**")
                .access("hasRole('ADMIN') or hasRole('DBA')") 
                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//We don't need sessions to be created.
               .and()
                .httpBasic()
                // Allow anonymous logins
                //.antMatchers("/auth/**").permitAll()
                
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login").usernameParameter("userName").passwordParameter("password")
                
                  .defaultSuccessUrl("/homepage")
                .failureUrl("/login?error=true")
                //.successHandler(myAuthenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                
                .and()
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                //.tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
                .tokenValiditySeconds(86400).and().csrf().disable().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
     */
 /*
    	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/dashboard")
				.access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
				.antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')").antMatchers("/edit-user-*")
				.access("hasRole('ADMIN') or hasRole('DBA')").and().formLogin().loginPage("/login")
				.loginProcessingUrl("/login").usernameParameter("userName").passwordParameter("password").and()
				.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
				.tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
     */
    // http://www.baeldung.com/spring-security-multiple-entry-points
    @Configuration
    @Order(1)
    public static class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/**").authorizeRequests().anyRequest().permitAll();
            http.antMatcher("/resources/**").authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(2)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
       

            http.csrf().disable()
                    .antMatcher("/api/**").authorizeRequests().anyRequest().permitAll();
            ;
        }
    }

    @Configuration
    @Order(3)
    public class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {          
            
            /*
            
              http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/admin/**")
                    .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                    .antMatchers("/user/updatePassword*", "/user/savePassword*", "/updatePassword*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login").usernameParameter("userName").passwordParameter("password")
                    .defaultSuccessUrl("/admin/dashboard")
                    
            
            */

            http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/admin/**")
                    .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                    .antMatchers("/user/updatePassword*", "/user/savePassword*", "/updatePassword*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login").usernameParameter("userName").passwordParameter("password")
                    .defaultSuccessUrl("/admin/dashboard")
                    
                    .and()
                    .sessionManagement()
                    //.invalidSessionUrl("/invalidSession.html")
                   // .invalidSessionUrl("/invalidSession")
                    .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
                    .sessionFixation().none()
                    .and()
                    .logout()
                    //.logoutSuccessHandler(myLogoutSuccessHandler)
                    //.invalidateHttpSession(false)
                    //.logoutSuccessUrl("/login?logSucc=true")
                    .deleteCookies("JSESSIONID")
                    .permitAll()    
                    
                    .and()
                    .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                   //.tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
                   .tokenValiditySeconds(86400).and().csrf().disable().exceptionHandling().accessDeniedPage("/Access_Denied");
                    
                /*    
                    .failureUrl("/login?error=true")
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                    .and()
                    .sessionManagement()
                    //.invalidSessionUrl("/invalidSession.html")
                    .invalidSessionUrl("/invalidSession.html")
                    .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
                    .sessionFixation().none()
                    .and()
                    .logout()
                    .logoutSuccessHandler(myLogoutSuccessHandler)
                    .invalidateHttpSession(false)
                    .logoutSuccessUrl("/logout.html?logSucc=true")
                    .deleteCookies("JSESSIONID")
                    .permitAll()    
                    
                    .and()
                    .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                   //.tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
                   .tokenValiditySeconds(86400).and().csrf().disable().exceptionHandling().accessDeniedPage("/Access_Denied");
            
            */
            
            
        }
    }

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("list")
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/newuser/**", "/delete-user-*")
                .access("hasRole('ADMIN')")
                .antMatchers("/edit-user-*")
                .access("hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/api/**")
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/test/**").permitAll()
                // Permit all on api/login
                .antMatchers("/api/**").permitAll()
                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//We don't need sessions to be created.
                .and()
                .httpBasic()
                // Allow anonymous logins
                //.antMatchers("/auth/**").permitAll()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login").usernameParameter("userName").passwordParameter("password")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error=true")
                //.successHandler(myAuthenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                //.tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
                .tokenValiditySeconds(86400).and().csrf().disable().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
    
     */

 /*
        
       @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()     
                .antMatchers("list")
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/newuser/**", "/delete-user-*")
                .access("hasRole('ADMIN')")
                .antMatchers("/edit-user-*")
                .access("hasRole('ADMIN') or hasRole('DBA')")
                   .antMatchers("/api/**")
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/test/**").permitAll()       
                // Permit all on api/login
                .antMatchers("/api/**").permitAll()        
                
                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//We don't need sessions to be created.
               .and()
                .httpBasic()
                // Allow anonymous logins
                //.antMatchers("/auth/**").permitAll()
                
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login").usernameParameter("userName").passwordParameter("password")
                
                  .defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error=true")
                //.successHandler(myAuthenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                
                .and()
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                //.tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
                .tokenValiditySeconds(86400).and().csrf().disable().exceptionHandling().accessDeniedPage("/Access_Denied");
    }      
    
     */
 /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices gePersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedService = new PersistentTokenBasedRememberMeServices(
                "remember-me", userDetailsService, tokenRepository);
        return tokenBasedService;
    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
