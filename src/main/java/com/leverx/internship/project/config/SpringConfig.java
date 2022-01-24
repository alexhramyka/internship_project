package com.leverx.internship.project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("com.leverx.internship.project")
@Configuration
@EnableWebMvc
public class SpringConfig {
}