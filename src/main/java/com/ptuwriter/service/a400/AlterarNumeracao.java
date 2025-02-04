package com.ptuwriter.service.a400;

import com.ptuwriter.model.v162.A400_V162;

import java.util.List;

public class AlterarNumeracao implements RulesA400 {
  final int start = A400_V162.NR_SEQ.getStart();
  final int end = A400_V162.NR_SEQ.getEnd();

  @Override
  public String execute(String linha, Long index, List<String> linhasProcessadas) {
    StringBuilder linhaAlterada = new StringBuilder(linha);

    String value = String.format("%08d",index);
    linhaAlterada.replace(start, end, value);

    return linhaAlterada.toString();
  }
}
