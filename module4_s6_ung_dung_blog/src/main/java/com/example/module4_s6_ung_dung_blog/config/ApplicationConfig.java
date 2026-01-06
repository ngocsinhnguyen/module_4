package com.example.module4_s6_ung_dung_blog.config;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.blog.repository")
@EntityScan("com.example.blog.entity")
@ComponentScan("com.example.blog")
public class ApplicationConfig {
}
