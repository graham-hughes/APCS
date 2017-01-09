//Name - Graham Hughes
//Date - January 19, 2016
//Class - APCS
//Lab  - Lab #11 Stopwatch class

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SimpleStopwatch implements ActionListener{

    private final JButton start;
    private final JButton stop;
    private final JLabel label;
    private final JCheckBox log;
    private final ArrayList<String> times = new ArrayList();
    private Boolean fromStart = false;
    long startTime;
    private JLabel timeLabel;

    public SimpleStopwatch() {
        times.add("0");
        times.add("0");
        times.add("0");
        JFrame jfrm = new JFrame("A Simple Stopwatch");
        jfrm.setSize(220, 150);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.getContentPane().setLayout(new FlowLayout());

        //Make start and stop buttons
        start = new JButton("Start");
        stop = new JButton("Stop");

        timeLabel = new JLabel("");
        log = new JCheckBox("logging");
        //add listener
        start.addActionListener(this);
        stop.addActionListener(this);
        log.addActionListener(this);

        label = new JLabel("Press Start to begin timing.");
        jfrm.getContentPane().add(start);
        jfrm.getContentPane().add(stop);
        jfrm.getContentPane().add(label);
        jfrm.getContentPane().add(log);
        jfrm.getContentPane().add(timeLabel);

        jfrm.setVisible(true);
        stop.setEnabled(false);
    }
    
//    public void itemStateChanged(ItemEvent ie){
//        if (log.isSelected()){
//            times.add(0, (Calendar.getInstance().getTimeInMillis() - startTime) / 1000.0);
//            times.remove(3);
//            timeLabel.setText("" +  times.get(0) +", "+times.get(1) +", "+times.get(2));
//        }
//    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String time = String.valueOf((Calendar.getInstance().getTimeInMillis() - startTime) / 1000.0);
        time = time.substring(0, time.length() - 1); //reduces to two digits after the decimal place as displayed in assignment 

        if (e.getSource() == start) {
            label.setText("Stopwatch is running ...");
            startTime = Calendar.getInstance().getTimeInMillis();
            fromStart = true;
            start.setEnabled(false);
            stop.setEnabled(true);
        } else if (fromStart) {
            label.setText("Elapsed time is " + time);
            fromStart = false; // makes it so that pressing the stop button again does nothing
            times.add(0,time);
            times.remove(3);
            timeLabel.setText(times.get(0) + ", " +times.get(1) + ", "+times.get(2));
            

            stop.setEnabled(false);
            start.setEnabled(true);
        }
        if(e.getSource() == log){
            if(log.isSelected()) timeLabel.setVisible(true);
            else timeLabel.setVisible(false);
        }
    }

}
