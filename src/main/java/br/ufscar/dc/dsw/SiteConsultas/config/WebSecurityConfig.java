package br.ufscar.dc.dsw.SiteConsultas.config;

import br.ufscar.dc.dsw.SiteConsultas.security.UsuarioDetailsServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        //auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/error").permitAll()
                .antMatchers("/login/**", "/js/**").permitAll()
                .antMatchers("/css/**", "/image/**", "/webjars/**").permitAll()

                .antMatchers("/medicos/cadastrar", "/pacientes/cadastrar").hasRole("Admin")
                .antMatchers("/pacientes/listar").hasAnyRole("Admin,Medico,Paciente")
                .antMatchers("/medicos/listar").permitAll()
                .antMatchers("/consultas/cadastrar").hasAnyRole("Admin,Paciente")




                .antMatchers("/pacientes", "/medicos", "/consultas").permitAll()
                .antMatchers("/pacientes/{\\d+}", "/medicos/{\\d+}").permitAll()
                .antMatchers("/consultas/{\\d+}").permitAll()
                .antMatchers("/medicos/cidades/{\\w+}").permitAll()
                .antMatchers("/consultas/pacientes/{\\d+}").permitAll()
                .antMatchers("/consultas/medicos/{\\d+}").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }
}