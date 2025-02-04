package com.ptuwriter.service.a400;

import com.ptuwriter.model.v162.A400_V162;
import com.ptuwriter.service.a400.util.Util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class AlterarHash implements RulesA400 {

  final int start = A400_V162.HASH.getStart();
  final int end = A400_V162.HASH.getEnd();

  @Override
  public String execute(String linha, Long index, List<String> linhasProcessadas) {
    if (!isR998(linha)) return linha;
    List<String> linhasToHash = linhasProcessadas;

    String hash = generateHash(linhasToHash);
    StringBuilder linhaAlterada = new StringBuilder(linha);
    linhaAlterada.replace(start, end, hash);
    return linhaAlterada.toString();
  }

  private boolean isR998(String linha){
    return Util.verifyTP_Registro(linha,"998");
  }

  private String generateHash(List<String> linhas){
    String conteudoArquivo = String.join("",linhas);

    try{
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] hashInBytes = md.digest(conteudoArquivo.getBytes(StandardCharsets.ISO_8859_1));
      StringBuilder hashMd5 = new StringBuilder();
      for(byte b : hashInBytes){
        hashMd5.append(String.format("%02x",b));
      }
      return hashMd5.toString();
    }catch (NoSuchAlgorithmException e){
      throw new RuntimeException("Erro ao gerar Hash");
    }
  }


}
