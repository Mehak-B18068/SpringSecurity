package com.mehak.springboot1.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //Building our own http authentication and authorization strategies
        //super.configure(http);
        //http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
       // http.headers().disable()
                http.authorizeRequests()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/support/admin").hasRole("ADMIN")
                .anyRequest()
                    .authenticated()
                .and()
                    .formLogin().loginPage("/login").defaultSuccessUrl("/",true);

       // http.authorizeRequests().anyRequest().permitAll();


    }
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().mvcMatchers("/css/**","/webjars/**");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //creating my own in-memory implementation with test user
        //spring expects each password
        PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("Victoria").password(encoder.encode("password")).roles("ADMIN")
        .and().withUser("user1").password(encoder.encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(encoder.encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(encoder.encode("adminPass")).roles("ADMIN");;

    }
//    @Bean
//    public HttpFirewall firewall()
//    {
//        StrictHttpFirewall firewall=new StrictHttpFirewall();
//        firewall.setAllowSemicolon(true);
//        return firewall;
//    }
}
