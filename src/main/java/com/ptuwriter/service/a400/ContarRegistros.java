package com.ptuwriter.service.a400;

import com.ptuwriter.model.v162.A400_V162;
import com.ptuwriter.service.a400.util.Util;

import java.util.List;

public class ContarRegistros implements RulesA400 {

  final int R402_start = A400_V162.QT_TOT_R402.getStart();
  final int R402_end = A400_V162.QT_TOT_R402.getEnd();
  final int R403_start = A400_V162.QT_TOT_R403.getStart();
  final int R403_end = A400_V162.QT_TOT_R403.getEnd();
  final int R404_start = A400_V162.QT_TOT_R404.getStart();
  final int R404_end = A400_V162.QT_TOT_R404.getEnd();
  final int R405_start = A400_V162.QT_TOT_R405.getStart();
  final int R405_end = A400_V162.QT_TOT_R405.getEnd();
  final int R406_start = A400_V162.QT_TOT_R406.getStart();
  final int R406_end = A400_V162.QT_TOT_R406.getEnd();
  final int R407_start = A400_V162.QT_TOT_R407.getStart();
  final int R407_end = A400_V162.QT_TOT_R407.getEnd();
  final int R408_start = A400_V162.QT_TOT_R408.getStart();
  final int R408_end = A400_V162.QT_TOT_R408.getEnd();
  final int R410_start = A400_V162.QT_TOT_R410.getStart();
  final int R410_end = A400_V162.QT_TOT_R410.getEnd();

  @Override
  public String execute(String linha, Long index, List<String> linhasProcessadas) {
    if(!isR499(linha))return linha;
    String linhaAlterada = count(linhasProcessadas,linha);
    return linhaAlterada;
  }

  private boolean isR499(String linha){
    return Util.verifyTP_Registro(linha,"499");
  }


  private String count(List<String> linhasProcessadas,String linhaAtual){

    Long totalR402 = 0L;
    Long totalR403 = 0L;
    Long totalR404 = 0L;
    Long totalR405 = 0L;
    Long totalR406 = 0L;
    Long TotalR407 = 0L;
    Long TotalR408 = 0L;
    Long TotalR410 = 0L;

    for(String linha :linhasProcessadas){
      if(Util.verifyTP_Registro(linha,"402"))totalR402++;
      if(Util.verifyTP_Registro(linha,"403"))totalR403++;
      if(Util.verifyTP_Registro(linha,"404"))totalR404++;
      if(Util.verifyTP_Registro(linha,"405"))totalR405++;
      if(Util.verifyTP_Registro(linha,"406"))totalR406++;
      if(Util.verifyTP_Registro(linha,"407"))TotalR407++;
      if(Util.verifyTP_Registro(linha,"408"))TotalR408++;
      if(Util.verifyTP_Registro(linha,"410"))TotalR410++;
    }

    StringBuilder linhaAlterada = new StringBuilder(linhaAtual);
    linhaAlterada.replace(R402_start,R402_end,String.format("%07d",totalR402));
    linhaAlterada.replace(R403_start,R403_end,String.format("%07d",totalR403));
    linhaAlterada.replace(R404_start,R404_end,String.format("%07d",totalR404));
    linhaAlterada.replace(R405_start,R405_end,String.format("%07d",totalR405));
    linhaAlterada.replace(R406_start,R406_end,String.format("%07d",totalR406));
    linhaAlterada.replace(R407_start,R407_end,String.format("%07d",TotalR407));
    linhaAlterada.replace(R408_start,R408_end,String.format("%07d",TotalR408));
    linhaAlterada.replace(R410_start,R410_end,String.format("%07d",TotalR410));
    return linhaAlterada.toString();
  }
}
