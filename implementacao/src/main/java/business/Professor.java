package business;

import java.util.List;
import java.util.LinkedList;

public class Professor extends Usuario {
    private List<Disciplina> disciplinasLecionadas = new LinkedList<Disciplina>();

    public List<Disciplina> getDisciplinasLecionadas() {
        return disciplinasLecionadas;
    }

    public void addDisciplina(Disciplina disciplina) {
        this.disciplinasLecionadas.add(disciplina);
    }

    public void removeDisciplina(Disciplina disciplina) {
        this.disciplinasLecionadas.remove(disciplina);
    }

    Professor(String nome, String senha) throws UsuarioInvalidoException {
        super(nome, senha);
    }

    public String getDisciplinasString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < disciplinasLecionadas.size(); i++) {
            sb.append(disciplinasLecionadas.get(i).getNome());
            sb.append(";");
        }
        return sb.toString();
    }

    @Override
    public String getDados() {
        return "P;" + getNome() + ";" + getSenha() + ";" + getDisciplinasString() + ";" + "\n";
    }

    @Override
    public String getTipo() {
        return "P";
    }

}
