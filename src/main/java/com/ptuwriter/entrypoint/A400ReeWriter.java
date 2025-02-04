package com.ptuwriter.entrypoint;

import com.ptuwriter.io.ProcessadorDeArquivo;
import com.ptuwriter.io.ProcessadorPTUA400;
import com.ptuwriter.io.ReescritorDeArquivo;
import com.ptuwriter.io.ReescritorPTUA400;

import java.nio.file.Path;
import java.util.stream.Stream;

public class A400ReeWriter implements A400Controller {

  @Override
  public void start(Path inputPath, Path outputPath) {

    ProcessadorDeArquivo processador = new ProcessadorPTUA400();
    ReescritorDeArquivo reescritor = new ReescritorPTUA400();

    try {
      Stream<String> conteudoProcessado = processador.processar(inputPath);
      reescritor.reescrever(outputPath, conteudoProcessado,inputPath.getFileName().toString());
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
    }
  }
}
