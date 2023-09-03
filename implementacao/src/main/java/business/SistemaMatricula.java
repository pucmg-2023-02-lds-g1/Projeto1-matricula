package business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
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

    public SistemaMatricula(String nome) throws UsuarioInvalidoException {
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

    public void matricularDisciplina(String nomeUsuario, String nomeDisciplina) {
        //System.out.println(nomeUsuario);
        //System.out.println(nomeDisciplina);
        Aluno aluno = (Aluno)filtrarUsuario(nomeUsuario);
        //System.out.println(aluno);
        //System.out.println("xxxxxxxxxxxxxxxx");
        Disciplina disciplina = filtrarDisciplina(nomeDisciplina);
        //System.out.println(disciplina);
        disciplina.addAlunos(aluno);
        notificarSistemaDeCobranca(nomeUsuario);
    }

    public void cancelarMatricula(String nomeUsuario, String nomeDisciplina) {
        Aluno aluno = (Aluno) filtrarUsuario(nomeUsuario);
        disciplinas.get(nomeDisciplina).removeAlunos(aluno);
    }

    public boolean confereDisciplina(String disciplina) throws Exception {
        if (!disciplinas.containsKey(disciplina)) {
            return true;
        } else {
            throw new Exception();
        }
    }

    public boolean confereAlunoMatriculado(String nomeA, String disciplina) throws Exception {
        Usuario aluno = usuarios.get(nomeA);
        if (!disciplinas.get(disciplina).getAlunos().contains(aluno)) {
            return true;
        } else {
            throw new Exception();
        }
    }

    public boolean confereAluno(String aluno) throws Exception {
        if (usuarios.containsKey(aluno)) {
            return true;
        } else {
            throw new Exception();
        }
    }

    public Usuario filtrarUsuario(String nomeDeUsuario) {
        return usuarios.get(nomeDeUsuario);
    }

    public Disciplina filtrarDisciplina(String nomeDisciplina) {
        return disciplinas.get(nomeDisciplina);
    }

    public void notificarSistemaDeCobranca(String nome) {

        long qtdDisc = calcQtdDisciplinas(nome);
        Double preco = qtdDisc * 100.0;
        financeiro.emitirCobranca(nome, "Boleto do aluno(a)" + nome, preco);
    }

    public String visualizarAlunos(String nomeDisciplina) {
        String listaVazia = "Não existem alunos registrados nessa disciplina \n";
        StringBuilder resultado = new StringBuilder();
        Disciplina disciplina = disciplinas.get(nomeDisciplina);
        if (disciplina.getAlunos().size() == 0) {
            return listaVazia;
        }
        for (Aluno atual : disciplina.getAlunos()) {
            resultado.append(atual.toString());
        }
        return resultado.toString();
    }

    //revisar repeticao
    public String visualizarDisciplinasAluno() {
        String hashVazio = "Não existem disciplinas registradas \n";
        StringBuilder resultado = new StringBuilder();
        Disciplina disciplinaComparada;
        if (disciplinas.size() == 0) {
            return hashVazio;
        }
        for (Map.Entry<String, Disciplina> atual : disciplinas.entrySet()) {
            disciplinaComparada = atual.getValue();
            if (disciplinaComparada.getAlunos().contains(usuarioAtual)) {
                resultado.append(disciplinaComparada.toString());
            }
        }
        return resultado.toString();
    }


    //revisar repeticao
    public String visualizarDisciplinasLecionadas() {
        String listaVazia = "Esse professor não leciona disciplinas no momento \n";
        StringBuilder resultado = new StringBuilder();
        List<Disciplina> disciplinas = ((Professor) usuarioAtual).getDisciplinasLecionadas();
        if (disciplinas.size() == 0) {
            return listaVazia;
        }
        for (Disciplina atual : disciplinas) {
            resultado.append(atual.toString());
        }
        return resultado.toString();
    }


    //revisar repeticao
    public String visualizarAlunosDoProfessor() {
        String listaVazia = "Esse professor não leciona disciplinas no momento \n";
        StringBuilder resultado = new StringBuilder();
        Professor Professor = (Professor) usuarioAtual;
        if (Professor.getDisciplinasLecionadas().size() == 0) {
            return listaVazia;
        }
        for (Disciplina atual : Professor.getDisciplinasLecionadas()) {
            resultado.append(atual.getNome() + ":\n");
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
            throw new UsuarioInvalidoException();
        }
    }

    public void excluirUsuario(String nomeUsuario) throws UsuarioInvalidoException {
        if (usuarios.containsKey(nomeUsuario)) {
            this.usuarios.remove(nomeUsuario);
        } else {
            throw new UsuarioInvalidoException();
        }
    }

    private void carregarUsuario() throws UsuarioInvalidoException {
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
            List<Aluno> alunos = new LinkedList<Aluno>();
            Aluno aluno;

            while ((linha = reader.readLine()) != null) {

                StringTokenizer str = new StringTokenizer(linha, ";");
                String nomeDisciplina = str.nextToken();
                int maxAlunos = Integer.parseInt(str.nextToken());
                String nomeCurso = str.nextToken();
                while(str.hasMoreTokens()){
                    aluno = (Aluno) usuarios.get(str.nextToken());
                    alunos.add(aluno);
                }

                Disciplina disciplina = new Disciplina(maxAlunos, nomeDisciplina, nomeCurso);

                disciplinas.put(nomeDisciplina, disciplina);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Disciplina criarDisciplina(String nome, int maxAlunos, String nomeCurso) {
        Disciplina atual = new Disciplina(maxAlunos, nome, nomeCurso);
        this.getDisciplinas().put(nome, atual);
        return atual;
    }

    public String visualizarDisciplinas() {
        String hashVazio = "Não existem disciplinas registradas \n";
        StringBuilder resultado = new StringBuilder();
        if (disciplinas.size() == 0) {
            return hashVazio;
        }
        for (Map.Entry<String, Disciplina> atual : disciplinas.entrySet()) {
            resultado.append(atual.getValue().toString());
        }
        return resultado.toString();
    }

    private void escreveArquivo(HashMap<?, ? extends ISalvavel> dados, String nomeArquivo) {
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
            return financeiro.visualizaCobranca(usuarioAtual.getNome());
        } catch (ClassCastException E) {
            throw new ClassCastException("Você não pode ver as cobranças porque você não é um aluno");
        }

    }

    public String visualizarUsuarios() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            Usuario value = entry.getValue();
            String tipo = value.getTipo();
            if (tipo.equals("A")) {
                tipo = "Aluno";
            } else if (tipo.equals("P")) {
                tipo = "Professor";
            } else if (tipo.equals("S")) {
                tipo = "Secretário";
            }
            sb.append(tipo + " --> " + value.getNome() + "\n");
        }
        return sb.toString();
    }
    

    public void setObrigatoria(Disciplina disciplina) {

        disciplina.setObrigatoria(new Disciplina(20, "a", "nomeCurso"));
    }

    public String checkTipo(){
        return usuarioAtual.getTipo();
    }


    public long calcQtdDisciplinas(String nome){

        Usuario aluno = usuarios.get(nome);
        return disciplinas.values()
        .stream()
        .filter(d -> d.getAlunos().contains(aluno))
        .count();
    }
}
