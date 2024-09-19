package dao;

import model.Prontuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;

public class ProntuarioDAO {

    // Inserir Prontuário (CREATE)
    public void inserirProntuario(Prontuario prontuario) {
        String sql = "INSERT INTO prontuario (id_paciente, id_consulta) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prontuario.getIdPaciente());
            stmt.setInt(2, prontuario.getIdConsulta());



            stmt.executeUpdate();
            System.out.println("Prontuário inserido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar Prontuários (READ)
    public List<Prontuario> listarProntuarios() {
        String sql = "SELECT * FROM prontuario";
        List<Prontuario> prontuarios = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Prontuario prontuario = new Prontuario();
                prontuario.setId(rs.getInt("id"));
                prontuario.setIdPaciente(rs.getInt("id_paciente"));
                prontuario.setIdConsulta(rs.getInt("id_consulta"));


                prontuarios.add(prontuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prontuarios;
    }

    // Atualizar Prontuário (UPDATE)
    public void atualizarProntuario(Prontuario prontuario) {
        String sql = "UPDATE prontuario SET id_paciente = ?, id_consulta = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prontuario.getIdPaciente());
            stmt.setInt(2, prontuario.getIdConsulta());
            stmt.setInt(3, prontuario.getId());

            stmt.executeUpdate();
            System.out.println("Prontuário atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletar Prontuário (DELETE)
    public void deletarProntuario(int id) {
        String sql = "DELETE FROM prontuario WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Prontuário deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}