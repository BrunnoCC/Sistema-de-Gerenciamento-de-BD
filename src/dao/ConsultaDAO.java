package dao;
import model.Consulta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;

public class ConsultaDAO {

    // Inserir Consulta (CREATE)
    public void inserirConsulta(Consulta consulta) {
        String sql = "INSERT INTO consulta (data, hora, status, id_paciente, id_medico, descricao) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consulta.getData());
            stmt.setString(2, consulta.getHora());
            stmt.setString(3, consulta.getStatus());
            stmt.setInt(4, consulta.getIdPaciente());
            stmt.setInt(5, consulta.getIdMedico());
            stmt.setString(6, consulta.getDescricao());

            stmt.executeUpdate();
            System.out.println("Consulta inserida com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consultar Consultas (READ)
    public List<Consulta> listarConsultas() {
        String sql = "SELECT * FROM consulta";
        List<Consulta> consultas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setData(rs.getString("data"));
                consulta.setHora(rs.getString("hora"));
                consulta.setStatus(rs.getString("status"));
                consulta.setIdPaciente(rs.getInt("id_paciente"));
                consulta.setIdMedico(rs.getInt("id_medico"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultas;
    }

    // Atualizar Consulta (UPDATE)
    public void atualizarConsulta(Consulta consulta) {
        String sql = "UPDATE consulta SET data = ?, hora = ?, status = ?, id_paciente = ?, id_medico = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consulta.getData());
            stmt.setString(2, consulta.getHora());
            stmt.setString(3, consulta.getStatus());
            stmt.setInt(4, consulta.getIdPaciente());
            stmt.setInt(5, consulta.getIdMedico());
            stmt.setInt(6, consulta.getId());

            stmt.executeUpdate();
            System.out.println("Consulta atualizada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletar Consulta (DELETE)
    public void deletarConsulta(int id) {
        String sql = "DELETE FROM consulta WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Consulta deletada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
