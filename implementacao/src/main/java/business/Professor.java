package business;

import java.util.List;

public class Professor extends Usuario {
    private List<Disciplina> disciplinasLecionadas;

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
        return "P;" + getNome() + ";" + getSenha() + "\n";
    }
}
