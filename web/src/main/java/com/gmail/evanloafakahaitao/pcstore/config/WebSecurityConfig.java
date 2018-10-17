package com.gmail.evanloafakahaitao.pcstore.config;

import com.gmail.evanloafakahaitao.pcstore.controller.filter.AppAuthenticationSuccessHandler;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.WebProperties;
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
                    .antMatchers(
                            WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/register",
                            WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/login",
                            WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/register/users/create").permitAll()
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/**").fullyAuthenticated()
                .and()
                .formLogin()
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginPage(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/login")
                    .loginProcessingUrl(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/login")
                    .successHandler(appAuthenticationSuccessHandler)
                    .failureUrl(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/login?error=true")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/logout")
                    .logoutSuccessUrl(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/login?logout=true")
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
