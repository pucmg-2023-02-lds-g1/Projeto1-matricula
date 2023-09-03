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

    public Disciplina(int maxAlunos, String nome, String nomeCurso, List<Aluno> alunos) {
        this.maxAlunos = maxAlunos;
        this.nome = nome;
        this.nomeCurso = nomeCurso;
        this.alunos = alunos;
    }


    public void addAlunos(Aluno aluno) throws DisciplinaCheiaException {
        

        if(alunos.size()<=getMaxAlunos()){
            alunos.add(aluno);
        } else {
            throw new DisciplinaCheiaException();
        } 

        if(alunos.size()>3) {
            setAtivada(ativada);
        }
    }

    public void removeAlunos(Aluno aluno){
        getAlunos().remove(aluno);
    }


    public Disciplina(String nomeDisciplina) throws DisciplinaInvalidaException {
        setNome(nomeDisciplina);
    }


    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
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


    public void setMaxAlunos(int maxAlunos) throws DisciplinaInvalidaException {
        if(maxAlunos<=60) {
            this.maxAlunos = maxAlunos;
        } else {
            throw new DisciplinaInvalidaException();
        } 
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) throws DisciplinaInvalidaException {
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

    public String getAlunosString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alunos.size(); i++) {
            sb.append(alunos.get(i).getNome());
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