package br.cspi.dao;

import br.cspi.model.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicoDAO {

    //            ALTERAR
    public String alterer(Servico servicos, Integer id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement("UPDATE servico SET nome = ?, descricao = ?, valor = ?, tempo = ?::interval WHERE id = ?"
            );

            stmt.setString(1, servicos.getNome());
            stmt.setString(2, servicos.getDescricao());
            stmt.setDouble(3, servicos.getValor());
            stmt.setString(4, servicos.getTempo());
            stmt.setInt(5, id);


            stmt.execute();


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }

        return "Alterado com Sucesso";
    }

    //            EXCLUIR
    public String excluir(int id) {
        try {

//
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement("delete from servico where id = ?"
            );

            stmt.setInt(1, id);
            stmt.execute();

            if(stmt.getUpdateCount()<=0){
                return "Nenhuem servico exculuido";
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
        return "Excluido com sucesso";
    }

    //            INSERIR
    public String inserir(Servico servicos) {
        //conecta com banco
        //monta sql inserir
        //executa sql

        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement("insert into servico(nome, descricao, valor, tempo, cliente_usuario_id) values(?, ?, ?, ?::interval, ?)"
            );


            stmt.setString(1, servicos.getNome());
            stmt.setString(2, servicos.getDescricao());
            stmt.setDouble(3, servicos.getValor());
            stmt.setString(4, servicos.getTempo());
            stmt.setInt(5, servicos.getCliente_usuario_id());

            stmt.execute();


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir");
            throw new RuntimeException(e);


        }

        return "Inserido com Sucesso";
    }



    //            GET Servicos
    public ArrayList<Servico> getServicos(int id) {
        ArrayList<Servico> servicos = new ArrayList<>();


        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();

            String sql = "SELECT * FROM servico WHERE cliente_usuario_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id); // Aqui passamos o valor do id com segurança

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Servico s = new Servico();
                s.setId(rs.getInt("id"));
                s.setNome(rs.getString("nome"));
                s.setDescricao(rs.getString("descricao"));
                s.setValor(rs.getDouble("valor"));
                s.setTempo(rs.getString("tempo"));
                s.setCliente_usuario_id(rs.getInt("cliente_usuario_id"));

                servicos.add(s);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao conectar");
            ex.printStackTrace();
        }


        return servicos;
    }
}
