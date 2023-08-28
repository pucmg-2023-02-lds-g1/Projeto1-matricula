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

        //construir menu principal aqui com as opções


    }
}
