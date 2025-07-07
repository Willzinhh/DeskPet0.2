package br.cspi.controller;

import br.cspi.dao.Cliente_UsuarioDAO;
import br.cspi.dao.PetDAO;
import br.cspi.model.Agendamento;
import br.cspi.model.Pet;
import br.cspi.model.Servico;
import br.cspi.model.Funcionario;
import br.cspi.service.AgendamentoService;
import br.cspi.service.FuncionarioService;
import br.cspi.service.PetsService;
import br.cspi.service.ServicoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/Agendamento")
public class AgendamentoController {

    @GetMapping
    public String agendaSemanal(@RequestParam(value = "data", required = false)
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataSelecionada,
                                HttpSession session,
                                Model model) {
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");

        if (dataSelecionada == null) {
            dataSelecionada = LocalDate.now();
        }

        List<Agendamento> agendamentos = new AgendamentoService().listarPorData(usuarioId, Date.valueOf(dataSelecionada));
        model.addAttribute("agendamentos", agendamentos);
        model.addAttribute("dataSelecionada", dataSelecionada.toString());
        model.addAttribute("anterior", dataSelecionada.minusDays(1).toString());
        model.addAttribute("proximo", dataSelecionada.plusDays(1).toString());

        return "pages/agendaSemanal";
    }

    @GetMapping("/AdicionarAgendamento")
    public String novoAgendamento(HttpSession session, Model model) {
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");

        model.addAttribute("pets", new PetsService().listarPets(usuarioId));
        model.addAttribute("servicos", new ServicoService().listarServicos(usuarioId));
        model.addAttribute("funcionarios", new FuncionarioService().listarFuncionarios(usuarioId));

        return "pages/agendamento";
    }

    @PostMapping("/Adicionar")
    public String adicionarAgendamento( HttpSession session,
                                        @RequestParam("data") String dataStr,
                                        @RequestParam("horario") String horarioStr,
                                        @RequestParam("pet_id") int petId,
                                        @RequestParam("servico_id") int servicoId,
                                        @RequestParam("funcionario_id") int funcionarioId
    ) {
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");


        try {
            LocalDate data = LocalDate.parse(dataStr); // "2025-07-01"
            LocalTime hora = LocalTime.parse(horarioStr); // "13:30"
            Time horario = Time.valueOf(hora); // Converte para java.sql.Time

            Agendamento agendamento = new Agendamento();
            agendamento.setData(Date.valueOf(data));
            agendamento.setHorario(horario);
            agendamento.setPet_id(petId);
            agendamento.setServico_id(servicoId);
            agendamento.setFuncionario_id(funcionarioId);
            agendamento.setStatus_pagamento("Pendente");
            agendamento.setCliente_usuario_id(usuarioId);

            new AgendamentoService().adicionar(agendamento);
            return "redirect:/Agendamento";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Agendamento/AdicionarAgendamento";
        }
    }

    @PostMapping("/Excluir")
    public String excluirAgendamento(@RequestParam("opcao") int agendamento_id) {
        new AgendamentoService().excluir(agendamento_id);
        return "redirect:/Agendamento";
    }

    @PostMapping("/EdicaoAgendamento")
    public String editarAgendamento(HttpSession session,@RequestParam("opcao") int agendamento_id,Model model) {
        int usuarioId = (Integer) session.getAttribute("usuarioId");

        Agendamento agendamento = new AgendamentoService().buscarPorId(agendamento_id);

        List<Pet> pets = new PetsService().listarPets(usuarioId);
        List<Servico> servicos = new ServicoService().listarServicos(usuarioId);
        List<Funcionario> funcionarios = new FuncionarioService().listarFuncionarios(usuarioId);

        model.addAttribute("agendamento", agendamento);
        model.addAttribute("pets", pets);
        model.addAttribute("servicos", servicos);
        model.addAttribute("funcionarios", funcionarios);

        return "pages/editarAgendamento";
    }

    @PostMapping("/Editar")
    public String editarAgendamento(@RequestParam("opcao") int agendamento_id, Model model,
                                    @RequestParam("petId") int petid,
                                    @RequestParam("servicoId") int servicoid,
                                    @RequestParam("funcionarioId") int funcionarioid,
                                    @RequestParam("data") String data,
                                    @RequestParam("hora") String hora,
                                    @RequestParam("status") String status,
                                    @RequestParam("status_pagamento") String statusPagamento
                                    ) {

        Date dataStr = Date.valueOf(data); // precisa estar no formato "yyyy-MM-dd"

        if (hora.length() == 5) { // ex: "14:30"
            hora += ":00"; // transforma para "14:30:00"
        }
        Time horastr = Time.valueOf(hora); // agora está no formato HH:mm:ss

        Agendamento agendamento = new Agendamento();
        agendamento.setId(agendamento_id);
        agendamento.setPet_id(petid);
        agendamento.setServico_id(servicoid);
        agendamento.setFuncionario_id(funcionarioid);
        agendamento.setData(dataStr);
        agendamento.setHorario(horastr);
        agendamento.setStatus(status);
        agendamento.setStatus_pagamento(statusPagamento);

        new AgendamentoService().editar(agendamento);

        return "redirect:/Agendamento";
    }


    @PostMapping("/AtualizarStatus")
    public String atualizarStatus(@RequestParam("opcao") int id,
                                  @RequestParam("acao") String acao
    ){

          new AgendamentoService().atualizarStatus(id, acao);

        return "redirect:/Agendamento";
    }
}
