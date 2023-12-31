package business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SistemaMatricula {

    private String nome;
    private Usuario usuarioAtual;
    private static final String arqUsuario = "implementacao\\src\\arquivos\\arqUsuarios.txt";
    private static final String arqDisciplina = "implementacao\\src\\arquivos\\arqDisciplina.txt";
    private static final String arqCobranca = "implementacao\\src\\arquivos\\arqCobranca.txt";
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
        carregarDisciplina();
        carregarCobranca();
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

    public void matricularDisciplina(String nomeUsuario, String nomeDisciplina) throws DisciplinaCheiaException, UsuarioInvalidoException, DisciplinaInvalidaException {
        
        Aluno aluno = (Aluno)filtrarUsuario(nomeUsuario);
        Disciplina disciplina = filtrarDisciplina(nomeDisciplina);
        disciplina.addAlunos(aluno);
        notificarSistemaDeCobranca(nomeUsuario);
    }

    public void cancelarMatricula(String nomeUsuario, String nomeDisciplina) throws UsuarioInvalidoException {
        Aluno aluno = (Aluno) filtrarUsuario(nomeUsuario);
        disciplinas.get(nomeDisciplina).removeAlunos(aluno);
    }

    public boolean confereDisciplina(String disciplina) throws Exception {
        if (!disciplinas.containsKey(disciplina)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean confereAlunoMatriculado(String nomeA, String disciplina) throws Exception {
        Usuario aluno = usuarios.get(nomeA);
        if (!disciplinas.get(disciplina).getAlunos().contains(aluno)) {

            return true;
        } else {
            return false;
        }
    }

    public boolean confereAluno(String aluno) throws Exception {
        if (usuarios.containsKey(aluno)) {

            return true;
        } else {
            return false;
        }
    }

    public Usuario filtrarUsuario(String nomeDeUsuario) throws UsuarioInvalidoException {
       if(usuarios.containsKey(nomeDeUsuario)){
            return usuarios.get(nomeDeUsuario);
        }else{
            throw new UsuarioInvalidoException();
        }
    }

    public Disciplina filtrarDisciplina(String nomeDisciplina) throws DisciplinaInvalidaException {
        if(disciplinas.containsKey(nomeDisciplina)){
            return disciplinas.get(nomeDisciplina);
        }else{
            throw new DisciplinaInvalidaException();
        }
    }

    public void notificarSistemaDeCobranca(String nome) {

        long qtdDisc = calcQtdDisciplinas(nome);
        Double preco = qtdDisc * 100.0;
        financeiro.emitirCobranca(nome, nome, preco);
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

    public String visualizarDisciplinasGeral() {
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
            Disciplina disciplina;

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

                while(str.hasMoreTokens()){   
                    String disciplinaString = str.nextToken(); 
                    disciplina = disciplinas.get(disciplinaString); 
                    ((Professor) usuario).addDisciplina(disciplina);
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
                    String nomeAluno = str.nextToken(); 
                    aluno = (Aluno) usuarios.get(nomeAluno);  
                    alunos.add(aluno);
                }
                
                Disciplina disciplina = new Disciplina(maxAlunos, nomeDisciplina, nomeCurso, alunos);
                disciplinas.put(nomeDisciplina, disciplina);
                

                alunos = new LinkedList<Aluno>();                
            }
            
            
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarCobranca() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(arqCobranca));
            String linha;

            while ((linha = reader.readLine()) != null) {

                StringTokenizer str = new StringTokenizer(linha, ";");
                String desc = str.nextToken();
                double preco = Double.parseDouble(str.nextToken());

                Cobranca cobranca = new Cobranca(desc, preco);
                financeiro.addCobrancas(desc, cobranca);;
            }
            
            
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Disciplina criarDisciplina(String nome, String nomeCurso) {
        Disciplina atual = new Disciplina(60, nome, nomeCurso);
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

    private void escreveArqCobranca() {
        escreveArquivo(financeiro.getCobrancas(), arqCobranca);
    }


    public void salvarDados() {
        escreveArqUsuario();
        escreveArqDisciplina();
        escreveArqCobranca();
    }

    public String visualizarCobranca() throws CobrancaInvalidaException, ClassCastException {

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
    
    public void matricularProfessor(String nomeProfessor, String nomeDisciplina) throws DisciplinaInvalidaException, UsuarioInvalidoException{
        Professor professor = (Professor)filtrarUsuario(nomeProfessor);
        Disciplina disciplina = filtrarDisciplina(nomeDisciplina);
        professor.addDisciplina(disciplina);
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

    public String gerarCurriculo() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Currículo atual:\n");
        int qtdAlunos, qtdProfessores, disciplinasAtivas, disciplinasCheias;
        double mediaAlunos;
        //Quantidade de disciplinas
        disciplinasAtivas = disciplinas.values().stream().filter(d -> d.getAtivada()!=null).collect(Collectors.toList()).size();
        resultado.append("Quantidade de disciplinas ativas: "+disciplinasAtivas+"\n");
        disciplinasCheias = disciplinas.values().stream().filter(d -> d.getAtivada()==null).collect(Collectors.toList()).size();
        resultado.append("Quantidade de disciplinas cheias: "+disciplinasCheias+"\n");
        //Disciplina com mais alunos
        Disciplina disciplina = Collections.max(disciplinas.values(), Comparator.comparing(d -> d.getAlunos().size()));
        resultado.append("Disciplina com mais alunos: "+disciplina.getNome()+"\n");
        //Quantidade de professores
        qtdProfessores = usuarios.values().stream().filter(u -> u.getTipo()=="P").collect(Collectors.toList()).size();
        resultado.append("Quantidade de professores cadastrados: "+qtdProfessores+"\n");
        //Média de alunos por professor
        mediaAlunos = (disciplinas.values().stream().mapToInt(d -> d.getAlunos().size()).sum()) / qtdProfessores;
        resultado.append("Média de alunos por professor: "+mediaAlunos+"\n");
        //Quantidade de alunos matriculados
        qtdAlunos = usuarios.values().stream().filter(u -> u.getTipo()=="A").collect(Collectors.toList()).size();
        resultado.append("Quantidade de alunos cadastrados: "+qtdAlunos+"\n\n");
        
        return resultado.toString();
    }
}
