package business;

import java.util.List;

public class Aluno extends Usuario {
    private List disciplinas;
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
    
}
