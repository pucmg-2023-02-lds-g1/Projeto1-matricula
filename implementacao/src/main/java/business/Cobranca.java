package business;

public class Cobranca {

    private String descricao;
    private Double preco;

    public Cobranca(String descricao, Double preco) {
        setDescricao(descricao);
        setPreco(preco);
    }

    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        if(descricao.length() > 0){
            this.descricao = descricao;
        }
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        if(preco >= 0){
            this.preco = preco;
        }
        
    }

    public String formataDados(){

        return getDescricao() + "\n" + getPreco();
    }

    
    
}
