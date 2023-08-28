package business;
import java.util.List;

public class Disciplina implements IObrigatorio, IAtivada,ISalvavel{
    private List<Aluno> Alunos;
    private int maxAlunos;
    private String nome;
    public Disciplina(int maxAlunos, String nome, IObrigatorio obrigatoria) {
        this.maxAlunos = maxAlunos;
        this.nome = nome;
        this.obrigatoria = obrigatoria;
    }


    private IAtivada ativada;
    
    public List<Aluno> getAlunos() {
        return Alunos;
    }


    public void setAlunos(List<Aluno> alunos) {
        Alunos = alunos;
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


    public IAtivada getAtivada() {
        return ativada;
    }


    public void setAtivada(IAtivada ativada) {
        this.ativada = ativada;
    }


    public IObrigatorio getObrigatoria() {
        return obrigatoria;
    }


    public void setObrigatoria(IObrigatorio obrigatoria) {
        this.obrigatoria = obrigatoria;
    }


    private IObrigatorio obrigatoria;


    public String getDados(){
        return "D;" + this.nome;
    }
}
