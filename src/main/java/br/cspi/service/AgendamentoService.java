package br.cspi.service;

import br.cspi.dao.AgendamentoDAO;
import br.cspi.model.Agendamento;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoService {

        private final AgendamentoDAO dao = new AgendamentoDAO();

        public boolean adicionar(Agendamento agendamento) {
            AgendamentoDAO dao = new AgendamentoDAO();
            dao.inserir(agendamento);
            return true;
        }

        public List<Agendamento> listar(int usuarioId) {
            return new AgendamentoDAO().listar(usuarioId);
        }

        public Agendamento buscarPorId(int id) {
            return dao.buscarPorId(id);
        }

        public boolean excluir(int id) {
            return dao.excluir(id);
        }

        public boolean atualizarStatus(int id, String acao) {
            String status = "ERRO";
            String pagar = "ERRO";
            if (acao.equals("pagar")) {
                status = dao.buscarPorId(id).getStatus();
                pagar = "PAGO";
            }
            if (acao.equals("cancelar")) {
                 pagar = dao.buscarPorId(id).getStatus_pagamento();
                 status = "Cancelado";
            }

            return dao.atualizarStatus(id, status, pagar );
        }

        public boolean editar(Agendamento agendamento) {
            return dao.editar(agendamento);
        }

    public List<Agendamento> listarPorData(int usuarioId, Date data) {
        AgendamentoDAO dao = new AgendamentoDAO();
        return dao.buscarPorData(usuarioId, data);
    }

    public void marcarComoPago(int id) {
        dao.marcarComoPago(id);
    }

}


