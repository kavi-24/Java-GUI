import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor {

  public static void main(String[] args) {
    new Editor();
  }
}

class Editor extends JFrame implements ActionListener {

  JTextArea textArea;
  JScrollPane scrollPane;
  JSpinner fontSizeSpinner;
  JLabel fontLabel;
  JButton fontColorButton;
  JComboBox fontBox;

  JMenuBar menuBar;
  JMenu fileMenu;
  JMenuItem openItem;
  JMenuItem saveItem;
  JMenuItem exitItem;

  Editor() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Text __ Editor");
    this.setSize(500, 500);
    this.setLayout(new FlowLayout());
    this.setLocationRelativeTo(null);

    textArea = new JTextArea();
    // textArea.setPreferredSize(new Dimension(450, 450));
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    String myFont = "Hack";
    textArea.setFont(new Font(myFont, Font.PLAIN, 20));

    fontLabel = new JLabel("Font: ");

    scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(450, 450));
    scrollPane.setVerticalScrollBarPolicy(
      ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
    );

    fontSizeSpinner = new JSpinner();
    fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
    fontSizeSpinner.setValue(20);
    fontSizeSpinner.addChangeListener(
      new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          textArea.setFont(
            new Font(
              textArea.getFont().getFamily(),
              Font.PLAIN,
              (int) fontSizeSpinner.getValue()
            )
          );
        }
      }
    );

    fontColorButton = new JButton("Color");
    fontColorButton.addActionListener(this);

    String[] fonts = GraphicsEnvironment
      .getLocalGraphicsEnvironment()
      .getAvailableFontFamilyNames();
    fontBox = new JComboBox(fonts);
    fontBox.addActionListener(this);
    fontBox.setSelectedItem("Cantarell");

    menuBar = new JMenuBar();
    fileMenu = new JMenu("File");
    openItem = new JMenuItem("Open");
    saveItem = new JMenuItem("Save");
    exitItem = new JMenuItem("Exit");

    openItem.addActionListener(this);
    saveItem.addActionListener(this);
    exitItem.addActionListener(this);

    fileMenu.add(openItem);
    fileMenu.add(saveItem);
    fileMenu.add(exitItem);

    menuBar.add(fileMenu);

    this.setJMenuBar(menuBar);
    this.add(fontLabel);
    this.add(fontSizeSpinner);
    // this.add(textArea);
    this.add(fontColorButton);
    this.add(fontBox);
    this.add(scrollPane);
    this.setVisible(true); // Add this line at last
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == fontColorButton) {
      // JColorChooser colorChooser = new JColorChooser();
      textArea.setForeground(
        JColorChooser.showDialog(null, "Choose A Color", Color.BLACK)
      );
    }
    if (e.getSource() == fontBox) {
      textArea.setFont(
        new Font(
          (String) fontBox.getSelectedItem(),
          Font.PLAIN,
          textArea.getFont().getSize()
        )
      );
    }
    if (e.getSource() == openItem) {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File("."));
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
      fileChooser.setFileFilter(filter);

      int response = fileChooser.showOpenDialog(null);

      if (response == JFileChooser.APPROVE_OPTION) {
          File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
          Scanner fileIn = null;

          try {
            fileIn = new Scanner(file);
            if (file.isFile()) {
                textArea.setText("");
                while (fileIn.hasNextLine()) {
                    String line = fileIn.nextLine()+"\n";
                    textArea.append(line);
                }
            }
          } catch (Exception e1) {
              e1.printStackTrace();
          } finally {
              fileIn.close();
          }
          
      }
    }
    if (e.getSource() == saveItem) {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File("."));

      int response = fileChooser.showSaveDialog(null);

      if (response == JFileChooser.APPROVE_OPTION) {
        File file;
        PrintWriter fileOut = null;

        file = new File(fileChooser.getSelectedFile().getAbsolutePath());

        try {
          fileOut = new PrintWriter(file);
          fileOut.println(textArea.getText());
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        } finally {
          fileOut.close();
        }
      }
    }
    if (e.getSource() == exitItem) {
      System.exit(0);
    }
  }
}

