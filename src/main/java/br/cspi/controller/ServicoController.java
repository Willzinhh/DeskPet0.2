package br.cspi.controller;

import br.cspi.model.Servico;
import br.cspi.service.ServicoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.util.List;

@Controller
@RequestMapping("/Servico")
public class ServicoController {

    @GetMapping
    public String listarServicos(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("usuarioId");


        List<Servico> servicos = new ServicoService().listarServicos(id);

        model.addAttribute("servicos", servicos);
        return "pages/tabelaServicos";
    }

    @GetMapping("/Cadastro")
    public String pageCadastro(HttpSession session) {
        Integer id = (Integer) session.getAttribute("usuarioId");


        return "pages/servicos";

    }

    @PostMapping("/Cadastrar")
    public String CadastrarServico(HttpSession session,
                                   @RequestParam("nomeServico") String nome,
                                   @RequestParam("descricao") String descricao,
                                   @RequestParam("preco") double valor,
                                   @RequestParam("tempo") String tempo)
    {
        Integer id = (Integer) session.getAttribute("usuarioId");




        Servico servico = new Servico();
        servico.setNome(nome);
        servico.setDescricao(descricao);
        servico.setValor(valor);
        servico.setTempo(tempo);

        if (new ServicoService().cadastrarServico(servico, id)){
            return "redirect:/Servico";
        }
        else {
            return "redirect:/Cadastro";
        }


    }

    @PostMapping("Excluir")
    public String excluirServico(HttpSession session, @RequestParam("opcao") Integer idServico) {


        new ServicoService().excluirServico(idServico);
        return "redirect:/Servico";
    }

    @PostMapping("/Edicao")
    public String editarPage(HttpSession session, @RequestParam("opcao") Integer idServico, Model model) {
        Integer id = (Integer) session.getAttribute("usuarioId");
        System.out.println(id);

        Servico servico = new ServicoService().buscarServico(idServico, id);
        model.addAttribute("servico", servico);
        return "pages/editarServico";
    }

    @PostMapping("/Editar")
    public String editar(HttpSession session,
                         @RequestParam("id") int idServico,
                         @RequestParam("nome") String nome,
                         @RequestParam("descricao") String descricao,
                         @RequestParam("valor") double valor,
                         @RequestParam("tempo") String tempo) {


        Servico servico = new Servico();
        servico.setNome(nome);
        servico.setDescricao(descricao);
        servico.setValor(valor);
        servico.setTempo(tempo);

        new ServicoService().editarServico(servico, idServico);
        return "redirect:/Servico";


    }

}
