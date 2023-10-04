package com.security.jwtsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.security.jwtsecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/", "/api/**").hasRole(USER_ROLE.name())
                .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN_ROLE.name(), ADMIN_TRAINEE.name())
                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(ApplicationUserPermission.ROLE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(ApplicationUserPermission.ROLE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(ApplicationUserPermission.ROLE_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user01")
                .password(passwordEncoder.encode("01user"))
//                .roles(USER_ROLE.name())
                .authorities(USER_ROLE.getGrantedAuthorities())
                .build();
        UserDetails AdminTrainee = User.builder()
                .username("AdminTrainee")
                .password(passwordEncoder.encode("admin123"))
//                .roles(ADMIN_TRAINEE.name())
                .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
                .build();

        UserDetails adminUser = User.builder()
                .username("Admin")
                .password(passwordEncoder.encode("admin123"))
//                .roles(ADMIN_ROLE.name())
                .authorities(ADMIN_ROLE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                user, adminUser,
                AdminTrainee

        );
    }
}