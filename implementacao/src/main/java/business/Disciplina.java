package business;
import java.util.List;
import java.util.LinkedList;

public class Disciplina implements IObrigatorio, IAtivada,ISalvavel{
    private List<Aluno> Alunos = new LinkedList<Aluno>();
    private int maxAlunos;
    private String nome;
    private IAtivada ativada;
    private IObrigatorio obrigatoria=null;
    private Curso curso;
    private String nomeCurso;

    public List<Aluno> getAlunos() {
        return Alunos;
    }

    public String getNomeCurso(){
        return this.nomeCurso;
    }


    public Disciplina(int maxAlunos, String nome, String nomeCurso) {
        this.maxAlunos = maxAlunos;
        this.nome = nome;
        this.nomeCurso = nomeCurso;
    }


    public void addAlunos(Aluno aluno){
        Alunos.add(aluno);  
    }

    public void removeAlunos(Aluno aluno){
        getAlunos().remove(aluno);
    }


    public Disciplina(String nomeDisciplina) throws DisciplinaInvalidaException {
        setNome(nomeDisciplina);
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


    public void setMaxAlunos(int maxAlunos) throws DisciplinaInvalidaException { //dddddddddddddddddddddddddddd
        if(nome!=null) {
            this.maxAlunos = maxAlunos;
        } else {
            throw new DisciplinaInvalidaException();
        } 
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) throws DisciplinaInvalidaException { //dddddddddddddddddddddddddddd
        if(nome!=null) {
            this.nome = nome;
        } else {
            throw new DisciplinaInvalidaException();
        }  
    }


    public IObrigatorio getObrigatoria() {
        return obrigatoria;
    }


    public void setObrigatoria(IObrigatorio obrigatoria) {
        this.obrigatoria = obrigatoria;
    }

    @Override
    public String getDados(){
        return getNome() + ";" + getMaxAlunos() + ";" + getNomeCurso() + "\n";
    }

    @Override
    public String toString(){
        String obg = "Não";
        if(obrigatoria != null){
            obg = "Sim";
        }
        return ("Nome: "+nome+"\n"+"Maximo alunos: "+maxAlunos+"\n"+"É obrigatoria: "+obg+"\n\n");
    }
}