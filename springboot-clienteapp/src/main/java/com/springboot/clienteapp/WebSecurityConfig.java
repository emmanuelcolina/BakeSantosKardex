/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.clienteapp;

import com.springboot.clienteapp.util.LoginSuccessMessage;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author ecolina
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    
    @Autowired
    private LoginSuccessMessage successMessage;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index","/home","/","/css/**","/js/**","/images/**").permitAll()
                
                .antMatchers("/productos/").hasAnyRole("ADMIN")
                .antMatchers("/productos/mostrar").hasAnyRole("ADMIN")
                .antMatchers("/productos/agregar/").hasAnyRole("ADMIN")
                .antMatchers("/productos/edit/**").hasAnyRole("ADMIN")
                .antMatchers("/productos/delete/**").hasAnyRole("ADMIN")
                .antMatchers("/vender/").hasAnyRole("ADMIN")
                .antMatchers("/ventas/").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .successHandler(successMessage)
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout().permitAll();
    }
    
    @Autowired
    public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception{
        
        builder.jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(passEncoder)
        .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
        .authoritiesByUsernameQuery("SELECT u.username, r.rol FROM roles r INNER JOIN users u ON r.user_id=u.id WHERE u.username=?");
    }
    
}
