package br.cspi.service;


import br.cspi.dao.PetDAO;
import br.cspi.dao.ServicoDAO;
import br.cspi.model.Pet;
import br.cspi.model.Servico;

import java.util.ArrayList;

public class ServicoService {

    public ArrayList<Servico> listarServicos(int idUsuario) {
        ServicoDAO dao = new ServicoDAO();

        return dao.getServicos(idUsuario);
    }

    public boolean cadastrarServico(Servico servico, int id) {
        ServicoDAO dao = new ServicoDAO();
        System.out.println(id);
        servico.setCliente_usuario_id(id);

        dao.inserir(servico);

        return true;
    }

    public void excluirServico(int idServico) {
        ServicoDAO dao = new ServicoDAO();
        dao.excluir(idServico);
    }

    public Servico buscarServico(int idServico, int idUsuario){
        ServicoDAO dao = new ServicoDAO();

        for (Servico servico : dao.getServicos(idUsuario)) {
            if(servico.getId() == idServico){
                return servico;
            }
        }
        return null;
    }

    public boolean editarServico(Servico servico, int idServico){

        ServicoDAO dao = new ServicoDAO();



        dao.alterer(servico, idServico);
        return true;
    }
}
