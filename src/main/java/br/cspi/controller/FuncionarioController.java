package br.cspi.controller;

import br.cspi.model.Funcionario;
import br.cspi.service.FuncionarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/Funcionario")
public class FuncionarioController {

    @GetMapping
    public String listarFuncionarios(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("usuarioId");
        if (id == null) {
            return "redirect:/login";
        } else {
            List<Funcionario> funcionarios = new FuncionarioService().listarFuncionarios(id);
            model.addAttribute("funcionarios", funcionarios);
            return "pages/tabelaFuncionarios";
        }
    }

    @PostMapping("/Excluir")
    public String excluirFuncionario(@RequestParam("opcao") int funcionario_id) {
        new FuncionarioService().excluirFuncionario(funcionario_id);
        return "redirect:/Funcionario";
    }

    @PostMapping("/Edicao")
    public String edicaoFuncionario(@RequestParam("opcao") int funcionario_id, HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("usuarioId");
        Funcionario funcionario = new FuncionarioService().buscarFuncionario(funcionario_id, id);

        model.addAttribute("funcionario", funcionario);
        return "pages/editarFuncionario";
    }

    @PostMapping("/Editar")
    public String editarFuncionario(HttpSession session,
                                    @RequestParam("opcao") int funcionario_id,
                                    @RequestParam("nome") String nome,
                                    @RequestParam("cpf") String cpf,
                                    @RequestParam("telefone") String telefone,
                                    @RequestParam("cargo") String cargo,
                                    @RequestParam("salario") BigDecimal salario,
                                    @RequestParam(value = "ativo", required = false) boolean ativo) {

        int id = (Integer) session.getAttribute("usuarioId");

        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionario_id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setTelefone(telefone);
        funcionario.setCargo(cargo);
        funcionario.setSalario(salario);
        funcionario.setAtivo(ativo);
        funcionario.setCliente_usuario_id(id);

        if (new FuncionarioService().editarFuncionario(funcionario, funcionario_id)) {
            return "redirect:/Funcionario";
        } else {
            return "redirect:/Edicao";
        }
    }

    @PostMapping("/Cadastro")
    public String formAdicionarFuncionario(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("usuarioId");

        model.addAttribute("idUsuario", id);
        return "pages/cadastroFuncionario";
    }

    @PostMapping("/Cadastrar")
    public String adicionarFuncionario(@RequestParam("idUsuario") int cliente_usuario_id,
                                       @RequestParam("nome") String nome,
                                       @RequestParam("cpf") String cpf,
                                       @RequestParam("telefone") String telefone,
                                       @RequestParam("cargo") String cargo,
                                       @RequestParam("salario") BigDecimal salario,
                                       @RequestParam(value = "ativo", required = false) boolean ativo) {

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setTelefone(telefone);
        funcionario.setCargo(cargo);
        funcionario.setSalario(salario);
        funcionario.setAtivo(ativo);
        funcionario.setCliente_usuario_id(cliente_usuario_id);

        if (new FuncionarioService().adicionarFuncionario(funcionario)) {
            return "redirect:/Funcionario";
        } else {
            return "redirect:/Cadastro";
        }
    }
}
