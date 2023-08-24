package business;

public class Disciplna implements IObrigatorio, IAtivada {
    private List<Aluno> Alunos;
    private int maxAlunos;
    private String nome;
    private IAtivada ativada;
    private IObrigatorio obrigatoria;
}
