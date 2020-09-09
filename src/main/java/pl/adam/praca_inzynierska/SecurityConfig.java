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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.adam.praca_inzynierska.account.Account;
import pl.adam.praca_inzynierska.account.AccountRepository;
import pl.adam.praca_inzynierska.account.Transaction;
import pl.adam.praca_inzynierska.account.TransactionRepository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsServiceImpl userDetailsService;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;


    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.userDetailsService = userDetailsService;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
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




    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        List<Transaction> transactionList = new ArrayList<>();


        Account account = new Account("Dziala", passwordEncoder().encode("123123"), "12312312@gmail.com", "2020/04/10 22:29:50", 12, "USER", transactionList );
        accountRepository.save(account);
        Account account2 = new Account("Dziala2", passwordEncoder().encode("123123"), "123123122@gmail.com", "2020/04/10 22:29:50", 0, "USER", null);
        accountRepository.save(account2);
        Account account3 = new Account("Dziala3", passwordEncoder().encode("123123"), "123123123@gmail.com", "2020/04/10 22:29:50", 123123, "USER", null);
        accountRepository.save(account3);
        Account account4 = new Account("Dziala4", passwordEncoder().encode("123123"), "123123124@gmail.com", "2020/04/10 22:29:50", 4, "USER", null);
        accountRepository.save(account4);


        Transaction transaction = new Transaction( "chf", "2020-01-13", "2020-01-14", 100, 107, 57, account);
        Transaction transaction2 = new Transaction( "gbp", "2020-01-13", "2020-01-14", 50, 12, 65, account);
        Transaction transaction3 = new Transaction( "eur", "2020-01-13", "2020-01-14", 11, 99, 57, account);
        Transaction transaction4 = new Transaction( "chf", "2020-01-17", "2020-01-18", 100, 107, 99, account);

        Transaction transaction5 = new Transaction("CHF", "2020-09-01", 3.73, 100, account);
        Transaction transaction6 = new Transaction("EUR", "2020-09-01", 4.14, 100, account);
        Transaction transaction7 = new Transaction("GBP", "2020-09-01", 4.73, 100, account);

        transactionRepository.save(transaction);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);
        transactionRepository.save(transaction4);
        transactionRepository.save(transaction5);
        transactionRepository.save(transaction6);
        transactionRepository.save(transaction7);

        account.setTransaction(transactionList);

    }
}