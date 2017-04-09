package blog.scarycoders.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Created by mohammad on 8/4/17.
 */
@Component
public class MySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

  @Override
  protected void handle(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
      String targetString=this.determineTargetUrl(authentication);

      redirectStrategy.sendRedirect(request,response,targetString);
  }


  /*
    * This method extracts the roles of currently logged-in user and returns
    * appropriate URL according to his/her role.
    */
  protected String determineTargetUrl(Authentication authentication) {
    String url = "";

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

    List<String> roles = new ArrayList<String>();

    for (GrantedAuthority a : authorities) {
      roles.add(a.getAuthority());
    }

    if (isDba(roles)) {
      url = "/dba";
    } else if (isAdmin(roles)) {
      url = "/admin";
    } else if (isUser(roles)) {
      url = "/index";
    } else {
      url = "/not_permitted";
    }

    return url;
  }

  private boolean isUser(List<String> roles) {
    if (roles.contains("ROLE_USER")) {
      return true;
    }
    return false;
  }

  private boolean isAdmin(List<String> roles) {
    if (roles.contains("ROLE_ADMIN")) {
      return true;
    }
    return false;
  }

  private boolean isDba(List<String> roles) {
    if (roles.contains("ROLE_DBA")) {
      return true;
    }
    return false;
  }

}
