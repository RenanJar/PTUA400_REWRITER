package com.ptuwriter.service.a400;

import java.util.List;

public interface RulesA400 {

  String execute(String linha, Long index, List<String> linhasProcessadas);
}
