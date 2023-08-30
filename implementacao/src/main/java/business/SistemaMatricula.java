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
    private static final String arqUsuario = "pucmg-2023-02-lds-g1\\implementacao\\src\\arquivos\\arqUsuarios.txt";
    private static final String arqDisciplina = "pucmg-2023-02-lds-g1\\implementacao\\src\\arquivos\\arqDisciplina.txt";
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
            ((Aluno) usuarios.get(nome)).addDisciplinas(disciplinas.get(nomeDisciplina));
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
            ((Aluno) usuarios.get(nome)).removeDisciplinas(disciplinas.get(nomeDisciplina));
        } catch (ClassCastException E) {
            throw new ClassCastException("Você não pode adicionar");
        }
        } else {
            System.out.println("O aluno não está matriculado nessa matéria");
        }
    }

    public boolean confereDisciplina(Aluno aluno, Disciplina disciplina) {
        return aluno.getDisciplinas().contains(disciplina);
    }

    public void notificarSistemaDeCobranca() {
    
        financeiro.emitirCobranca(usuarioAtual.getNome(), arqDisciplina, null);
    }

    public String visualizarAlunos(String nomeDisciplina) {

        return "";
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

    private void carregarUsuario() {
        try {

            Usuario usuario;

            BufferedReader reader = new BufferedReader(new FileReader(arqUsuario));
            String linha;
            reader.readLine();

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
            reader.readLine();

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

    public void criarDisciplina(String nome, int maxAlunos ) {
        Disciplina atual = new Disciplina(maxAlunos, nome );
        this.getDisciplinas().put(nome, atual);
    }

    public String visualizarDisciplinas() {
        StringBuilder resultado = new StringBuilder();
        HashMap<String, Disciplina> disciplinas = this.getDisciplinas();

        for (Map.Entry<String, Disciplina> atual : disciplinas.entrySet()) {
            resultado.append(atual.getKey()).append(": ").append("Maximo alunos:")
                    .append(atual.getValue().getMaxAlunos()).append(", ")
                    .append("É obrigatoria: ").append(atual.getValue().getObrigatoria()).append("\n");
        }
        return resultado.toString();
    }

    private void escreveArquivo(HashMap<?, ? extends ISalvavel> map, String nomeArquivo) {
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo, false);
            map.forEach((key, value) -> {
                try {
                    arquivo.write(value.getDados());
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
