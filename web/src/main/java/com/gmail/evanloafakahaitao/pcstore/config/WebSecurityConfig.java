package com.gmail.evanloafakahaitao.pcstore.config;

import com.gmail.evanloafakahaitao.pcstore.controller.filter.AppAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

    @Autowired
    public WebSecurityConfig(@Qualifier("userDetailsService") UserDetailsService userDetailsService, AppAuthenticationSuccessHandler appAuthenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.appAuthenticationSuccessHandler = appAuthenticationSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/web/register**", "/web/login**", "/web/users").permitAll()
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers("/web/**").fullyAuthenticated()
                .and()
                .formLogin()
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginPage("/web/login")
                    .loginProcessingUrl("/web/login")
                    .successHandler(appAuthenticationSuccessHandler)
                    .failureUrl("/web/login?error=true")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/web/logout")
                    .logoutSuccessUrl("/web/login?logout=true")
                    .permitAll()
                .and()
                    .csrf()
                    .disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
}
