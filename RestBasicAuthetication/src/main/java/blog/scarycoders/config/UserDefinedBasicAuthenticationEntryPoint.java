package blog.scarycoders.config;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * Created by mohammad on 16/4/17.
 */
public class UserDefinedBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint{

  @Override
  public void afterPropertiesSet() throws Exception {
    setRealmName("SCARYCODERS_REALM");
    super.afterPropertiesSet();
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.addHeader("WWW-Authenticate","Basic realm=" + getRealmName() + "");
    PrintWriter printWriter=response.getWriter();
    printWriter.println("HTTP 401 :"+authException.getMessage());
  }
}
