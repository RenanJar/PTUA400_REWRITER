package com.ptuwriter.io;

import com.ptuwriter.service.ServiceRule;
import com.ptuwriter.service.a400.ServiceA400;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ProcessadorPTUA400 implements ProcessadorDeArquivo {

  public Stream<String> processar(Path arquivo) throws IOException {

    ServiceRule rules = new ServiceA400();

    try(Stream<String> linhasAtuais = Files.lines(arquivo);){
      return rules.executarRegras(linhasAtuais);
    }catch (IOException e){
      throw new IOException(e);
    }
  }
}
