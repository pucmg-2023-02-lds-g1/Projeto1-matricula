package business;
import java.util.List;

public class Disciplina implements IObrigatorio, IAtivada,ISalvavel{
    private List<Aluno> Alunos;
    private int maxAlunos;
    private String nome;
    private IAtivada ativada;
    private IObrigatorio obrigatoria;


    public List<Aluno> getAlunos() {
        return Alunos;
    }


    public Disciplina(int maxAlunos, String nome, IObrigatorio obrigatoria) {
        this.maxAlunos = maxAlunos;
        this.nome = nome;
        this.obrigatoria = obrigatoria;
    }


    public void setAlunos(List<Aluno> alunos) {
        Alunos = alunos;
    }


    public IAtivada getAtivada() {
        return ativada;
    }


    public void setAtivada(IAtivada ativada) {
        this.ativada = ativada;
    }


    public int getMaxAlunos() {
        return maxAlunos;
    }


    public void setMaxAlunos(int maxAlunos) {
        this.maxAlunos = maxAlunos;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public IObrigatorio getObrigatoria() {
        return obrigatoria;
    }


    public void setObrigatoria(IObrigatorio obrigatoria) {
        this.obrigatoria = obrigatoria;
    }


    public String getDados(){
        return "D;" + this.nome;
    }
}
