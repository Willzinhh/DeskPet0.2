package br.cspi.controller;

import br.cspi.model.Clientes;
import br.cspi.model.Pet;
import br.cspi.service.ClientesService;
import br.cspi.service.PetsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String dashboard(HttpSession session) {

        Integer id = (Integer) session.getAttribute("usuarioId");
        System.out.println(id);
        if (id == null) {
            return "redirect:/login";
        }else {
            return "pages/dashboard";
        }
    }



}
