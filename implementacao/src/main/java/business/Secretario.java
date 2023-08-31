package business;
public class Secretario extends Usuario{
    
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
        return "Secretario:" + getNome()+ "\n";
    }
}
