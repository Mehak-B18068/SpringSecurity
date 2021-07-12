package com.mehak.springboot1.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@Order(99)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //Building our own http authentication and authorization strategies
        //super.configure(http);
        //http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
        http.mvcMatcher("/support/admin").authorizeRequests()
                .anyRequest()
                .hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login");

        // http.authorizeRequests().anyRequest().permitAll();


    }
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().mvcMatchers("/css/**","/webjars/**");
    }

}
