package com.ptuwriter.io;


import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface ProcessadorDeArquivo {
  Stream<String> processar(Path arquivo)throws IOException;
}
