package br.cspi.service;

import br.cspi.dao.ClientesDAO;
import br.cspi.model.Clientes;

import java.util.ArrayList;

public class ClientesService {

    public ArrayList<Clientes> listarClientes(int idCliente) {
        ClientesDAO dao = new ClientesDAO();


        return dao.getClientes(idCliente);

    }

    public boolean cadastrarCliente(Clientes cliente, int id) {
        ClientesDAO dao = new ClientesDAO();
        System.out.println(id);
        for (Clientes c: dao.getClientes(id)){
            if(c.getCpf().equals(cliente.getCpf())){
                System.out.println("CPF já cadastrado");
                return false;
            }
        }
        dao.inserir(cliente);

        return true;
    }



            public Clientes buscarCliente(int idCliente, int idUsuario){
                ClientesDAO dao = new ClientesDAO();

                for (Clientes cliente : dao.getClientes(idUsuario)) {
                    if(cliente.getId() == idCliente){
                        return cliente;
                    }
                }
                return null;
            }


            public void excluirCliente(int id){
                ClientesDAO dao = new ClientesDAO();
                dao.excluir(id);
            }



            public boolean editarCliente(Clientes cliente, int idCliente){
                ClientesDAO dao = new ClientesDAO();

                dao.alterer(cliente, idCliente);
                return true;
            }



    }

