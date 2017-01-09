//Name - Graham Hughes
//Date - February 3, 2016
//Class - APCS
//Lab  - Lab #12 GUI class
//
//This class implements the GUI and logic

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class GUI implements ActionListener {
    //all of the private variables
    private final JTextField nameField;
    private final JLabel nameLabel;
    private final JTextField numberField;
    private final JLabel numberLabel;
    private final JLabel label; 
    private final JLabel empty1;
    private final JLabel empty2;
    private final JToggleButton ignoreCase;
    private final JRadioButton ends;
    private final JRadioButton starts;
    private final JRadioButton exact;
    private final JButton add;

    private final ButtonGroup with;
    private String[] name;
    private String[] number;

    //Constructor takes names and numbers arrays as inputs
    public GUI(String[] names, String[] numbers) {
        name = names;
        number = numbers;
        
        //Setting up the JFrame
        JFrame jfrm = new JFrame("A Simple Phone List");
        jfrm.setSize(200, 270);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.getContentPane().setLayout(new GridLayout(12,1));

        //Make input fields and labels
        nameLabel = new JLabel("Name");
        nameField = new JTextField(10);
        numberLabel = new JLabel("Number");
        numberField = new JTextField(10);
        
        ignoreCase = new JCheckBox("Ignore Case");
        ignoreCase.setSize(10, 10);

        empty1 = new JLabel("");
        empty2 = new JLabel("");
        
        ends = new JRadioButton("Ends With");
        starts = new JRadioButton("Starts With");
        exact = new JRadioButton("Exact Match");
        add = new JButton("Add to Adress Book");

        with = new ButtonGroup();
        with.add(exact);
        with.add(starts);
        with.add(ends);

        
        //add listener
        nameField.addActionListener(this);
        numberField.addActionListener(this);
        add.addActionListener(this);
        label = new JLabel("Search Options");
        
        //adding all of the J objects
        jfrm.getContentPane().add(nameLabel);
        jfrm.getContentPane().add(nameField);
        jfrm.getContentPane().add(numberLabel);
        jfrm.getContentPane().add(numberField);
        jfrm.getContentPane().add(empty1);
        jfrm.getContentPane().add(label);
        jfrm.getContentPane().add(ignoreCase);
        jfrm.getContentPane().add(empty2);
        jfrm.getContentPane().add(exact);
        jfrm.getContentPane().add(starts);
        jfrm.getContentPane().add(ends);
        jfrm.getContentPane().add(add);

        jfrm.setVisible(true);
        exact.setSelected(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add){   //extra credit, allows you to add names to the address book
            int len = name.length;
            String[] newName = new String[len +1];
            String[] newNumber = new String[len +1];
            for(int i = 0; i<len; i++){
                newName[i] = name[i];
                newNumber[i] = number[i];
            } 
            newName[len] = nameField.getText();
            newNumber[len] = numberField.getText();
            name = newName;
            number = newNumber;
            for(String str:name)System.out.println(str);
    }
        if (e.getSource() == nameField) { //triggered by writing in name box
            if(!ignoreCase.isSelected()){ 
                if (exact.isSelected()){
                    for(int i = 0; i < name.length;i++){
                        if (name[i].equals(nameField.getText())) numberField.setText(""+ number[i]);
                    }            
                }else if(starts.isSelected()){
                    for(int i = 0; i < name.length;i++){
                        if (name[i].startsWith(nameField.getText())) numberField.setText(""+ number[i]);
                    }          
                }else if (ends.isSelected()){
                    for(int i = 0; i < name.length;i++){
                        if (name[i].endsWith(nameField.getText())) numberField.setText(""+ number[i]);
                    }     
                }
            } else{
                if (exact.isSelected()){
                    for(int i = 0; i < name.length;i++){
                        if (name[i].equalsIgnoreCase(nameField.getText())) numberField.setText(""+ number[i]);
                    }            
                }else if(starts.isSelected()){
                    for(int i = 0; i < name.length;i++){ //no ignore case starts with, so convets both to lower case
                        if (name[i].toLowerCase().startsWith(nameField.getText().toLowerCase())) numberField.setText(""+ number[i]);
                    }          
                }else if (ends.isSelected()){
                    for(int i = 0; i < name.length;i++){ //no ignore case starts with, so convets both to lower case
                        if (name[i].toLowerCase().endsWith(nameField.getText().toLowerCase())) numberField.setText(""+ number[i]);
                    }     
                }
            }
        }
        else if (e.getSource() == numberField) { //triggered by writing in name box 
            if (exact.isSelected()){
                for(int i = 0; i < name.length;i++){
                    if (number[i].equals(numberField.getText())) nameField.setText(""+ name[i]);
                }            
            }else if(starts.isSelected()){
                for(int i = 0; i < name.length;i++){
                    if (number[i].startsWith(numberField.getText())) nameField.setText(""+ name[i]);
                }          
            }else if (ends.isSelected()){
                for(int i = 0; i < name.length;i++){
                    if (number[i].endsWith(numberField.getText())) nameField.setText(""+ name[i]);
                }     
            } 
        } 
    }
}

