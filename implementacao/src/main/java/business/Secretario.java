package business;
public class Secretario extends Usuario{
    
    Secretario(String nome,String senha) throws UsuarioInvalidoException{
        super(nome,senha);
    }

    
    public String gerarCurriculo(){
        return null;
    }


    public void salvarDados(){

    }

    @Override
    public String getDados(){
        return "S;" + getNome() + ";" + getSenha() + ";" + "\n";
    }


    @Override
    public String getTipo() {
        return "S";
    }
}
