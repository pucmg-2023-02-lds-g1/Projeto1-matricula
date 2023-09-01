package business;

public class Aluno extends Usuario {

    Aluno(String nome, String senha) {
        super(nome, senha);
    }
    
    public String visualizarCobranca(SistemaCobranca financeiro){

        return financeiro.visualizaCobranca(getNome());
    }

    @Override
    public String getDados(){
        return "A;" + getNome() + ";" + getSenha() + ";" + "\n";
    }

    @Override
    public String toString(){
        return ("Nome: "+getNome()+"\n");
    }
}
