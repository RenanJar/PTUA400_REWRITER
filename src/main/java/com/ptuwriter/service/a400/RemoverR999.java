package com.ptuwriter.service.a400;

import com.ptuwriter.service.a400.util.Util;

import java.util.List;

public class RemoverR999 implements RulesA400 {

  @Override
  public String execute(String linha, Long index, List<String> linhasProcessadas) {
    if(!isR999(linha))return linha;
    return null;
  }

  private boolean isR999(String linha) {
    return Util.verifyTP_Registro(linha, "999");
  }
}
