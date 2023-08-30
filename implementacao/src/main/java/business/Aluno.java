package business;

import java.util.List;

public class Aluno extends Usuario implements ISalvavel {
    private List<Disciplina> disciplinas;
    private String nome;
    private String senha;

    Aluno(String nome, String senha) {
        super(nome, senha);
    }

    public void addDisciplinas(Disciplina disciplina){
        getDisciplinas().add(disciplina);
        disciplina.addAlunos(this);
    }

    public void removeDisciplinas(Disciplina disciplina){
        getDisciplinas().remove(disciplina);
    }

    public List<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    
    public String visualizarCobranca(SistemaCobranca financeiro){

        return financeiro.visualizaCobranca(this.nome);
    }

    @Override
    public String getDados(){
        return "A;" + this.nome + ";" + this.senha;
    }
}
