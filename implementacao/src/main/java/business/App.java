package business;

import java.util.Scanner;

public class App {
    public static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {

        try {

            SistemaMatricula sysMat = new SistemaMatricula("Sistema Matrícula");
            menu(sysMat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menu(SistemaMatricula sysMat) throws Exception {

        int opcao;
        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Cadastrar um secretário");
            System.out.println("0 - Salvar dados");
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao) {
                case 1:
                    fazerLogin(sysMat);
                    break;
                case 2:
                    cadastroSecretario(sysMat);
                    break;
                case 0:
                    sysMat.salvarDados();
                    break;
            }
        } while (opcao != 0);
        // construir menu principal aqui com as opções

    }

    public static void menuSecretario(SistemaMatricula sysMat) {
        int opcao;
        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar usuario");
            System.out.println("2 - Excluir usuario");
            System.out.println("3 - Cadastrar nova disciplina");
            System.out.println("4 - Vizualizar disciplinas");
            System.out.println("5 - Vizualizar usuarios");
            System.out.println("6 - Matricular aluno");
            System.out.println("7 - Desmatricular aluno");
            System.out.println("8 - Matricular professor para ensinar disciplina");
            System.out.println("9 - Mudar de conta");
            System.out.println("0 - Salvar dados");
            opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao) {
                case 1:
                    cadastroUsuario(sysMat);
                    break;
                case 2:
                    excluirUsuario(sysMat);
                    break;
                case 3:
                    cadastroDisciplina(sysMat);
                    break;
                case 4:
                    System.out.println(sysMat.visualizarDisciplinasGeral());
                    break;
                case 5:
                    System.out.println(sysMat.visualizarUsuarios());
                    break;
                case 6:
                    matricularAluno(sysMat);
                    break;
                case 7:
                    desmatricularAluno(sysMat);
                    break;
                case 8:
                    matricularProfessor(sysMat);
                    break;
                case 9:
                    try {
                        menu(sysMat);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    sysMat.salvarDados();
                    break;
            }
        } while (opcao != 0);

    }

