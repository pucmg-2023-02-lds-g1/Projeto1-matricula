package business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SistemaMatricula {
    
    private String nome;
    private static final String arqUsuario = "implementacao\\src\\arquivos\\arqUsuarios.txt";
    private HashMap<String, Usuario> usuarios = new HashMap<>();
    private HashMap<String, Disciplina> disciplinas = new HashMap<>();


    public SistemaMatricula(String nome){

        carregarUsuario();

        if(nome.length() > 1){
            this.nome = nome;
        }
    }


    public HashMap<String, Disciplina> getDisciplinas() {
        return disciplinas;
    }


    public void setDisciplinas(HashMap<String, Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }


    public Disciplina matricularDisciplina(String nome, String nomeDisciplina){

        return null;
    }

    public Disciplina cancelarMatricula(String nome, String nomeDisciplina){

        return null;
    }

    public void confereDisciplina(){
        
    }
    
    public void notificarSistemaDeCobranca(){

    }
     
    public String visualizarAlunos(String nomeDisciplina){

        return "";
    }

    public void validarLogin(String nome, String senha){

    }

    public void cadastro(){

    }

    private void carregarUsuario() {
        try {

            Usuario usuario;

            BufferedReader reader = new BufferedReader(new FileReader(arqUsuario));
            String linha;
            reader.readLine();

            while ((linha = reader.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(linha, ";");
                String tipo = str.nextToken();
                String nome = str.nextToken();
                String senha = str.nextToken();

                if(tipo.equals("A")) {
                    usuario = new Aluno(nome, senha);
                } else if(tipo.equals("P")) {
                    usuario = new Professor(nome, senha);
                } else {
                    usuario = new Secretario(nome, senha);
                }

                usuarios.put(nome, usuario);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void criarDisciplina(String nome ,int maxAlunos,IObrigatorio obrigatorio){
        Disciplina atual= new Disciplina(maxAlunos, nome, obrigatorio);
        this.getDisciplinas().put(nome, atual);
    }

    public String visualizarDiciplinas(){
        StringBuilder resultado = new StringBuilder();
        HashMap<String, Disciplina> disciplinas = this.getDisciplinas();

        for(Map.Entry<String, Disciplina> atual : disciplinas.entrySet() ){
            resultado.append(atual.getKey()).append(": ").append("Maximo alunos:").append(atual.getValue().getMaxAlunos()).append(", ")
            .append("É obrigatoria: ").append(atual.getValue().getObrigatoria()).append("\n");
        }
        return resultado.toString();
    }
}
