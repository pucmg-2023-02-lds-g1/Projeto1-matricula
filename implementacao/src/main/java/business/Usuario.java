package business;

public abstract class Usuario implements ISalvavel {
    private String nome;
    private String senha;

    Usuario(String nome, String senha) {
        setNome(nome);
        setSenha(senha);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {

    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        
    }

    public abstract String getDados();
}
