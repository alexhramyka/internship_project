package com.leverx.internship.project.config;

import com.leverx.internship.project.security.jwt.JwtConfigurer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtConfigurer jwtConfigurer;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic()
        .disable()
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/api/auth/login").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/**", "/api/projects/**").hasAnyAuthority("user.read")
        .antMatchers(HttpMethod.POST, "/api/auth/logout").hasAnyAuthority("user.read")
        .antMatchers(HttpMethod.GET, "/api/departments/**").hasAnyAuthority("user.write")
        .antMatchers(HttpMethod.PUT, "/api/users/**", "/api/projects/**", "/api/departments/**").hasAnyAuthority("user.delete")
        .antMatchers(HttpMethod.POST, "/api/users/**", "/api/projects/**", "/api/departments/**").hasAnyAuthority("user.delete")
        .antMatchers(HttpMethod.DELETE, "/api/users/**", "api/projects/**", "/api/departments/**").hasAnyAuthority("user.delete")
        .anyRequest().authenticated()
        .and()
        .apply(jwtConfigurer);
    }

  @Bean
  protected PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
