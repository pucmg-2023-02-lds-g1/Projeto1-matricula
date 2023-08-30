package business;

import java.util.List;

public class Aluno extends Usuario {
    private List<Disciplina> disciplinas;

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

        return financeiro.visualizaCobranca(getNome());
    }

    @Override
    public String getDados(){
        return "A;" + getNome() + ";" + getSenha();
    }
}
