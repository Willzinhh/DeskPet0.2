package br.cspi.service;

import br.cspi.dao.FuncionarioDAO;
import br.cspi.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {

    public ArrayList<Funcionario> listarFuncionarios(int idUsuario) {
        FuncionarioDAO dao = new FuncionarioDAO();
        return dao.getFuncionarios(idUsuario);
    }

    public boolean adicionarFuncionario(Funcionario funcionario) {
        FuncionarioDAO dao = new FuncionarioDAO();
        dao.inserir(funcionario);
        return true;
    }

    public boolean editarFuncionario(Funcionario funcionario, int idFuncionario) {
        FuncionarioDAO dao = new FuncionarioDAO();
        dao.alterar(funcionario, idFuncionario);
        return true;
    }

    public void excluirFuncionario(int idFuncionario) {
        FuncionarioDAO dao = new FuncionarioDAO();
        dao.excluir(idFuncionario);
    }

    public Funcionario buscarFuncionario(int idFuncionario, int idUsuario) {
        FuncionarioDAO dao = new FuncionarioDAO();

        // Garante que o funcionário pertence ao usuário logado
        for (Funcionario f : dao.getFuncionarios(idUsuario)) {
            if (f.getId() == idFuncionario) {
                return f;
            }
        }

        return null;
    }
}

