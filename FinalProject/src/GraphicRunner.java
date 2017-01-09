/*
 Graham Hughes
 Mrs. Hemiup
 April 12, 2016
 APCS Second Semester Final Project Graphic Runner (adds/removes/modifies
 the various fractal JLabels based on the GUI inputs)
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public final class GraphicRunner extends JFrame implements ActionListener, ChangeListener {

    //radio buttons for toggling between fractals
    private final ButtonGroup chooseFractal;
    private final JRadioButton tree;
    private final JRadioButton triangle;
    private final JRadioButton original;
    private final JRadioButton carpet;
    private final JMenuBar menu;
    private final Boolean[] sequence; //must be Tree, Original, Tree, Carpet, Tree, Triangle, Tree

    //JPanel that stores the fractals when they are displayed
    private JPanel pane;

    //slider for adjusting fractals
    //leftmost position is largest size (least detail / fewest recursions)
    //rightmost position is smalles size (most detail / most recurisions)
    private final JSlider fractalDepth;

    public GraphicRunner() {
        super("Fractal Final Project - Graham Hughes");
        setSize(800, 600);

        getContentPane().setLayout(new FlowLayout());
        pane = new JPanel();
        chooseFractal = new ButtonGroup();
        tree = new JRadioButton("Tree Fractal");
        triangle = new JRadioButton("Triangle Fractal");
        original = new JRadioButton("Original Fractal");
        carpet = new JRadioButton("Carpet Fractal");
        menu = new JMenuBar();
        sequence = new Boolean[]{false, false, false, false, false, false};

        setJMenuBar(menu);

        fractalDepth = new JSlider(0, 100, 0);

        chooseFractal.add(tree);
        chooseFractal.add(triangle);
        chooseFractal.add(original);
        chooseFractal.add(carpet);

        menu.setBackground(Color.white);
        menu.add(tree);
        menu.add(triangle);
        menu.add(original);
        menu.add(carpet);
        menu.add(fractalDepth);
        getContentPane().add(menu);

        tree.addActionListener(this);
        triangle.addActionListener(this);
        original.addActionListener(this);
        carpet.addActionListener(this);
        fractalDepth.addChangeListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //tree fractal is initially selected and generated for minimum value
        tree.setSelected(true);
        runTree(191);
    }

    public static void main(String args[]) throws IOException {
        GraphicRunner run = new GraphicRunner();
    }

    //each "run" method deletes the old pane, resets it to the correct JPanel,
    //adds it back, and sets it visible. They all have a min parameter that is controlled by
    //the slider. 
    public void runTree(int min) {
        getContentPane().remove(pane);
        pane = new Tree(1.05, min, .8, 200);
        getContentPane().add(pane);
        setVisible(true);
    }

    public void runTriangle(int min) {
        getContentPane().remove(pane);
        pane = new Triangle(min);
        getContentPane().add(pane);
        setVisible(true);
    }

    public void runOriginal(int min) {
        getContentPane().remove(pane);
        pane = new OriginalFractal(4, min, 200);
        getContentPane().add(pane);
        setVisible(true);
    }

    public void runCarpet(int min) {
        getContentPane().remove(pane);
        pane = new Carpet(min);
        getContentPane().add(pane);
        setVisible(true);
    }

    //Calls the "run" method corresponding to the selected button of the button group
    //and passes in a min value based on the adjusted slider value
    @Override
    public void actionPerformed(ActionEvent e) {

        //Super secret extra credit functionality :D
        Boolean yes = true;
        for (Boolean s : sequence) {
            if (s == false) {
                yes = false;
            }
        }
        System.out.println("");
        if (yes == true) {
            try {
                specialExtraCredit();
            } catch (IOException ex) {
                Logger.getLogger(GraphicRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == tree) {
            runTree(191 - (int) (fractalDepth.getValue() * 1.9));
            
            //Super secret extra credit functionality :D
            if (sequence[0] == false) {
                sequence[0] = true;
            } else if (sequence[0] == true && sequence[1] == true && sequence[2] == false) {
                sequence[2] = true;
            } else if (sequence[0] == true && sequence[1] == true && sequence[2] == true && sequence[3] == true && sequence[4] == false) {
                sequence[4] = true;
            } else {
                for (Boolean seq : sequence) {
                    seq = false;
                }
            }

        }
        if (e.getSource() == triangle) {
            runTriangle(401 - 4 * fractalDepth.getValue());
            
            //Super secret extra credit functionality :D
            if (sequence[0] == true && sequence[1] == true && sequence[2] == true && sequence[3] == true && sequence[4] == true && sequence[5] == false) {
                sequence[5] = true;
            } else {
                for (Boolean seq : sequence) {
                    seq = false;

                }
            }

        }
        if (e.getSource() == original) {
            runOriginal(200 - 2 * fractalDepth.getValue());
            
            //Super secret extra credit functionality :D
            if (sequence[0] == true && sequence[1] == false) {
                sequence[1] = true;
            } else {
                for (Boolean seq : sequence) {
                    seq = false;
                }
            }

        }
        if (e.getSource() == carpet) {
            runCarpet((int) (361 - 3.6 * fractalDepth.getValue()));
            
            //Super secret extra credit functionality :D
            if (sequence[0] == true && sequence[1] == true && sequence[2] == true && sequence[3] == false) {
                sequence[3] = true;
            } else {
                for (Boolean seq : sequence) {
                    seq = false;
                }
            }

        }
    }

    //Runs when the slider is moved. Refreshes the current fractal to the new min.
    @Override
    public void stateChanged(ChangeEvent e) {

        if (tree.isSelected()) {
            runTree(191 - (int) (fractalDepth.getValue() * 1.9));
        }
        if (triangle.isSelected()) {
            runTriangle(401 - 4 * fractalDepth.getValue());
        }
        if (original.isSelected()) {
            runOriginal(201 - 2 * fractalDepth.getValue());
        }
        if (carpet.isSelected()) {
            runCarpet((int) (361 - 3.6 * fractalDepth.getValue()));
        }
    }

    public void specialExtraCredit() throws IOException {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        CreativeExtraCredit c = new CreativeExtraCredit();
        c.playSound();
        while (this.isActive()) {
            CreativeExtraCredit.setOutputVolume((float) 7.0);
        }
    }

}
