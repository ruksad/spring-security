package blog.scarycoders.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Created by mohammad on 16/4/17.
 */
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{

  private static String REALM="SCARYCODERS_REALM";
  private AuthenticationEntryPoint basicAuthEntryPoint;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("ruksad").password("siddiqui").roles("ADMIN");
    auth.inMemoryAuthentication().withUser("abc").password("abc").roles("USER");
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/user/**").hasRole("ADMIN")
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint());
  }


  public AuthenticationEntryPoint getBasicAuthEntryPoint() {
    return new UserDefinedBasicAuthenticationEntryPoint();
  }
}
