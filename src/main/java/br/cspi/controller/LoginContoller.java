package br.cspi.controller;

import br.cspi.model.Usuario;
import jakarta.servlet.http.HttpSession;
import br.cspi.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginContoller {
    @GetMapping("/home")
    public String index() {
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/Logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

    @PostMapping("/login")
    public String login(Model model, String email, String senha, HttpSession session) {
        System.out.println("Realizando o login o usuario" + email);
        System.out.println("brilha brilha");

       Usuario user = new LoginService().autenticar(email,senha);

        if (user != null ) {
            session.setAttribute("usuarioId", user.getId());
            System.out.println("Login com sucesso");
            return "redirect:dashboard";
        } else {
            model.addAttribute("msg", "Login ou senha incorreto!");
            return "login";
        }

    }

}