package br.cspi.dao;

import br.cspi.model.Agendamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    // INSERIR
    public void inserir(Agendamento agendamento) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            String sql = "INSERT INTO agendamento (data, horario, pet_id, servico_id, funcionario_id, cliente_usuario_id, status_pagamento) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, agendamento.getData());
            stmt.setTime(2, agendamento.getHorario());
            stmt.setInt(3, agendamento.getPet_id());
            stmt.setInt(4, agendamento.getServico_id());
            stmt.setInt(5, agendamento.getFuncionario_id());
            stmt.setInt(6, agendamento.getCliente_usuario_id());
            stmt.setString(7, agendamento.getStatus_pagamento());

            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao inserir agendamento", e);
        }
    }


    // LISTAR AGENDAMENTOS POR USUÁRIO
    public ArrayList<Agendamento> listar(int usuarioId) {
        ArrayList<Agendamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM agendamento WHERE cliente_usuario_id = ? ORDER BY data, horario";

        try (Connection conn = ConectarBancoDados.conectarBancoDados();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Agendamento ag = new Agendamento();
                ag.setId(rs.getInt("id"));
                ag.setData(rs.getDate("data"));
                ag.setHorario(rs.getTime("horario"));
                ag.setPet_id(rs.getInt("pet_id"));
                ag.setServico_id(rs.getInt("servico_id"));
                ag.setFuncionario_id(rs.getInt("funcionario_id"));
                ag.setObservacao(rs.getString("observacao"));
                ag.setStatus(rs.getString("status"));
                ag.setPagamento(rs.getString("status_pagamento"));
                ag.setCliente_usuario_id(rs.getInt("cliente_usuario_id"));

                System.out.println("Agendamento: " + ag + "status: " + ag.getStatus());



                lista.add(ag);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // EXCLUIR
    public boolean excluir(int id) {
        String sql = "DELETE FROM agendamento WHERE id = ?";

        try (Connection conn = ConectarBancoDados.conectarBancoDados();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    // BUSCAR POR ID
    public Agendamento buscarPorId(int id) {
        String sql = "SELECT * FROM agendamento WHERE id = ?";
        Agendamento ag = null;

        try (Connection conn = ConectarBancoDados.conectarBancoDados();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ag = new Agendamento();
                ag.setId(rs.getInt("id"));
                ag.setData(rs.getDate("data"));
                ag.setHorario(rs.getTime("horario"));
                ag.setPet_id(rs.getInt("pet_id"));
                ag.setServico_id(rs.getInt("servico_id"));
                ag.setFuncionario_id(rs.getInt("funcionario_id"));
                ag.setObservacao(rs.getString("observacao"));
                ag.setStatus(rs.getString("status"));
                ag.setPagamento(rs.getString("status_pagamento"));
                ag.setCliente_usuario_id(rs.getInt("cliente_usuario_id"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return ag;
    }

    // ATUALIZAR STATUS E PAGAMENTO
    public boolean atualizarStatus(int id, String status, String status_pagamento) {
        String sql = "UPDATE agendamento SET status = ?, status_pagamento = ? WHERE id = ?";

        try (Connection conn = ConectarBancoDados.conectarBancoDados();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setString(2, status_pagamento);
            stmt.setInt(3, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    // EDITAR AGENDAMENTO COMPLETO
    public boolean editar(Agendamento agendamento) {
        String sql = "UPDATE agendamento SET data = ?, horario = ?, pet_id = ?, servico_id = ?, funcionario_id = ?, status = ?, status_pagamento = ? WHERE id = ?";

        try (Connection conn = ConectarBancoDados.conectarBancoDados();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, agendamento.getData());
            stmt.setTime(2, agendamento.getHorario());
            stmt.setInt(3, agendamento.getPet_id());
            stmt.setInt(4, agendamento.getServico_id());
            stmt.setInt(5, agendamento.getFuncionario_id());
            stmt.setString(6, agendamento.getStatus());
            stmt.setString(7, agendamento.getStatus_pagamento());
            stmt.setInt(8, agendamento.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Agendamento> buscarPorData(int usuarioId, Date data) {
        List<Agendamento> agendamentos = new ArrayList<>();

        try (Connection conn = ConectarBancoDados.conectarBancoDados()) {
            String sql = "SELECT a.*, p.nomepet, s.nome AS servico_nome, f.nome AS funcionario_nome " +
                    "FROM agendamento a " +
                    "JOIN pet p ON a.pet_id = p.id " +
                    "JOIN servico s ON a.servico_id = s.id " +
                    "JOIN funcionario f ON a.funcionario_id = f.id " +
                    "WHERE a.data = ? AND a.cliente_usuario_id = ? " +
                    "ORDER BY a.horario";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, data);
            stmt.setInt(2, usuarioId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Agendamento ag = new Agendamento();
                ag.setId(rs.getInt("id"));
                ag.setData(rs.getDate("data"));
                ag.setHorario(rs.getTime("horario"));
                ag.setPet_id(rs.getInt("pet_id"));
                ag.setServico_id(rs.getInt("servico_id"));
                ag.setFuncionario_id(rs.getInt("funcionario_id"));
                ag.setCliente_usuario_id(rs.getInt("cliente_usuario_id"));
                ag.setStatus(rs.getString("status"));
                ag.setStatus_pagamento(rs.getString("status_pagamento"));

                // extras para exibir na tabela
                ag.setPetNome(rs.getString("nomepet"));
                ag.setServicoNome(rs.getString("servico_nome"));
                ag.setFuncionarioNome(rs.getString("funcionario_nome"));

                agendamentos.add(ag);
            }

            rs.close();
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao buscar agendamentos por data", e);
        }

        return agendamentos;
    }

    public void marcarComoPago(int id) {
        String sql = "UPDATE agendamento SET status_pagamento = 'Pago' WHERE id = ?";

        try (Connection conn = ConectarBancoDados.conectarBancoDados();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao marcar pagamento como Pago", e);
        }
    }
}
