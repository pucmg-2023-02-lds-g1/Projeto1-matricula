package business;

import java.util.*;

public class SistemaCobranca {

   private HashMap<String, Cobranca> cobrancas = new HashMap<>();

   public void emitirCobranca(String nomeAluno, String desc, Double preco) {

      Cobranca c = new Cobranca(desc, preco);
      cobrancas.put(nomeAluno, c);
   }

   public String visualizaCobranca(String nomeAluno) {

      return cobrancas.get(nomeAluno).formataDados();
   }

}