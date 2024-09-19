package dao;
import model.Pagamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;

public class PagamentoDAO {

    // Inserir Pagamento (CREATE)
    public void inserirPagamento(Pagamento pagamento) {
        String sql = "INSERT INTO pagamento (valor, data, metodo, id_paciente) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, pagamento.getValor());
            stmt.setString(2, pagamento.getData());
            stmt.setString(3, pagamento.getFormaPagamento());
            stmt.setInt(4, pagamento.getIdPaciente());


            stmt.executeUpdate();
            System.out.println("Pagamento inserido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consultar Pagamentos (READ)
    public List<Pagamento> listarPagamentos() {
        String sql = "SELECT * FROM pagamento";
        List<Pagamento> pagamentos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setId(rs.getInt("id"));
                pagamento.setValor(rs.getDouble("valor"));
                pagamento.setData(rs.getString("data"));
                pagamento.setMetodo(rs.getString("metodo"));
                pagamento.setIdPaciente(rs.getInt("id_paciente"));
                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagamentos;
    }

    // Atualizar Pagamento (UPDATE)
    public void atualizarPagamento(Pagamento pagamento) {
        String sql = "UPDATE pagamento SET valor = ?, data = ?, metodo = ?, id_paciente = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, pagamento.getValor());
            stmt.setString(2, pagamento.getData());
            stmt.setString(3, pagamento.getMetodo());
            stmt.setInt(4, pagamento.getId());
            stmt.setInt(5, pagamento.getIdPaciente());

            stmt.executeUpdate();
            System.out.println("Pagamento atualizado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Deletar Pagamento (DELETE)
    public void deletarPagamento(int id) {
        String sql = "DELETE FROM pagamento WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Pagamento deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
