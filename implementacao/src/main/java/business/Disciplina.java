package business;
import java.util.List;
import java.util.LinkedList;

public class Disciplina implements IObrigatorio, IAtivada,ISalvavel{
    private List<Aluno> alunos = new LinkedList<Aluno>();
    private int maxAlunos;
    private String nome;
    private IAtivada ativada;
    private IObrigatorio obrigatoria=null;
    private Curso curso;
    private String nomeCurso;

    public List<Aluno> getAlunos() {
        return this.alunos;
    }

    public String getNomeCurso(){
        return this.nomeCurso;
    }


    public Disciplina(int maxAlunos, String nome, String nomeCurso) {
        this.maxAlunos = maxAlunos;
        this.nome = nome;
        this.nomeCurso = nomeCurso;
    }

    public Disciplina(int maxAlunos, String nome, String nomeCurso, List<Aluno> Alunos) {
        this.maxAlunos = maxAlunos;
        this.nome = nome;
        this.nomeCurso = nomeCurso;
    }


    public void addAlunos(Aluno aluno){
        alunos.add(aluno);  
    }

    public void removeAlunos(Aluno aluno){
        getAlunos().remove(aluno);
    }


    public Disciplina(String nomeDisciplina) {
        setNome(nomeDisciplina);
    }


    public void setAlunos(List<Aluno> alunos) {
        alunos = alunos;
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

    public String getAlunosString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alunos.size(); i++) {
            sb.append(alunos.get(i));
            sb.append(";");
        }
        return sb.toString();
    }
    
    

    @Override
    public String getDados(){
        return getNome() + ";" + getMaxAlunos() + ";" + getNomeCurso() + ";" + getAlunosString() + "\n";
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