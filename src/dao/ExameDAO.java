package dao;

import model.Exame;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;

public class ExameDAO {

    // Inserir Exame (CREATE)
    public void inserirExame(Exame exame) {
        String sql = "INSERT INTO exame (nome, tipo, data, resultado, valor, id_paciente, id_medico) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, exame.getNome());
            stmt.setString(2, exame.getTipo());
            stmt.setString(3, exame.getData());
            stmt.setString(4, exame.getResultado());
            stmt.setDouble(5, exame.getValor());
            stmt.setInt(6, exame.getIdPaciente());
            stmt.setInt(7, exame.getIdMedico());

            stmt.executeUpdate();
            System.out.println("Exame inserido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar Exames (READ)
    public List<Exame> listarExames() {
        String sql = "SELECT * FROM exame";
        List<Exame> exames = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Exame exame = new Exame();
                exame.setId(rs.getInt("id"));
                exame.setNome(rs.getString("nome"));
                exame.setTipo(rs.getString("tipo"));
                exame.setData(rs.getString("data"));
                exame.setResultado(rs.getString("resultado"));
                exame.setValor(rs.getDouble("valor"));
                exame.setIdPaciente(rs.getInt("id_paciente"));
                exame.setIdMedico(rs.getInt("id_medico"));
                exames.add(exame);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exames;
    }

    // Atualizar Exame (UPDATE)
    public void atualizarExame(Exame exame) {
        String sql = "UPDATE exame SET tipo = ?, data = ?, resultado = ?, valor = ?, id_paciente = ?, id_medico = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, exame.getTipo());
            stmt.setString(2, exame.getData());
            stmt.setString(3, exame.getResultado());
            stmt.setDouble(4, exame.getValor());
            stmt.setInt(5, exame.getIdPaciente());
            stmt.setInt(6, exame.getIdMedico());
            stmt.setInt(7, exame.getId());

            stmt.executeUpdate();
            System.out.println("Exame atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletar Exame (DELETE)
    public void deletarExame(int id) {
        String sql = "DELETE FROM exame WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Exame deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
