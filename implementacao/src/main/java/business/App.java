package business;
import java.util.Scanner;

public class App {
    public static Scanner entrada = new Scanner(System.in);
    public static void main( String[] args ) {
        
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
            opcao = entrada.nextInt();
            switch(opcao){
                case 1: 
                fazerLogin(sysMat);
                break;
            }
        } while (opcao != 0);
        //construir menu principal aqui com as opções


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
        } catch (Exception e) { //Criar exceção
            System.out.println(e.getMessage());
        }

    }
}
