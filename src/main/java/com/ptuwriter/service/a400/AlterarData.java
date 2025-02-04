package com.ptuwriter.service.a400;

import com.ptuwriter.model.v162.A400_V162;
import com.ptuwriter.service.a400.util.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AlterarData implements RulesA400 {

  final int start = A400_V162.DT_GERACAO.getStart();
  final int end = A400_V162.DT_GERACAO.getEnd();

  @Override
  public String execute(String linha, Long index, List<String> linhaProcessadas){

    if(!isR401(linha))return linha;

    String dataHoje = getDataHoje();

    StringBuilder linhaAlterada = new StringBuilder(linha);

    linhaAlterada.replace(start,end,dataHoje);

    return linhaAlterada.toString();
  }

  private String getDataHoje(){
    LocalDate dataHoje = LocalDate.now();
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyyMMdd");
    return dataHoje.format(formatador);
  }

  private boolean isR401(String linha){
    return Util.verifyTP_Registro(linha, "401");
  }

}
