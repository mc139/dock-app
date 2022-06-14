package com.dock.dockapp.config;

import com.dock.dockapp.model.DockUser;
import com.dock.dockapp.model.DockUserDetailServiceImpl;
import com.dock.dockapp.repository.DockUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DockUserDetailServiceImpl dockUserDetailService;
    private DockUserRepo dockUserRepo;

    @Autowired
    public WebSecurityConfig(DockUserDetailServiceImpl dockUserDetailService, DockUserRepo dockUserRepo) {
        this.dockUserDetailService = dockUserDetailService;
        this.dockUserRepo = dockUserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(
                new User("admin",
                        passwordEncoder().encode("admin"),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))));

        auth.userDetailsService(dockUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/boats").hasRole("ADMIN")
                .antMatchers("/dashboard/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/gui/**").hasAnyRole("ADMIN")
//                .antMatchers("/api/**").hasRole("ADMIN")
                .antMatchers("/api/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();

    }

    @Bean()
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeUsers() {
        DockUser adminUser = new DockUser("admin", passwordEncoder().encode("admin"), "ROLE_ADMIN");
        DockUser normalUser = new DockUser("normal", passwordEncoder().encode("normal"), "ROLE_USER");
        dockUserRepo.save(adminUser);
        dockUserRepo.save(normalUser);
    }
}

