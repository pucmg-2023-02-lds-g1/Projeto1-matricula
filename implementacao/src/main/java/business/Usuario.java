package business;

public abstract class Usuario implements ISalvavel {
    private String nome;
    private String senha;

    Usuario(String nome, String senha) throws UsuarioInvalidoException {
        setNome(nome);
        setSenha(senha);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) throws UsuarioInvalidoException {
        if(nome!=null) {
        this.nome = nome;
        } else {
            throw new UsuarioInvalidoException();
        }
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) throws UsuarioInvalidoException {
        if(nome!=null) {
        this.senha = senha;
        } else {
            throw new UsuarioInvalidoException();
        }
    }

    public abstract String getDados();

    public abstract String getTipo();
}
