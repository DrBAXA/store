package sombra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecuriryConfiguration extends WebSecurityConfigurerAdapter{

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("SELECT name, password, enabled FROM users WHERE name=?")
                .authoritiesByUsernameQuery("SELECT users.name, roles.name FROM users " +
                                            "JOIN roles ON users.role_id=roles.id AND users.name=?");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin*").hasRole("ADMIN")
                .and()


                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("name")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=1")
                .and()

                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
}
