package main;


import java.util.Scanner;
import java.util.List;
import model.Paciente;
import model.Medico;
import model.Consulta;
import model.Pagamento;
import model.Exame;
import model.Prontuario;
import dao.PacienteDAO;
import dao.MedicoDAO;
import dao.ConsultaDAO;
import dao.PagamentoDAO;
import dao.ExameDAO;
import dao.ProntuarioDAO;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PacienteDAO pacienteDAO = new PacienteDAO();
        MedicoDAO medicoDAO = new MedicoDAO();
        ConsultaDAO consultaDAO = new ConsultaDAO();
        PagamentoDAO pagamentoDAO = new PagamentoDAO();
        ExameDAO exameDAO = new ExameDAO();
        ProntuarioDAO prontuarioDAO = new ProntuarioDAO();

        int opcaoPrincipal = 0;
        while (opcaoPrincipal != 7) {
            System.out.println("\n*** Menu Principal ***");
            System.out.println("1. Pacientes");
            System.out.println("2. Médicos");
            System.out.println("3. Consultas");
            System.out.println("4. Pagamentos");
            System.out.println("5. Exames");
            System.out.println("6. Prontuários");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcaoPrincipal = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (opcaoPrincipal) {
                case 1:
                    menuPaciente(scanner, pacienteDAO);
                    break;
                case 2:
                    menuMedico(scanner, medicoDAO);
                    break;
                case 3:
                    menuConsulta(scanner, consultaDAO);
                    break;
                case 4:
                    menuPagamento(scanner, pagamentoDAO);
                    break;
                case 5:
                    menuExame(scanner, exameDAO);
                    break;
                case 6:
                    menuProntuario(scanner, prontuarioDAO);
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    // Menu de Pacientes
    private static void menuPaciente(Scanner scanner, PacienteDAO pacienteDAO) {
        int opcaoPaciente = 0;
        while (opcaoPaciente != 5) {
            System.out.println("\n*** Menu de Pacientes ***");
            System.out.println("1. Inserir Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Atualizar Paciente");
            System.out.println("4. Deletar Paciente");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoPaciente = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (opcaoPaciente) {
                case 1:
                    Paciente novoPaciente = new Paciente();
                    System.out.print("Nome: ");
                    novoPaciente.setNome(scanner.nextLine());
                    System.out.print("CPF: ");
                    novoPaciente.setCpf(scanner.nextLine());
                    System.out.print("Data de Nascimento (AAAA-MM-DD): ");
                    novoPaciente.setDataNascimento(scanner.nextLine());
                    System.out.print("Contato: ");
                    novoPaciente.setContato(scanner.nextLine());

                    pacienteDAO.inserirPaciente(novoPaciente);
                    System.out.println("Paciente inserido com sucesso!");
                    break;
                case 2:
                    List<Paciente> pacientes = pacienteDAO.listarPacientes();
                    for (Paciente p : pacientes) {
                        System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | CPF: " + p.getCpf() + " | Data de Nascimento : " + p.getDataNascimento() + " | Contato: " + p.getContato());
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do paciente que deseja atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    Paciente pacienteParaAtualizar = pacienteDAO.listarPacientes().stream().filter(p -> p.getId() == idAtualizar).findFirst().orElse(null);
                    if (pacienteParaAtualizar != null) {
                        System.out.print("Novo nome: ");
                        pacienteParaAtualizar.setNome(scanner.nextLine());
                        pacienteDAO.atualizarPaciente(pacienteParaAtualizar);
                        System.out.print("Novo CPF: ");
                        pacienteParaAtualizar.setCpf(scanner.nextLine());
                        pacienteDAO.atualizarPaciente(pacienteParaAtualizar);
                        System.out.print("Nova Data de Nascimento: ");
                        pacienteParaAtualizar.setDataNascimento(scanner.nextLine());
                        pacienteDAO.atualizarPaciente(pacienteParaAtualizar);
                        System.out.print("Novo Contato: ");
                        pacienteParaAtualizar.setContato(scanner.nextLine());
                        pacienteDAO.atualizarPaciente(pacienteParaAtualizar);
                        System.out.println("Paciente atualizado com sucesso!");
                    } else {
                        System.out.println("Paciente não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do paciente que deseja deletar: ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    pacienteDAO.deletarPaciente(idDeletar);
                    System.out.println("Paciente deletado com sucesso!");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Menu de Médicos
    private static void menuMedico(Scanner scanner, MedicoDAO medicoDAO) {
        int opcaoMedico = 0;
        while (opcaoMedico != 5) {
            System.out.println("\n*** Menu de Médicos ***");
            System.out.println("1. Inserir Médico");
            System.out.println("2. Listar Médicos");
            System.out.println("3. Atualizar Médico");
            System.out.println("4. Deletar Médico");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoMedico = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (opcaoMedico) {
                case 1:
                    Medico novoMedico = new Medico();
                    System.out.print("Nome: ");
                    novoMedico.setNome(scanner.nextLine());
                    System.out.print("CRM: ");
                    novoMedico.setCrm(scanner.nextLine());
                    System.out.print("Especialidade: ");
                    novoMedico.setEspecialidade(scanner.nextLine());
                    System.out.print("Contato: ");
                    novoMedico.setContato(scanner.nextLine());

                    medicoDAO.inserirMedico(novoMedico);
                    System.out.println("Médico inserido com sucesso!");
                    break;
                case 2:
                    List<Medico> medicos = medicoDAO.listarMedicos();
                    for (Medico m : medicos) {
                        System.out.println("ID: " + m.getId() + " | Nome: " + m.getNome() + " | CRM: " + m.getCrm() + " | Especialidade: " + m.getEspecialidade());
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do médico que deseja atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    Medico medicoParaAtualizar = medicoDAO.listarMedicos().stream().filter(m -> m.getId() == idAtualizar).findFirst().orElse(null);
                    if (medicoParaAtualizar != null) {
                        System.out.print("Novo nome: ");
                        medicoParaAtualizar.setNome(scanner.nextLine());
                        medicoDAO.atualizarMedico(medicoParaAtualizar);
                        System.out.print("Novo CRM: ");
                        medicoParaAtualizar.setCrm(scanner.nextLine());
                        medicoDAO.atualizarMedico(medicoParaAtualizar);
                        System.out.print("Nova Especialidade: ");
                        medicoParaAtualizar.setEspecialidade(scanner.nextLine());
                        medicoDAO.atualizarMedico(medicoParaAtualizar);
                        System.out.print("Novo Contato: ");
                        medicoParaAtualizar.setContato(scanner.nextLine());
                        medicoDAO.atualizarMedico(medicoParaAtualizar);
                        System.out.println("Médico atualizado com sucesso!");
                    } else {
                        System.out.println("Médico não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do médico que deseja deletar: ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    medicoDAO.deletarMedico(idDeletar);
                    System.out.println("Médico deletado com sucesso!");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Menu de Consultas
    private static void menuConsulta(Scanner scanner, ConsultaDAO consultaDAO) {
        int opcaoConsulta = 0;
        while (opcaoConsulta != 5) {
            System.out.println("\n*** Menu de Consultas ***");
            System.out.println("1. Inserir Consulta");
            System.out.println("2. Listar Consultas");
            System.out.println("3. Atualizar Consulta");
            System.out.println("4. Deletar Consulta");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoConsulta = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (opcaoConsulta) {
                case 1:
                    Consulta novaConsulta = new Consulta();
                    System.out.print("Data (AAAA-MM-DD): ");
                    novaConsulta.setData(scanner.nextLine());
                    System.out.print("Hora (HH:MM): ");
                    novaConsulta.setHora(scanner.nextLine());
                    System.out.print("Status: ");
                    novaConsulta.setStatus(scanner.nextLine());
                    System.out.print("ID do Paciente: ");
                    novaConsulta.setIdPaciente(scanner.nextInt());
                    System.out.print("ID do Médico: ");
                    novaConsulta.setIdMedico(scanner.nextInt());
                    scanner.nextLine(); // Consumir newline

                    consultaDAO.inserirConsulta(novaConsulta);
                    System.out.println("Consulta inserida com sucesso!");
                    break;
                case 2:
                    List<Consulta> consultas = consultaDAO.listarConsultas();
                    for (Consulta c : consultas) {
                        System.out.println("ID: " + c.getId() + " | Data: " + c.getData() + " | Hora: " + c.getHora() + " | Status: " + c.getStatus() + " | ID do Médico: " + c.getIdMedico() + " | Id do Paciente: " + c.getIdPaciente());
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID da consulta que deseja atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    Consulta consultaParaAtualizar = consultaDAO.listarConsultas().stream().filter(c -> c.getId() == idAtualizar).findFirst().orElse(null);
                    if (consultaParaAtualizar != null) {
                        System.out.print("Nova data: ");
                        consultaParaAtualizar.setData(scanner.nextLine());
                        consultaDAO.atualizarConsulta(consultaParaAtualizar);
                        System.out.print("Nova Hora: ");
                        consultaParaAtualizar.setHora(scanner.nextLine());
                        consultaDAO.atualizarConsulta(consultaParaAtualizar);
                        System.out.print("Novo status: ");
                        consultaParaAtualizar.setStatus(scanner.nextLine());
                        consultaDAO.atualizarConsulta(consultaParaAtualizar);
                        System.out.print("Novo ID paciente: ");
                        consultaParaAtualizar.setIdPaciente(scanner.nextInt());
                        consultaDAO.atualizarConsulta(consultaParaAtualizar);
                        System.out.print("Novo ID medico: ");
                        consultaParaAtualizar.setIdMedico(scanner.nextInt());
                        consultaDAO.atualizarConsulta(consultaParaAtualizar);
                        System.out.println("Consulta atualizada com sucesso!");
                    } else {
                        System.out.println("Consulta não encontrada.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID da consulta que deseja deletar: ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    consultaDAO.deletarConsulta(idDeletar);
                    System.out.println("Consulta deletada com sucesso!");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Menu de Pagamentos
    private static void menuPagamento(Scanner scanner, PagamentoDAO pagamentoDAO) {
        int opcaoPagamento = 0;
        while (opcaoPagamento != 5) {
            System.out.println("\n*** Menu de Pagamentos ***");
            System.out.println("1. Inserir Pagamento");
            System.out.println("2. Listar Pagamentos");
            System.out.println("3. Atualizar Pagamento");
            System.out.println("4. Deletar Pagamento");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoPagamento = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (opcaoPagamento) {
                case 1:
                    Pagamento novoPagamento = new Pagamento();
                    System.out.print("Valor: ");
                    novoPagamento.setValor(scanner.nextDouble());
                    scanner.nextLine(); // Consumir newline
                    System.out.print("Forma de Pagamento: ");
                    novoPagamento.setFormaPagamento(scanner.nextLine());
                    System.out.print("Data (AAAA-MM-DD): ");
                    novoPagamento.setData(scanner.nextLine());
                    System.out.print("ID do Paciente: ");
                    novoPagamento.setIdPaciente(scanner.nextInt());
                    scanner.nextLine(); // Consumir newline

                    pagamentoDAO.inserirPagamento(novoPagamento);
                    System.out.println("Pagamento inserido com sucesso!");
                    break;
                case 2:
                    List<Pagamento> pagamentos = pagamentoDAO.listarPagamentos();
                    for (Pagamento p : pagamentos) {
                        System.out.println("ID do Pagamento: " + p.getId() + " | ID do Paciente: " + p.getIdPaciente() + " | Valor: " + p.getValor() + " | Data: " + p.getData() + " | Metodo: " + p.getMetodo());
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do pagamento que deseja atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    Pagamento pagamentoParaAtualizar = pagamentoDAO.listarPagamentos().stream().filter(p -> p.getId() == idAtualizar).findFirst().orElse(null);
                    if (pagamentoParaAtualizar != null) {
                        System.out.print("Novo valor: ");
                        pagamentoParaAtualizar.setValor(scanner.nextDouble());
                        scanner.nextLine(); // Consumir newline
                        pagamentoDAO.atualizarPagamento(pagamentoParaAtualizar);
                        System.out.print("Nova data: ");
                        pagamentoParaAtualizar.setData(scanner.nextLine());
                        pagamentoDAO.atualizarPagamento(pagamentoParaAtualizar);
                        System.out.print("Nova Forma de Pagamento: ");
                        pagamentoParaAtualizar.setMetodo(scanner.nextLine());
                        pagamentoDAO.atualizarPagamento(pagamentoParaAtualizar);
                        System.out.print("Novo ID do Paciente: ");
                        pagamentoParaAtualizar.setIdPaciente(scanner.nextInt());
                        pagamentoDAO.atualizarPagamento(pagamentoParaAtualizar);
                        System.out.println("Pagamento atualizado com sucesso!");
                    } else {
                        System.out.println("Pagamento não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do pagamento que deseja deletar: ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    pagamentoDAO.deletarPagamento(idDeletar);
                    System.out.println("Pagamento deletado com sucesso!");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Menu de Exames
    private static void menuExame(Scanner scanner, ExameDAO exameDAO) {
        int opcaoExame = 0;
        while (opcaoExame != 5) {
            System.out.println("\n*** Menu de Exames ***");
            System.out.println("1. Inserir Exame");
            System.out.println("2. Listar Exames");
            System.out.println("3. Atualizar Exame");
            System.out.println("4. Deletar Exame");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoExame = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (opcaoExame) {
                case 1:
                    Exame novoExame = new Exame();
                    System.out.print("Tipo do Exame: ");
                    novoExame.setTipo(scanner.nextLine());
                    System.out.print("Nome do Exame: ");
                    novoExame.setNome(scanner.nextLine());
                    System.out.print("Data (AAAA-MM-DD): ");
                    novoExame.setData(scanner.nextLine());
                    System.out.println("Resultado: ");
                    novoExame.setResultado(scanner.nextLine());
                    System.out.println("Valor: ");
                    novoExame.setValor(scanner.nextDouble());
                    System.out.print("ID do Paciente: ");
                    novoExame.setIdPaciente(scanner.nextInt());
                    System.out.println("ID do Medico: ");
                    novoExame.setIdMedico((scanner.nextInt()));
                    scanner.nextLine(); // Consumir newline

                    exameDAO.inserirExame(novoExame);
                    System.out.println("Exame inserido com sucesso!");
                    break;
                case 2:
                    List<Exame> exames = exameDAO.listarExames();
                    for (Exame e : exames) {
                        System.out.println("ID: " + e.getId() + " | Valor: " + e.getValor() + " | Nome: " + e.getNome() + " | Tipo: " + e.getTipo() + " | Data: " + e.getData() + " | Resultado: " + e.getResultado() + " | ID do Médico: " + e.getIdMedico());
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do exame que deseja atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    Exame exameParaAtualizar = exameDAO.listarExames().stream().filter(e -> e.getId() == idAtualizar).findFirst().orElse(null);
                    if (exameParaAtualizar != null) {
                        System.out.print("Novo nome: ");
                        exameParaAtualizar.setNome(scanner.nextLine());
                        exameDAO.atualizarExame(exameParaAtualizar);
                        System.out.println("Novo Tipo: ");
                        exameParaAtualizar.setTipo(scanner.nextLine());
                        exameDAO.atualizarExame(exameParaAtualizar);
                        System.out.println("Nova Data: ");
                        exameParaAtualizar.setData(scanner.nextLine());
                        exameDAO.atualizarExame(exameParaAtualizar);
                        System.out.println("Novo Resultado: ");
                        exameParaAtualizar.setResultado(scanner.nextLine());
                        exameDAO.atualizarExame(exameParaAtualizar);
                        System.out.println("Novo Valor: ");
                        exameParaAtualizar.setValor(scanner.nextDouble());
                        exameDAO.atualizarExame(exameParaAtualizar);
                        System.out.println("Novo ID Paciente: ");
                        exameParaAtualizar.setIdPaciente(scanner.nextInt());
                        exameDAO.atualizarExame(exameParaAtualizar);
                        System.out.println("Novo ID Medico: ");
                        exameParaAtualizar.setIdMedico(scanner.nextInt());
                        exameDAO.atualizarExame(exameParaAtualizar);
                        System.out.println("Exame atualizado com sucesso!");
                    } else {
                        System.out.println("Exame não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do exame que deseja deletar: ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    exameDAO.deletarExame(idDeletar);
                    System.out.println("Exame deletado com sucesso!");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Menu de Prontuários
    private static void menuProntuario(Scanner scanner, ProntuarioDAO prontuarioDAO) {
        int opcaoProntuario = 0;
        while (opcaoProntuario != 5) {
            System.out.println("\n*** Menu de Prontuários ***");
            System.out.println("1. Inserir Prontuário");
            System.out.println("2. Listar Prontuários");
            System.out.println("3. Atualizar Prontuário");
            System.out.println("4. Deletar Prontuário");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoProntuario = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (opcaoProntuario) {
                case 1:
                    Prontuario novoProntuario = new Prontuario();
                    System.out.print("ID do Paciente: ");
                    novoProntuario.setIdPaciente(scanner.nextInt());
                    System.out.print("ID da Consulta: ");
                    novoProntuario.setIdConsulta(scanner.nextInt());
                    scanner.nextLine(); // Consumir newline


                    prontuarioDAO.inserirProntuario(novoProntuario);
                    System.out.println("Prontuário inserido com sucesso!");
                    break;
                case 2:
                    List<Prontuario> prontuarios = prontuarioDAO.listarProntuarios();
                    for (Prontuario pr : prontuarios) {
                        System.out.println("ID: " + pr.getId() + " | ID Paciente: " + pr.getIdPaciente() + " | ID da Consulta: " + pr.getIdConsulta());
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do prontuário que deseja atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    Prontuario prontuarioParaAtualizar = prontuarioDAO.listarProntuarios().stream().filter(p -> p.getId() == idAtualizar).findFirst().orElse(null);
                    if (prontuarioParaAtualizar != null) {
                        System.out.print("Novo ID Paciente: ");
                        prontuarioParaAtualizar.setIdPaciente(scanner.nextInt());
                        prontuarioDAO.atualizarProntuario(prontuarioParaAtualizar);
                        System.out.print("Novo ID Consulta: ");
                        prontuarioParaAtualizar.setIdConsulta(scanner.nextInt());
                        prontuarioDAO.atualizarProntuario(prontuarioParaAtualizar);
                        System.out.println("Prontuário atualizado com sucesso!");
                    } else {
                        System.out.println("Prontuário não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do prontuário que deseja deletar: ");
                    int idDeletar = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    prontuarioDAO.deletarProntuario(idDeletar);
                    System.out.println("Prontuário deletado com sucesso!");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
