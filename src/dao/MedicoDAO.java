package dao;
import model.Medico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;
public class MedicoDAO {

    // Inserir Medico (CREATE)
    public void inserirMedico(Medico medico) {
        String sql = "INSERT INTO medico (nome, crm, especialidade, contato) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCrm());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setString(4, medico.getContato());

            stmt.executeUpdate();
            System.out.println("Medico inserido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consultar Medicos (READ)
    public List<Medico> listarMedicos() {
        String sql = "SELECT * FROM medico";
        List<Medico> medicos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCrm(rs.getString("crm"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medico.setContato(rs.getString("contato"));
                medicos.add(medico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicos;
    }

    // Atualizar Medico (UPDATE)
    public void atualizarMedico(Medico medico) {
        String sql = "UPDATE medico SET nome = ?, crm = ?, especialidade = ?, contato = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCrm());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setString(4, medico.getContato());
            stmt.setInt(5, medico.getId());

            stmt.executeUpdate();
            System.out.println("Medico atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletar Medico (DELETE)
    public void deletarMedico(int id) {
        String sql = "DELETE FROM medico WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Medico deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
