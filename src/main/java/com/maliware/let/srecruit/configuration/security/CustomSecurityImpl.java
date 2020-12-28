package com.maliware.let.srecruit.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Configuration
public class CustomSecurityImpl extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests( a -> a.mvcMatchers(GET,"/user","/offer","/user/**").hasAuthority("READ")
//        .anyRequest().hasAnyAuthority("WRITE","READ")).httpBasic();
//        http.authorizeRequests().antMatchers("/user", "/offer").permitAll();

        http.csrf().disable();
    }


//    @Bean
//    UserDetailsService userDetailsService(DataSource dataSource){
//       return new JdbcUserDetailsManager(dataSource);//{
////            @Override
////           protected List<GrantedAuthority> loadUserAuthorities(String uName){
////                return AuthorityUtils.createAuthorityList("ROLE_USER");
////            }
//
////        };
//    }
}

//    @Bean
//    WebMvcConfigurer webMvc() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/oauth2/token")
//                        .allowedOrigins("http://localhost:8081")
//                        .maxAge(0);
//            }
//        };
//    }
