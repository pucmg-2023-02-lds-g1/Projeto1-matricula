package business;

import java.util.*;
import java.util.Map.Entry;

public class SistemaCobranca {

   private HashMap<String, Cobranca> cobrancas = new HashMap<>();


   public HashMap<String, Cobranca> getCobrancas(){
      return this.cobrancas;
   }

   public void addCobrancas(String nomeAluno,Cobranca cobranca){
      cobrancas.put(nomeAluno,cobranca);
   }

   public void setCobrancas(HashMap<String, Cobranca> cobrancas){
      this.cobrancas = cobrancas;
   }

   public void emitirCobranca(String nomeAluno, String desc, Double preco) {

      if (cobrancas.containsKey(nomeAluno)) {
         Cobranca cobranca = cobrancas.remove(nomeAluno);
         cobranca.setPreco(preco);
         cobrancas.put(nomeAluno, cobranca);

      } else {
         Cobranca c = new Cobranca(desc, preco);
         cobrancas.put(nomeAluno, c);
      }

      for (Entry<String, Cobranca> entry : cobrancas.entrySet()) {
         String key = entry.getKey();
         String value = entry.getValue().toString();
         System.out.println("Key: " + key + ", Value: " + value);
      }
   }
   

   public String visualizaCobranca(String nomeAluno) throws CobrancaInvalidaException{

      if (cobrancas.containsKey(nomeAluno)){
         return cobrancas.get(nomeAluno).formataDados();
      }else{
         return "Você não possui nenhuma cobrança";
      }
       
      
   }

}