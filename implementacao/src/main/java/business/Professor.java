package business;

import java.util.List;
import java.util.LinkedList;

public class Professor extends Usuario {
    private List<Disciplina> disciplinasLecionadas = new LinkedList<Disciplina>();

    public List<Disciplina> getDisciplinasLecionadas() {
        return disciplinasLecionadas;
    }

    public void setDisciplinasLecionadas(List<Disciplina> disciplinasLecionadas) {
        this.disciplinasLecionadas = disciplinasLecionadas;
    }

    Professor(String nome, String senha) {
        super(nome, senha);

    }

    @Override
    public String getDados(){
        return "P;" + getNome() + ";" + getSenha() + ";" + "\n";
    }
}
