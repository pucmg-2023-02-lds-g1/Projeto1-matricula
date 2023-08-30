package business;
public class Secretario extends Usuario implements ISalvavel{
    
    Secretario(String nome,String senha){
        super(nome,senha);
    }

    
    public String gerarCurriculo(){
        return null;
    }


    public void salvarDados(){

    }

    @Override
    public String getDados(){
        return "S;" + getNome() + ";" + getSenha();
    }
}
