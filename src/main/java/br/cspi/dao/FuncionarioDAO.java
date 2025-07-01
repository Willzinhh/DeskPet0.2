package br.cspi.dao;

import br.cspi.model.Funcionario;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class FuncionarioDAO {

    // INSERIR
    public String inserir(Funcionario funcionario) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO funcionario (nome, cpf, telefone, cargo, salario, ativo, cliente_usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getCargo());
            stmt.setBigDecimal(5, funcionario.getSalario());
            stmt.setBoolean(6, funcionario.isAtivo());
            stmt.setInt(7, funcionario.getCliente_usuario_id());

            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir funcionário");
            throw new RuntimeException(e);
        }

        return "Funcionário inserido com sucesso";
    }

    // ALTERAR
    public String alterar(Funcionario funcionario, int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE funcionario SET nome = ?, cpf = ?, telefone = ?, cargo = ?, salario = ?, ativo = ?, cliente_usuario_id = ? WHERE id = ?"
            );

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getCargo());
            stmt.setBigDecimal(5, funcionario.getSalario());
            stmt.setBoolean(6, funcionario.isAtivo());
            stmt.setInt(7, funcionario.getCliente_usuario_id());
            stmt.setInt(8, id);

            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return "Funcionário alterado com sucesso";
    }

    // EXCLUIR
    public String excluir(int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM funcionario WHERE id = ?");
            stmt.setInt(1, id);
            stmt.execute();

            if (stmt.getUpdateCount() <= 0) {
                return "Nenhum funcionário excluído";
            }

            stmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return "Funcionário excluído com sucesso";
    }

    // LISTAR POR USUÁRIO
    public ArrayList<Funcionario> getFuncionarios(int clienteUsuarioId) {
        ArrayList<Funcionario> lista = new ArrayList<>();

        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM funcionario WHERE cliente_usuario_id = ?"
            );
            stmt.setInt(1, clienteUsuarioId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setTelefone(rs.getString("telefone"));
                f.setCargo(rs.getString("cargo"));
                f.setSalario(rs.getBigDecimal("salario"));
                f.setAtivo(rs.getBoolean("ativo"));
                f.setCliente_usuario_id(rs.getInt("cliente_usuario_id"));
                lista.add(f);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // BUSCAR POR ID
    public Funcionario buscarFuncionario(int id) {
        Funcionario f = null;

        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM funcionario WHERE id = ?"
            );
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setTelefone(rs.getString("telefone"));
                f.setCargo(rs.getString("cargo"));
                f.setSalario(rs.getBigDecimal("salario"));
                f.setAtivo(rs.getBoolean("ativo"));
                f.setCliente_usuario_id(rs.getInt("cliente_usuario_id"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return f;
    }
}
