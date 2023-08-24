package business;
import java.util.List;

public class Curso {
    private String nome;
    private int creditos;
    private List<Disciplina> diciplinas;


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCreditos() {
        return creditos;
    }
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    public List<Disciplina> getDiciplinas() {
        return diciplinas;
    }
    public void setDiciplinas(List<Disciplina> diciplinas) {
        this.diciplinas = diciplinas;
    }

    public Curso(String nome, int creditos, List<Disciplina> diciplinas) {
        this.nome = nome;
        this.creditos = creditos;
        this.diciplinas = diciplinas;
    }
    
   
}