    private static void matricularProfessor(SistemaMatricula sysMat) {
        System.out.println("Digite o nome da disciplina a ser lessionada ");
        String nomeDisciplina = entrada.nextLine();
        System.out.println("Digite o nome do professor a lessiona-la ");
        String nomeProfessor = entrada.nextLine();
        try {
            sysMat.matricularProfessor(nomeProfessor, nomeDisciplina);
            System.out.println("O professor " + nomeProfessor + " agora esta lessionando a disciplina " + nomeDisciplina);
        } catch (DisciplinaInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
        
    }

    private static void menuAluno(SistemaMatricula sysMat) {

        try {

            int opcao;
            do {
                System.out.println("Selecione uma opção:");
                System.out.println("1 - Visualizar disciplinas");
                System.out.println("2 - Visualizar cobranças");
                System.out.println("0 - Salvar dados");
                opcao = entrada.nextInt();
                entrada.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.println(sysMat.visualizarDisciplinasAluno());
                        break;
                    case 2:
                        System.out.println(sysMat.visualizarCobranca());
                        break;
                    case 0:
                        sysMat.salvarDados();
                        break;
                }
            } while (opcao != 0);

        } catch (CobrancaInvalidaException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void menuProfessor(SistemaMatricula sysMat) {

        try {

            int opcao;
            do {
                System.out.println("Selecione uma opção:");
                System.out.println("1 - Visualizar disciplinas lecionadas");
                System.out.println("2 - Visualizar alunos");
                System.out.println("0 - Salvar dados");
                opcao = entrada.nextInt();
                entrada.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.println(sysMat.visualizarDisciplinasLecionadas());
                        break;
                    case 2:
                        System.out.println(sysMat.visualizarAlunosDoProfessor());
                        break;
                    case 0:
                        sysMat.salvarDados();
                        break;
                }
            } while (opcao != 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void excluirUsuario(SistemaMatricula sysMat) {
        System.out.println("Digite o nome do usuario que você quer excluir: ");
        String nome = entrada.nextLine();
        try {
            sysMat.excluirUsuario(nome);
            System.out.println("Usuario (" + nome + ") excluido com sucesso.");
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cadastroDisciplina(SistemaMatricula sysMat) {
        System.out.println("Digite o nome da disciplina: ");
        String nomeDisciplina = entrada.nextLine();

        System.out.println("Digite o maximo de alunos desse disciplina: ");
        int maxAlunos = entrada.nextInt();

        entrada.nextLine();
        System.out.println("Digite o nome do curso: ");
        String nomeCurso = entrada.nextLine();

        Disciplina atual = sysMat.criarDisciplina(nomeDisciplina, maxAlunos, nomeCurso);
        System.out.println("Essa disicplina é obrigatoria?(S/N) ");
        String op = entrada.nextLine();

        if (op.equals("S")) {
            sysMat.setObrigatoria(atual);
        }
        System.out.println("Disciplina criada com sucesso!");
    }

    private static void cadastroUsuario(SistemaMatricula sysMat) {

        System.out.println("Digite 1 para cadastrar um aluno ou 2 para cadastrar um professor: ");
        int opcao = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite o nome: ");
        String nome = entrada.nextLine();
        System.out.println("Digite a senha: ");
        String senha = entrada.nextLine();

        try {
            if (opcao == 1) {
                Aluno aluno = new Aluno(nome, senha);
                sysMat.cadastro(aluno);
                System.out.println("Aluno cadastrado com sucesso: " + aluno.getNome());
            } else if (opcao == 2) {
                Professor professor = new Professor(nome, senha);
                sysMat.cadastro(professor);
                System.out.println("Professor cadastrado com sucesso: " + professor.getNome());
            } else {
                System.out.println("Opção inválida!");
            }
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cadastroSecretario(SistemaMatricula sysMat) {

        try {
            System.out.println("Digite o nome: ");
            String nome = entrada.nextLine();
            System.out.println("Digite a senha: ");
            String senha = entrada.nextLine();

            Secretario secretario = new Secretario(nome, senha);
            sysMat.cadastro(secretario);
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void fazerLogin(SistemaMatricula sysMat) {

        try {
            System.out.println("Opção 1 selecionada.");

            System.out.println("Insira o nome de usuario:");
            String nomeUsuario = entrada.nextLine();

            System.out.println("Insira a senha:");
            String senha = entrada.nextLine();

            sysMat.validarLogin(nomeUsuario, senha);

            String tipoUsuario = sysMat.checkTipo();

            if (tipoUsuario.equals("S")) {
                menuSecretario(sysMat);
            } else if (tipoUsuario.equals("A")) {
                menuAluno(sysMat);
            } else if (tipoUsuario.equals("P")) {
                menuProfessor(sysMat);
            }

        } catch (Exception e) { // Criar exceção
            System.out.println(e.getMessage());
        }

    }

    private static void matricularAluno(SistemaMatricula sysMat) {
        // System.out.println(sysMat.filtrarDisciplina("Curso legal").getNome());
        // System.out.println(sysMat.filtrarUsuario("vagner").getSenha());
        // boolean condicao = true;//, condicao2 = true;
        String nomeAluno;
        String nomeDisciplina;

        // do {
        System.out.println("Digite o nome do aluno: ");
        nomeAluno = entrada.nextLine();
        // try {
        // condicao = sysMat.confereAluno(nomeAluno);
        // } catch (Exception E) {
        // }
        // } while (!condicao);

        // System.out.println("Digite o numero de materias em que o aluno será
        // matriculado: ");
        // int numMat = entrada.nextInt();

        // for (int i = 0; i != numMat; i++) {
        // do {

        System.out.println("Digite o nome da disciplina: ");
        nomeDisciplina = entrada.nextLine();
        // try {
        // condicao = sysMat.confereDisciplina(nomeDisciplina);
        // condicao2 = sysMat.confereAlunoMatriculado(nomeAluno, nomeDisciplina);
        // } catch (Exception E) {
        // }

        // } while (!condicao);
        try {
            sysMat.matricularDisciplina(nomeAluno, nomeDisciplina);
        } catch (DisciplinaInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
        // }

    }

    private static void desmatricularAluno(SistemaMatricula sysMat) {
        boolean condicao = true;// , condicao2 = true;
        String nomeAluno;
        String nomeDisciplina;

        do {
            System.out.println("Digite o nome do aluno: ");
            nomeAluno = entrada.nextLine();
            try {
                condicao = sysMat.confereAluno(nomeAluno);
            } catch (Exception E) {
            }
        } while (!condicao);

        do {

            System.out.println("Digite o nome da disciplina: ");
            nomeDisciplina = entrada.nextLine();
            try {
                condicao = sysMat.confereDisciplina(nomeDisciplina);
                // condicao2 = sysMat.confereAlunoMatriculado(nomeAluno, nomeDisciplina);
            } catch (Exception E) {
            }

        } while (!condicao);

        try {
            sysMat.cancelarMatricula(nomeAluno, nomeDisciplina);
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

}
