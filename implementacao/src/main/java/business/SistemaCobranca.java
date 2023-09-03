package business;

import java.util.*;

public class SistemaCobranca {

   private HashMap<String, Cobranca> cobrancas = new HashMap<>();

   public void emitirCobranca(String nomeAluno, String desc, Double preco) {

      if (cobrancas.containsKey(nomeAluno)) {
         Cobranca cobranca = cobrancas.remove(nomeAluno);
         cobranca.setPreco(preco);
         cobrancas.put(nomeAluno, cobranca);

      } else {
         Cobranca c = new Cobranca(desc, preco);
         cobrancas.put(nomeAluno, c);
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