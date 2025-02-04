package com.ptuwriter.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class ReescritorPTUA400 implements ReescritorDeArquivo {

  @Override
  public void reescrever(Path path, Stream<String> conteudo, String nomeArquivo) {
    String novoNome = gerarNomeArquivo(nomeArquivo);
    Path novoArquivo = path.resolve(novoNome);

    try(BufferedWriter writer = Files.newBufferedWriter(novoArquivo);){
      conteudo.forEach(linha -> {
        try{
          writer.write(linha);
          writer.newLine();
        }catch (IOException e){
          throw new RuntimeException("Erro ao gravar Arquivo");
        }
      });

    }catch (IOException e){
      throw new RuntimeException("Erro ao gravar Arquivo");
    }

  }

  private String gerarNomeArquivo(String nomeAtual){
    String extencao = nomeAtual.split("\\.")[1];
    String dataHoje = getDataHoje();

    return "C"+dataHoje+"."+extencao;
  }

  private String getDataHoje(){
    LocalDate dataHoje = LocalDate.now();
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("ddMMyy");
    return dataHoje.format(formatador);
  }

}
