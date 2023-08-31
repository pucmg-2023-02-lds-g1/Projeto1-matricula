package business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SistemaMatricula {

    private String nome;
    private Usuario usuarioAtual;
    private static final String arqUsuario = "implementacao\\src\\arquivos\\arqUsuarios.txt";
    private static final String arqDisciplina = "implementacao\\src\\arquivos\\arqDisciplina.txt";
    private SistemaCobranca financeiro = new SistemaCobranca();
    private HashMap<String, Usuario> usuarios = new HashMap<>();
    private HashMap<String, Disciplina> disciplinas = new HashMap<>();

    public HashMap<String, Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(HashMap<String, Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public SistemaMatricula(String nome) {
        carregarDisciplina();
        carregarUsuario();
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 1) {
            this.nome = nome;
        }
    }

    public void matricularDisciplina(String nome, String nomeDisciplina) {
            

        if(!confereDisciplina(((Aluno) usuarios.get(nome)), (disciplinas.get(nomeDisciplina)))){
        try {
            System.out.println("passei");
            disciplinas.get(nomeDisciplina).addAlunos((Aluno) usuarios.get(nome));
        } catch (ClassCastException E) {
            throw new ClassCastException("Você não pode adicionar");
        }
        } else {
            System.out.println("O aluno já está matriculado nessa matéria");
        }

        System.out.println("nn passei");

    }

    public void cancelarMatricula(String nome, String nomeDisciplina) {

        if(!confereDisciplina(((Aluno) usuarios.get(nome)), (disciplinas.get(nomeDisciplina)))){        
        try {
            disciplinas.get(nomeDisciplina).removeAlunos((Aluno) usuarios.get(nome));
        } catch (ClassCastException E) {
            throw new ClassCastException("Você não pode cancelar");
        }
        } else {
            System.out.println("O aluno não está matriculado nessa matéria");
        }
    }

    public boolean confereDisciplina(Aluno aluno, Disciplina disciplina) {
        return disciplina.getAlunos().contains(aluno);
    }

    public void notificarSistemaDeCobranca() {
    
        financeiro.emitirCobranca(usuarioAtual.getNome(), arqDisciplina, null);
    }

    public String visualizarAlunos(String nomeDisciplina) {
        String listaVazia="Não existem alunos registrados nessa disciplina \n";
        StringBuilder resultado = new StringBuilder();
        Disciplina disciplina = disciplinas.get(nomeDisciplina);
        if(disciplina.getAlunos().size()==0){
            return listaVazia;
        }
        for (Aluno atual : disciplina.getAlunos()) {
            resultado.append(atual.toString());
        }
        return resultado.toString();
    }

    public String visualizarDisciplinas(String nomeAluno) {
        String hashVazio="Não existem disciplinas registradas \n";
        StringBuilder resultado = new StringBuilder();
        HashMap<String, Disciplina> disciplinas = this.getDisciplinas();
        Usuario aluno = usuarios.get(nomeAluno);
        Disciplina disciplinaComparada;
        if(disciplinas.size()==0){
            return hashVazio;
        }
        for (Map.Entry<String, Disciplina> atual : disciplinas.entrySet()) {
            disciplinaComparada = atual.getValue();
            if(disciplinaComparada.getAlunos().contains(aluno)){
                resultado.append(disciplinaComparada.toString());
            }
        }
        return resultado.toString();
    }

    public String visualizarDisciplinasLecionadas(String nomeProfessor) {
        String listaVazia="Esse professor não leciona disciplinas no momento \n";
        StringBuilder resultado = new StringBuilder();
        Usuario professor = usuarios.get(nomeProfessor);
        List<Disciplina> disciplinas = ((Professor)professor).getDisciplinasLecionadas();
        if(disciplinas.size()==0){
            return listaVazia;
        }
        for (Disciplina atual : disciplinas) {
            resultado.append(atual.toString());
        }
        return resultado.toString();
    }

    public String visualizarAlunosDoProfessor(String nomeProfessor) {
        String listaVazia="Esse professor não leciona disciplinas no momento \n";
        StringBuilder resultado = new StringBuilder();
        Professor Professor = (Professor)usuarios.get(nomeProfessor);
        if(Professor.getDisciplinasLecionadas().size()==0){
            return listaVazia;
        }
        for (Disciplina atual : Professor.getDisciplinasLecionadas()) {
            resultado.append(atual.getNome()+":\n");
            resultado.append(visualizarAlunos(atual.getNome()));
        }
        return resultado.toString();
    }

    public boolean validarLogin(String nome, String senha) throws Exception {
        usuarioAtual = usuarios.get(nome);
        if (usuarioAtual == null) {
            throw new Exception("Login incorreto!");// Criar exceção específica
        }
        if (!senha.equals(usuarioAtual.getSenha())) {
            this.usuarioAtual = null;
            throw new Exception("Senha incorreta!");// Criar exceção específica
        }
        return true;
    }

    public void cadastro(Usuario u) throws UsuarioInvalidoException {
        if (!usuarios.containsKey(u.getNome())) {
            this.usuarios.put(u.getNome(), u);
        } else {
            throw new UsuarioInvalidoException("Esse usuario já existe!");
        }
    }

     public void excluirUsuario(String nomeUsuario) throws UsuarioInvalidoException {
        if (usuarios.containsKey(nomeUsuario)) {
            this.usuarios.remove(nomeUsuario);
        } else {
            throw new UsuarioInvalidoException("Esse usuario não existe!");
        }
    }

    private void carregarUsuario() {
        try {

            Usuario usuario;

            BufferedReader reader = new BufferedReader(new FileReader(arqUsuario));
            String linha;

            while ((linha = reader.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(linha, ";");
                String tipo = str.nextToken();
                String nome = str.nextToken();
                String senha = str.nextToken();

                if (tipo.equals("A")) {
                    usuario = new Aluno(nome, senha);
                } else if (tipo.equals("P")) {
                    usuario = new Professor(nome, senha);
                } else {
                    usuario = new Secretario(nome, senha);
                }

                usuarios.put(nome, usuario);
            }
            reader.close();
            System.out.println(usuarios);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDisciplina() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(arqDisciplina));
            String linha;

            while ((linha = reader.readLine()) != null) {
                StringTokenizer str = new StringTokenizer(linha, ";");
                String nomeDisciplina = str.nextToken();
                String idDisciplina = str.nextToken();
                String nomeCurso = str.nextToken();

                Disciplina disciplina = new Disciplina(nomeDisciplina, idDisciplina);

                disciplinas.put(nomeCurso, disciplina);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Disciplina criarDisciplina(String nome, int maxAlunos ) {
        Disciplina atual = new Disciplina(maxAlunos, nome );
        this.getDisciplinas().put(nome, atual);
        return atual;
    }

    public String visualizarDisciplinas() {
        String hashVazio="Não existem disciplinas registradas \n";
        StringBuilder resultado = new StringBuilder();
        HashMap<String, Disciplina> disciplinas = this.getDisciplinas();
        if(disciplinas.size()==0){
            return hashVazio;
        }
        for (Map.Entry<String, Disciplina> atual : disciplinas.entrySet()) {
            resultado.append(atual.getValue().toString());
        }
        return resultado.toString();
    }

    private void escreveArquivo(HashMap<?, ? extends ISalvavel> dados , String nomeArquivo) {
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo, false);
            dados.forEach((key, value) -> {
                try {
                    arquivo.write((value).getDados());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void escreveArqUsuario() {
        escreveArquivo(usuarios, arqUsuario);
    }

    private void escreveArqDisciplina() {
        escreveArquivo(disciplinas, arqDisciplina);
    }

    public void salvarDados() {
        escreveArqUsuario();
        escreveArqDisciplina();
    }

    public String visualizarCobranca() {

        try {
            return ((Aluno) usuarioAtual).visualizarCobranca(financeiro);
        } catch (ClassCastException E) {
            throw new ClassCastException("Você não pode ver as cobranças porque você não é um aluno");
        }

    }

    public void setObrigatoria(Disciplina disciplina){
        
        disciplina.setObrigatoria(new Disciplina(20,"a"));
    }

}
