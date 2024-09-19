package model;

public class Exame {
    private int id;
    private String nome;
    private String tipo;
    private String data;
    private String resultado;
    private double valor;
    private int idPaciente;
    private int idMedico;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public int getIdMedico() { return idMedico; }
    public void setIdMedico(int idMedico) { this.idMedico = idMedico; }
}
