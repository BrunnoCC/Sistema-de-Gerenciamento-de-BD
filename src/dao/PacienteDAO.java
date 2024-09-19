package dao;

import model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;

public class PacienteDAO {
    public void inserirPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome, cpf, data_nascimento, contato) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getDataNascimento());
            stmt.setString(4, paciente.getContato());

            stmt.executeUpdate();
            System.out.println("Paciente inserido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> listarPacientes() {
        String sql = "SELECT * FROM paciente";
        List<Paciente> pacientes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setDataNascimento(rs.getString("data_nascimento"));
                paciente.setContato(rs.getString("contato"));
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    public void atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, data_nascimento = ?, contato = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getDataNascimento());
            stmt.setString(4, paciente.getContato());
            stmt.setInt(5, paciente.getId());

            stmt.executeUpdate();
            System.out.println("Paciente atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarPaciente(int id) {
        String sql = "DELETE FROM paciente WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Paciente deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
