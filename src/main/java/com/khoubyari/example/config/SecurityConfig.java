package com.khoubyari.example.config;
import com.khoubyari.example.security.JwtConfigurer;
import com.khoubyari.example.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.GET,"/users/**").hasAnyAuthority("admin", "manager", "agent")
                .antMatchers("/users/**").hasAuthority("admin")
                .antMatchers("/orders/**").hasAnyAuthority("manager", "admin", "customer")
                .antMatchers("/tasks/**").hasAnyAuthority("agent", "manager", "admin")
                .antMatchers("/comments/**").hasAnyAuthority("agent", "manager", "admin")
                .antMatchers(HttpMethod.GET, "/requests/**").hasAnyAuthority("customer", "manager", "admin")
                .antMatchers(HttpMethod.POST, "/requests/**").hasAnyAuthority("customer")
                .antMatchers(HttpMethod.PUT, "/requests/**").hasAnyAuthority("manager", "admin")
                .antMatchers(HttpMethod.DELETE, "/requests/**").hasAnyAuthority("admin")
                .anyRequest().permitAll()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        //@formatter:on
    }


}