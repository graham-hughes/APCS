/*
 Graham Hughes
 Mrs. Hemiup
 April 12, 2016
 APCS Second Semester Final Project Tree Fractal
 Randomness of branch angle/length and smiley fruit added
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class Tree extends JPanel {
    private final double fraction;
    private final int smallestBranch;
    private final double branchAngle;
    private final int trunkLength;
    
    public Tree(double f, int sb, double ba, int tl) {
        fraction = f;
        smallestBranch = sb;
        branchAngle = ba;
        trunkLength = tl;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics window) {
        window.setColor(Color.GREEN);

        //trunk
        DrawTree(window,trunkLength,300,500,0);
        //branches
        TreeLogic(window, trunkLength, 300, 500-trunkLength,0);
    }

    public void DrawTree(Graphics window, int branchLength, int x1, int y1, double angle) {
        //turns length and angle into secondary points and then draws a branch
        window.setColor(Color.green);
        int x2 = (int) (x1 + branchLength*Math.sin(angle));
        int y2 = (int) (y1-branchLength*Math.cos(angle));
        window.drawLine(x1, y1, x2, y2);
    }

    public void Smiley(Graphics window, int radius, int x, int y) {
        window.setColor(Color.yellow);
        window.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        window.setColor(Color.black);
        window.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        //eyes
        window.fillOval(x - radius / 2, y - radius / 2, radius / 3, radius / 3);
        window.fillOval(x + radius / 2 - radius / 3, y - radius / 2, radius / 3, radius / 3);
        window.drawArc(x - radius / 2, y, radius, radius / 2, -20, -140);
        }
    
    public void TreeLogic(Graphics window, int len, int x, int y, double angle) {
        //calculates angles and adds in randomness to angle and length 
        
        //+ Math.random()/2 - .25
        double angle1 = angle+1.5*branchAngle;
        double angle2 = angle-.75*branchAngle;
        //(Math.random()/2+1.50)*
        double fract = 1.5*fraction;
        DrawTree(window, (int) (len/fract),x,y,angle1);
        DrawTree(window, (int) (len/fract),x,y,angle2);
        //recursive call to branches until minimum branch size is met
        if(len/fraction > smallestBranch){
            TreeLogic(window, (int) (len/fract), (int) (x + len*Math.sin(angle1)/fract),(int) (y-len*Math.cos(angle1)/fract), angle1);
            TreeLogic(window, (int) (len/fract), (int) (x + len*Math.sin(angle2)/fract),(int) (y-len*Math.cos(angle2)/fract), angle2);

        } else{ //adds a smiley face "fruit" to the end of the last branches
            Smiley(window, len/4,(int) (x + len*Math.sin(angle1)/fract),(int) (y-len*Math.cos(angle1)/fract));
            Smiley(window, len/4,(int) (x + len*Math.sin(angle2)/fract),(int) (y-len*Math.cos(angle2)/fract));
            
        }
    }

}
