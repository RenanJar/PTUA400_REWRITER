package com.ptuwriter.model.v162;

public enum A400_V162 {

  NR_SEQ(1,8),
  TP_REGISTRO(9,11),
  DT_GERACAO(20, 27),
  QT_TOT_R402(12, 18),
  QT_TOT_R403(19, 25),
  QT_TOT_R404(26, 32),
  QT_TOT_R405(33, 39),
  QT_TOT_R407(40, 46),
  QT_TOT_R408(47, 53),
  QT_TOT_R410(54, 60),
  QT_TOT_R406(61, 67),
  HASH(12, 43);

  private final int start;
  private final int end;

  A400_V162(int start, int end) {
    this.start = start ;
    this.end = end;
  }

  public int getStart() {
    //O retorno peculiar de start-1 se da pelo fato de que os metodos de
    // manipulacao de string do java são iniciados em 0 (pois sao arrays)

    return start-1;
  }

  public int getEnd() {
    return end;
  }
}
