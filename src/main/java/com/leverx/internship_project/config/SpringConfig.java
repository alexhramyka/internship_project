package com.leverx.internship_project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan("com.leverx.internship_project")
@Configuration
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {}
