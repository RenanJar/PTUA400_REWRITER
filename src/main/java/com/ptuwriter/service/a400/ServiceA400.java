package com.ptuwriter.service.a400;

import com.ptuwriter.service.ServiceRule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class ServiceA400 implements ServiceRule {

  private final List<RulesA400> rules = getRules();

  @Override
  public Stream<String> executarRegras(Stream<String> linhas) {
    List<String> linhasAlteradas = new ArrayList<>();
    AtomicLong index = new AtomicLong(0);

    linhas.forEach(linha -> {

      Long i = index.incrementAndGet();
      String linhaAlterada = aplicarRegras(linha,i,linhasAlteradas);

      if(linhaAlterada != null && !linhaAlterada.isEmpty()){
        linhasAlteradas.add(linhaAlterada);
      }

    });

    return linhasAlteradas.stream();
  }

  private String aplicarRegras(String linha,Long index,List<String> linhasAlteradas){
    String linhaAlterada = linha;

    for(RulesA400 rule : rules){
      linhaAlterada = rule.execute(linhaAlterada, index, linhasAlteradas);
    }

    return linhaAlterada;
  }

  public List<RulesA400> getRules(){
    List<RulesA400> rules = new ArrayList<>();
    rules.add(new AlterarData());
    rules.add(new AlterarNumeracao());
    rules.add(new ContarRegistros());
    rules.add(new AlterarHash());
    rules.add(new RemoverR999());
    return rules;
  }

}
