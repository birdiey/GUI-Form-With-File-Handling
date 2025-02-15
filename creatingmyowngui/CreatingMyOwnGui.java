package creatingmyowngui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreatingMyOwnGui implements ActionListener {

    JFrame frame;
    JPanel mainP, temp, p1;
    JLabel lblName, lblSurname, lblAge, lblslide, combos;
    JTextField tName, tSurname, tAge;
    JMenuBar menubar;
    JMenu menuFile, menuEdit;
    JRadioButton male, female;
    JButton bAdd, bClear;
    JTextArea tArea;
    ButtonGroup grp;
    JSlider slide;
    JComboBox<String> cBox;

    JMenuItem mSave, mOpen;

    public CreatingMyOwnGui() {
        frame = new JFrame("My World");
        frame.setSize(450, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        mainP = new JPanel(new BorderLayout());
        mainP.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        p1 = new JPanel(new BorderLayout());

        lblName = new JLabel("Name:");
        lblSurname = new JLabel("Surname:");
        lblAge = new JLabel("Age:");

        tName = new JTextField(10);
        tSurname = new JTextField(10);
        tAge = new JTextField(10);

        temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.add(lblName);
        temp.add(tName);
        p1.add(temp, BorderLayout.NORTH);

        temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.add(lblSurname);
        temp.add(tSurname);
        p1.add(temp, BorderLayout.CENTER);

        temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.add(lblAge);
        temp.add(tAge);
        p1.add(temp, BorderLayout.SOUTH);

        mainP.add(p1, BorderLayout.NORTH);

        setMenu();

        p1 = new JPanel(new BorderLayout());

        slide = new JSlider(140, 200, 140);
        slide.setMajorTickSpacing(10);
        slide.setPaintLabels(true);
        slide.setPaintTicks(true);

        lblslide = new JLabel("Height:");

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        grp = new ButtonGroup();
        grp.add(male);
        grp.add(female);

        cBox = new JComboBox<String>();
        cBox.addItem("Fav Lang");
        cBox.addItem("Python");
        cBox.addItem("C#");
        cBox.addItem("C++");
        cBox.addItem("Java");

        combos = new JLabel("Programming langs");

        temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.add(lblslide);
        temp.add(slide);
        p1.add(temp, BorderLayout.NORTH);

        temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.add(male);
        temp.add(female);
        p1.add(temp, BorderLayout.CENTER);

        temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.add(combos);
        temp.add(cBox);
        p1.add(temp, BorderLayout.SOUTH);

        mainP.add(p1, BorderLayout.CENTER);

        p1 = new JPanel(new BorderLayout());

        bAdd = new JButton("Add");
        bAdd.addActionListener(this);
        bClear = new JButton("Clear");
        bClear.addActionListener(this);

        tArea = new JTextArea(10, 15);

        temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.add(tArea);
        p1.add(temp, BorderLayout.NORTH);

        temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.add(bAdd);
        temp.add(bClear);
        p1.add(temp, BorderLayout.CENTER);

        mainP.add(p1, BorderLayout.SOUTH);

        frame.setJMenuBar(menubar);
        frame.add(mainP);
        frame.setVisible(true);
        frame.pack();
    }

    public static void main(String[] args) {

        new CreatingMyOwnGui();
    }

    private void setMenu() {

        menubar = new JMenuBar();

        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");

        mSave = new JMenuItem("Save File");
        mSave.addActionListener(this);
        mOpen = new JMenuItem("Open File");
        mOpen.addActionListener(this);
       

        menuFile.add(mSave);
        menuFile.add(mOpen);

        menubar.add(menuFile);
        menubar.add(menuEdit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bAdd) {
            String name = tName.getText();
            String surmane = tSurname.getText();
            String age = tAge.getText();
            int slider = slide.getValue();
            String combB = cBox.getSelectedItem().toString();
            String gen = " ";
            if (female.isSelected()) {
                gen = female.getText();
            } else {
                gen = male.getText();
            }

            String str = name + "\n" + surmane + "\n" + age + "\n" + slider + "\n" + combB + "\n" + gen;
            tArea.setText(str);

        } else if (e.getSource() == mSave) {

            JFileChooser jF = new JFileChooser(".");
            jF.setSelectedFile(new File("CreatingGUI.txt"));

            if (jF.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter fW = new FileWriter(jF.getSelectedFile());
                    fW.write(tArea.getText());
                    fW.close();
                } catch (IOException e1) {
                    e1.getMessage();

                }
            }

        } else if (e.getSource() == bClear) {
            // tName, tSurname, tAge;
            tName.setText(" ");
            tSurname.setText(" ");
            tAge.setText(" ");
            grp.clearSelection();
            tArea.setText(" ");
            slide.setValue(0);
        } else if (e.getSource() == mOpen) {

            JFileChooser jC = new JFileChooser(".");

            if (jC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = jC.getSelectedFile();
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    
                    while((line=br.readLine())!= null){
                        tArea.append(line+" \n")  ;
                         br.close();
                    }
                   

                } catch (IOException ex) {
                    Logger.getLogger(CreatingMyOwnGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
