package com.search.place.config;

import com.search.place.application.service.UserPrincipalDetailsService;
import com.search.place.application.repository.UserRepository;

import com.search.place.auth.filter.JwtAuthenticationFilter;
import com.search.place.auth.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserPrincipalDetailsService userPrincipalDetailsService;
    private final UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//            .withUser("admin")
//            .password(passwordEncoder().encode("admin123"))
//            .roles("ADMIN").authorities("ACCESS_TEST1", "ACCESS_TEST2")
//            .and()
//            .withUser("user")
//            .password(passwordEncoder().encode("user123"))
//            .roles("USER")
//            .and()
//            .withUser("manager")
//            .password(passwordEncoder().encode("manager123"))
//            .roles("MANAGER").authorities("ACCESS_TEST1");
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // add jwt filters (1. authentication, 2. authorization)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userRepository))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/api/public/management/*").hasRole("MANAGER")
                .antMatchers("/api/public/admin/*").hasRole("ADMIN")
                .anyRequest().authenticated();
//            .anyRequest().authenticated()
//            .antMatchers("/index.html").permitAll()
//            .antMatchers("/profile/**").authenticated()
//            .antMatchers("/admin/**").hasRole("ADMIN")
//            .antMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
//            .antMatchers("/api/public/test1").hasAnyAuthority("ACCESS_TEST1")
//            .antMatchers("/api/public/test2").hasAnyAuthority("ACCESS_TEST2")
//            .antMatchers("/api/public/users").hasRole("ADMIN")
//            .and()
//            .formLogin()
//                .loginProcessingUrl("/signin")
//            .loginPage("/login").permitAll()
//                .usernameParameter("txtUsername")
//                .passwordParameter("txtPassword")
//            .and()
//            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
//            .and()
//        .rememberMe().tokenValiditySeconds(2592000).key("mySecretKey")
//        .rememberMeParameter("checkRememberMe");
//            .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
