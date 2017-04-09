package blog.scarycoders.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by mohammad on 8/4/17.
 */

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${blog.scarycoders.user.username}")
  private String userUserName;

  @Value("${blog.scarycoders.user.password}")
  private String userPassword;

  @Value("${blog.scarycoders.user.role}")
  private String userRole;

  @Value("${blog.scarycoders.admin.username}")
  private String adminUserName;

  @Value("${blog.scarycoders.admin.password}")
  private String adminPassword;

  @Value("${blog.scarycoders.admin.role}")
  private String adminRole;

  @Value("${blog.scarycoders.admin.dba.username}")
  private String adminDBAUserName;

  @Value("${blog.scarycoders.admin.dba.password}")
  private String adminDBAPassword;

  @Value("${blog.scarycoders.admin.dba.role}")
  private String adminDbaRole;


  MySuccessHandler mySuccessHandler;

  @Autowired
  public SecurityConfiguration(MySuccessHandler mySuccessHandler){
    this.mySuccessHandler=mySuccessHandler;
  }
  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.inMemoryAuthentication().withUser(userUserName).password(userPassword).roles(userRole);
    authenticationManagerBuilder.inMemoryAuthentication().withUser(adminUserName).password(adminPassword).roles(adminRole);
    authenticationManagerBuilder.inMemoryAuthentication().withUser(adminDBAUserName).password(adminDBAPassword).roles(adminRole,adminDbaRole);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/","/index").access("hasRole('USER')")
        .antMatchers("/admin").access("hasRole('ADMIN')")
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        .antMatchers("/dba/**").access("hasRole('ADMIN') and hasRole('DBA')")
        .and().formLogin().loginPage("/login").usernameParameter("signinId").passwordParameter("password")
        .successHandler(mySuccessHandler)
        .and().exceptionHandling().accessDeniedPage("/no_access");
  }
}
