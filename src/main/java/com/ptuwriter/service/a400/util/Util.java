package com.ptuwriter.service.a400.util;

import com.ptuwriter.model.v162.A400_V162;

public class Util {

  public static boolean verifyTP_Registro(String linha,String isResgistro){
    if(linha.isBlank())return false;
    final int start = A400_V162.TP_REGISTRO.getStart();
    final int end = A400_V162.TP_REGISTRO.getEnd();
    return linha.substring(start,end).equals(isResgistro);
  }
}
