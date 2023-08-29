package business;
public class Secretario extends Usuario implements ISalvavel{

    private String nome;
    private String senha;
    
    
    Secretario(String nome,String senha){
        super(nome,senha);
    }

    
    String gerarCurriculo(){
        return null;
    }


    void salvarDados(){

    }

    @Override
    public String getDados(){
        return "S;" + this.nome + ";" + this.senha;
    }
}
