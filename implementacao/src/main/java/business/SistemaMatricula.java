package business;

public class SistemaMatricula {
    
    public String nome;

    public SistemaMatricula(String nome){
        if(nome.length() > 1){
            this.nome = nome;
        }
    }

    public Disciplina matricularDisciplina(String nome, String nomeDisciplina){

        return "";
    }

    public Disciplina cancelarMatricula(String nome, String nomeDisciplina){

        return "";
    }

    public void confereDisciplina(){
        
    }
    
    public void notificarSistemaDeCobranca(){

    }
     
    public String visualizarAlunos(String nomeDisciplina){

        return "";
    }

    public void validarLogin(String nome, String senha){

    }

    public void cadastro(){

    }
}
