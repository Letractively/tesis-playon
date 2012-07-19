package tesis.playon.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AccessController {

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required = false) String message) {
	model.addAttribute("message", message);
	return "access/login";
    }

    @RequestMapping(value = "/denied")
    public String denied() {
	return "access/denied";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure() {
	String message = "�Usuario y/o contrase�a incorrectos!";
	return "redirect:/login?message=" + message;
    }

    @RequestMapping(value = "/logout/success")
    public String logoutSuccess() {
	String message = "�Accesso logrado!";
	return "redirect:/login?message=" + message;
    }
}