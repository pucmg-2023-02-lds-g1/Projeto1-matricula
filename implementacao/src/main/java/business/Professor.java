package business;

public class Professor extends Usuario implements ISalvavel {

    private String nome;
    private String senha;

    Professor(String nome, String senha) {
        super(nome, senha);

    }

    public String getDados(){
        return "P;" + this.nome + ";" + this.senha;
    }
}
