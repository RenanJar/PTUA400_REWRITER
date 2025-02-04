package com.ptuwriter.io;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface ReescritorDeArquivo {

  void reescrever(Path path, Stream<String> conteudo, String nomeArquivo);
}
