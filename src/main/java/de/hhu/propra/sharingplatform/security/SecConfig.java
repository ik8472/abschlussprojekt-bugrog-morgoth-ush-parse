package de.hhu.propra.sharingplatform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecConfig extends WebSecurityConfigurerAdapter {


    // this cannot be autowired in an constructor?!?!
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/sale", "/css/**", "/images/**", "/static/images/*",
                "/user/register")
            .permitAll() // h2 has to be removed in production
            .antMatchers("/conflicts/**")
            .hasRole("admin")
            .anyRequest().authenticated();
        http.formLogin().permitAll();
        http.logout().permitAll();

        http.userDetailsService(userDetailsService);

        http.csrf().disable();
    }

}
