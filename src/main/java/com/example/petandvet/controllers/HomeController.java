package com.example.petandvet.controllers;

import com.example.petandvet.models.LoginUser;
import com.example.petandvet.models.Product;
import com.example.petandvet.models.User;
import com.example.petandvet.services.BreedService;
import com.example.petandvet.services.PetService;
import com.example.petandvet.services.ProductService;
import com.example.petandvet.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@AllArgsConstructor
@Controller
public class HomeController {

  private UserService userServ;
  private BreedService breedServ;
  private PetService petServ;
  private ProductService productServ;

  @GetMapping("/")
  public String index(Model model, HttpSession session) {
    if (session.getAttribute("user_id") != null) {
      return "redirect:/home";
    }
    model.addAttribute("newUser", new User());
    model.addAttribute("newLogin", new LoginUser());
    return "login.jsp";
  }

  @PostMapping("/register")
  public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result,
                         Model model, HttpSession session) {
    User regUser = userServ.register(newUser, result);
    if (result.hasErrors()) {
      model.addAttribute("newLogin", new LoginUser());
      return "login.jsp";
    }

    session.setAttribute("user_id", regUser.getId());
    Long userId = (Long) session.getAttribute("user_id");
    User user = userServ.findUserById(userId);
//    Cart cart = new Cart();
//    cart.setUser(user);
//    user.setCart(cart);
    return "redirect:/home";
  }

  @PostMapping("/login")
  public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result,
                      Model model, HttpSession session) {
    User logUser = userServ.login(newLogin, result);
    if (result.hasErrors()) {
      model.addAttribute("newUser", new User());
      return "login.jsp";
    }
    session.setAttribute("user_id", logUser.getId());
    Long x = (Long) session.getAttribute("user_id");
    if (x == 1) {
      return "redirect:/admin";
    }
    return "redirect:/home";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }


  @GetMapping("/profilePage")
  public String profilePage(HttpSession session, Model model) {
    if (session.getAttribute("user_id") == null) {
      return "redirect:/";
    }
    User user = userServ.findUserById((Long) session.getAttribute("user_id"));
    model.addAttribute("user", user);
    model.addAttribute("pets", petServ.getPetsByLocation(user.getLocation()));
    model.addAttribute("breed", breedServ.getAllBreeds());
    return "profilePage.jsp";
  }


  @GetMapping("/aboutUs")
  public String abouUsPage(HttpSession session, Model model) {
    if (session.getAttribute("user_id") == null) {

      return "redirect:/";
    }
    User user = userServ.findUserById((Long) session.getAttribute("user_id"));
    model.addAttribute("user", user);

    return "aboutUs.jsp";
  }

  @GetMapping("/admin")
  public String admin(Model model) {
    List<Product>productList = productServ.getAllProducts();
    model.addAttribute("products", productList);

    return "admin.jsp";
  }




}