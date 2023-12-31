package business;

public class Aluno extends Usuario {

    Aluno(String nome, String senha) throws UsuarioInvalidoException {
        super(nome, senha);
    }
    
    @Override
    public String getDados(){
        return "A;" + getNome() + ";" + getSenha() + ";" + "\n";
    }

    @Override
    public String toString(){
        return ("Nome: "+getNome()+"\n");
    }

    @Override
    public String getTipo() {
        return "A";
    }

    
}
