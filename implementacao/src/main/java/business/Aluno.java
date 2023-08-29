package business;

import java.util.List;

public class Aluno extends Usuario implements ISalvavel {
    private List disciplinas;
    private String nome;
    private String senha;

    Aluno(String nome, String senha) {
        super(nome, senha);
        
    }

    public void addDisciplinas(){

    }

    public void removeDisciplinas(){
        
    }

    public List getDisciplinas() {
        return disciplinas;
    }

    
    public String visualizarCobranca(SistemaCobranca financeiro){

        return financeiro.visualizaCobranca(this.nome);
    }

    @Override
    public String getDados(){
        return "A;" + this.nome + ";" + this.senha;
    }
}
