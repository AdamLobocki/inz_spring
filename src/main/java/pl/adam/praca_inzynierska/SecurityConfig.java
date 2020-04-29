package pl.adam.praca_inzynierska;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.adam.praca_inzynierska.account.Account;
import pl.adam.praca_inzynierska.account.AccountRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsServiceImpl userDetailsService;
    private AccountRepository accountRepository;


    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, AccountRepository accountRepository) {
        this.userDetailsService = userDetailsService;
        this.accountRepository = accountRepository;
    }

    @Autowired
    DataSource dataSource;



    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("jelop")
//                .password("dupaaa")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/createAccountPage")
                .permitAll()
                .anyRequest()
                .authenticated()
//                .hasRole("USER")
               .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }


    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Object pricipal = auth.getPrincipal();   // pobieranie name zalogowanego użytkownika



    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        Account account = new Account("Dziala", passwordEncoder().encode("123123"), "12312312@gmail.com", "2020/04/10 22:29:50", 12, "USER", null);
        accountRepository.save(account);
        Account account2 = new Account("Dziala2", passwordEncoder().encode("123123"), "123123122@gmail.com", "2020/04/10 22:29:50", 0, "USER", null);
        accountRepository.save(account2);
        Account account3 = new Account("Dziala3", passwordEncoder().encode("123123"), "123123123@gmail.com", "2020/04/10 22:29:50", 123123, "USER", null);
        accountRepository.save(account3);
        Account account4 = new Account("Dziala4", passwordEncoder().encode("123123"), "123123124@gmail.com", "2020/04/10 22:29:50", 4, "USER", null);
        accountRepository.save(account4);
    }
}
