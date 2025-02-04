package com.ptuwriter;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.ptuwriter.view.ReWriterView;

import javax.swing.*;

public class App {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(new FlatDarculaLaf());
      SwingUtilities.invokeLater(ReWriterView::new);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
