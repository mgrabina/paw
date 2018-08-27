package ar.edu.itba.paw.webapp.config;

import ar.edu.itba.paw.webapp.auth.PawUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.paw.webapp.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PawUserDetailsService userDetailsService;

    protected void configure(final HttpSecurity http)throws Exception {
        http.userDetailsService(userDetailsService).sessionManagement().invalidSessionUrl("/user/login").and().authorizeRequests()
                .antMatchers("/user/login").anonymous()
                .antMatchers("/user/register").anonymous()
                // .antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/**").authenticated()
                .antMatchers("/property/register").authenticated()
                .and().formLogin().usernameParameter("mail").passwordParameter("password").defaultSuccessUrl("/", false).loginPage("/user/login")
                .failureUrl("/user/login?error=true")
                .and().rememberMe().rememberMeParameter("j_rememberme")
                .userDetailsService(userDetailsService).key("pawkey2018secretatr")
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
                .and().logout().logoutUrl("/user/logout").logoutSuccessUrl("/user/login")
                // .and().exceptionHandling().accessDeniedPage("/")
                .and().csrf().disable();
    }
    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/resources/**",
                "/403");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}