package com.ptuwriter.service;

import java.util.stream.Stream;

public interface ServiceRule{

  Stream<String> executarRegras(Stream<String> linhas);
}
