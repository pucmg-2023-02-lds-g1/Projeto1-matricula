package business;

public class Professor extends Usuario implements ISalvavel {

    Professor(String nome, String senha) {
        super(nome, senha);

    }

    @Override
    public String getDados(){
        return "P;" + getNome() + ";" + getSenha();
    }
}
