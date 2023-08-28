package business;

import java.util.List;

public class Aluno extends Usuario implements ISalvavel {
    private List disciplinas;
    private String nome;
    private String senha;

    Aluno(String nome, String senha) {
        super(nome, senha);
        
    }

    void addDisciplinas(){

    }

    void removeDisciplinas(){
        
    }

    public List getDisciplinas() {
        return disciplinas;
    }

    
    void visualizarCobranca(){

    }

    public String getDados(){
        return "A;" + this.nome + ";" + this.senha;
    }
}
