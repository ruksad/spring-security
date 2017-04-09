package blog.scarycoders.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by mohammad on 9/4/17.
 */
@Controller
public class SecuredMVCController {

  @GetMapping(value = {"/","/index"})
  public String landingPage(ModelMap modelMap){
      modelMap.addAttribute("message","welcome to scarycoders by examples");
      return "index";
  }

  @GetMapping(value = {"/admin"})
  public String adminPage(ModelMap modelMap){
    modelMap.addAttribute("message",this.getPrincipal());
    return "admin";
  }

  @GetMapping(value = "/dba")
  public String dbPage(ModelMap modelMap){
    modelMap.addAttribute("message",this.getPrincipal());
    return "dba";
  }

  @GetMapping(value = "/not_permitted")
  public String accessDenied(ModelMap modelMap){
    modelMap.addAttribute("message",this.getPrincipal());
    return "not_permitted";
  }

  @GetMapping(value = "/login")
  public String loginPage() {
    return "login";
  }

  @GetMapping(value = "/logout")
  public String logOut(HttpServletRequest request,HttpServletResponse response){
    Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

    if(authentication!=null){
      new SecurityContextLogoutHandler().logout(request,response,authentication);
    }

    return "redirect:/login?logout";
  }


  private String getPrincipal(){
    String userName=null;
    Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if(principal instanceof UserDetails){
      userName=((UserDetails) principal).getUsername();
    }else{
      userName=principal.toString();
    }
    return userName;
  }
}
