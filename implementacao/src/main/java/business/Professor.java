package business;

public class Professor extends Usuario {

    Professor(String nome, String senha) {
        super(nome, senha);

    }

    @Override
    public String getDados(){
        return "P;" + getNome() + ";" + getSenha() + "\n";
    }
}
