package com.ptuwriter.view;

import com.ptuwriter.entrypoint.A400Controller;
import com.ptuwriter.entrypoint.A400ReeWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class ReWriterView {
  private JFrame frame;
  private JTextField tfInputFilePath;
  private JTextField tfOutputFilePath;
  private JProgressBar progressBar;
  private JButton btnSelectInput;
  private JButton btnSelectOutput;
  private JButton btnProcess;
  private boolean isProcessing = false;
  private A400Controller controller;
  private ResourceBundle bundle;

  public ReWriterView() {
    controller = new A400ReeWriter();
    bundle = ResourceBundle.getBundle("messages");
    initialize();
  }

  private void initialize() {
    frame = new JFrame(bundle.getString("app.title"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 200);
    frame.setLocationRelativeTo(null);
    frame.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);

    addComponents(gbc);
    frame.setVisible(true);
  }

  private void addComponents(GridBagConstraints gbc) {
    addLabel(bundle.getString("label.input"), gbc, 0, 0);
    tfInputFilePath = addTextField(gbc, 1, 0);
    btnSelectInput = addButton(bundle.getString("button.select"), e -> selectInputFile(), gbc, 2, 0);

    addLabel(bundle.getString("label.output"), gbc, 0, 1);
    tfOutputFilePath = addTextField(gbc, 1, 1);
    btnSelectOutput = addButton(bundle.getString("button.select"), e -> selectOutputFile(), gbc, 2, 1);

    progressBar = new JProgressBar(0, 100);
    progressBar.setIndeterminate(true);
    progressBar.setVisible(false);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    frame.add(progressBar, gbc);

    btnProcess = addButtonProcess(bundle.getString("button.process"), e -> processFile(), gbc, 1, 3);
  }

  private JLabel addLabel(String text, GridBagConstraints gbc, int x, int y) {
    JLabel label = new JLabel(text);
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.anchor = GridBagConstraints.WEST;
    frame.add(label, gbc);
    return label;
  }

  private JTextField addTextField(GridBagConstraints gbc, int x, int y) {
    JTextField textField = new JTextField(20);
    textField.setEditable(false);
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1;
    frame.add(textField, gbc);
    return textField;
  }

  private JButton addButton(String text, ActionListener actionListener, GridBagConstraints gbc, int x, int y) {
    JButton button = new JButton(text);
    button.addActionListener(actionListener);
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.fill = GridBagConstraints.EAST;
    gbc.weightx = 0;
    frame.add(button, gbc);
    return button;
  }

  private JButton addButtonProcess(String text, ActionListener actionListener, GridBagConstraints gbc, int x, int y) {
    JButton button = new JButton(text);
    button.addActionListener(actionListener);
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 0;
    frame.add(button, gbc);
    return button;
  }

  private void selectInputFile() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle(bundle.getString("dialog.selectInput"));
    int result = fileChooser.showOpenDialog(frame);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      tfInputFilePath.setText(selectedFile.getAbsolutePath());
    }
  }

  private void selectOutputFile() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle(bundle.getString("dialog.selectOutput"));
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int result = fileChooser.showSaveDialog(frame);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedDirectory = fileChooser.getSelectedFile();
      tfOutputFilePath.setText(selectedDirectory.getAbsolutePath());
    }
  }

  private void processFile() {
    if (isProcessing) {
      return;
    }

    String inputFile = tfInputFilePath.getText();
    String outputFile = tfOutputFilePath.getText();

    if (inputFile.isEmpty() || outputFile.isEmpty()) {
      JOptionPane.showMessageDialog(frame, bundle.getString("error.selectFiles"), bundle.getString("error.title"), JOptionPane.ERROR_MESSAGE);
      return;
    }

    showProgressBar();
    isProcessing = true;
    setButtonsEnabled(false);

    new SwingWorker<Void, Void>() {
      @Override
      protected Void doInBackground() {
        Path inputFilePath = Path.of(inputFile);
        Path outputFilePath = Path.of(outputFile);
        controller.start(inputFilePath, outputFilePath);
        return null;
      }

      @Override
      protected void done() {
        hideProgressBar();
        JOptionPane.showMessageDialog(frame, bundle.getString("success.message"), bundle.getString("success.title"), JOptionPane.INFORMATION_MESSAGE);
        resetUI();
      }
    }.execute();
  }

  private void resetUI() {
    isProcessing = false;
    setButtonsEnabled(true);
  }

  private void setButtonsEnabled(boolean enabled) {
    btnProcess.setEnabled(enabled);
    btnSelectInput.setEnabled(enabled);
    btnSelectOutput.setEnabled(enabled);
  }

  private void showProgressBar() {
    progressBar.setVisible(true);
  }

  private void hideProgressBar() {
    progressBar.setVisible(false);
  }
}