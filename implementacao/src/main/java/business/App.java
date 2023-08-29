package business;

import java.util.Scanner;

public class App {
    public static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            SistemaMatricula sysMat = new SistemaMatricula("Sistema Matrícula");
            //menu(sysMat);
            menuSecretario(sysMat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menu(SistemaMatricula sysMat) throws Exception {

        int opcao;
        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Fazer login");
            System.out.println("0 - Salvar dados");
            opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    fazerLogin(sysMat);
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
            System.out.println("1 - Cadastrar Usuario");
             System.out.println("0 - Salvar dados");
            opcao = entrada.nextInt();
            switch (opcao) {
                case 1:
                    cadastro(sysMat);
                    break;
                case 0:
                    sysMat.salvarDados();
                    break;
            }
        } while (opcao != 0);

    }

    private static void cadastro(SistemaMatricula sysMat) {
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
                System.out.println("Aluno cadastrado com sucesso");
            } else if (opcao == 2) {
                Professor professor = new Professor(nome, senha);
                sysMat.cadastro(professor);
                System.out.println("Professor cadastrado com sucesso");
            } else {
                System.out.println("Opção inválida!");
            }
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void fazerLogin(SistemaMatricula sysMat) {

        System.out.println("Opção 1 selecionada.");
        entrada.nextLine();

        System.out.println("Insira o nome de usuario:");
        String nomeUsuario = entrada.nextLine();

        System.out.println("Insira a senha:");
        String senha = entrada.nextLine();

        try {
            sysMat.validarLogin(nomeUsuario, senha);
        } catch (Exception e) { // Criar exceção
            System.out.println(e.getMessage());
        }

    }
}
