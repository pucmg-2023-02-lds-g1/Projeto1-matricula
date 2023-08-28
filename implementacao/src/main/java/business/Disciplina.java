package business;
import java.util.List;

public class Disciplina implements IObrigatorio, IAtivada,ISalvavel{
    private List<Aluno> Alunos;
    private int maxAlunos;
    private String nome;
    private IAtivada ativada;
    private IObrigatorio obrigatoria;


    public String getDados(){
        return "D;" + this.nome;
    }
}
