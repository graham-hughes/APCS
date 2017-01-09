/*
 Graham Hughes
 Mrs. Hemiup
 April 12, 2016
 APCS Second Semester Final Project Sierpinski's Carpet 
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class Carpet extends JPanel {
    //minimum square size
    private final int minimumSize;
    //initial square dimensions array 
    private final int[] initialSquare;

    public Carpet(int min) {
        //sets initial square dimensions: x, y, length
        initialSquare = new int[]{100, 100, 360}; 
        //sets minimum square size
        minimumSize = min;

        //sets the background of the JPanel to white, and sets its dimensions
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
    }

    //paints the JPanel
    @Override
    public void paint(Graphics window) {
        //Draws initial large square (uses initialSquare array for dimensions, these can be
        //changed in the constructor) in blue
        window.setColor(Color.BLUE);
        window.fillRect(initialSquare[0], initialSquare[1], initialSquare[2], initialSquare[2]);
        
        //changes color to white and starts the Sierpinski carpet method, based on
        //the dimensions of the original square
        window.setColor(Color.white);
        sierpinski(window, initialSquare[0], initialSquare[1], initialSquare[2]);
    }

        //draws a smiley face
    public void smiley(Graphics window, int radius, int x, int y) {
        //face and outline
        window.setColor(Color.yellow);
        window.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        window.setColor(Color.black);
        window.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        //eyes
        window.fillOval(x - radius / 2, y - radius / 2, radius / 3, radius / 3);
        window.fillOval(x + radius / 2 - radius / 3, y - radius / 2, radius / 3, radius / 3);
        //smile
        window.drawArc(x - radius / 2, y, radius, radius / 2, -20, -140);
    }
    
    public void square(Graphics window, int x, int y, int length) {
        //draws a white square of length length with its upper left hand corner at x,y
        window.setColor(Color.white);
        //uncomment the following line to add in the white squares if you want. Otherwise, 
        //I think it looks better with smiley faces
        //window.fillRect(x, y, length, length);
        //calls a smiley for the middle of the square
        smiley(window, length/2,x+length/2,y+length/2);
    }

    public void sierpinski(Graphics window, int x, int y, long len) {
        //Draws the middle square
        square(window, (int) (x + len / 3), (int) (y + len / 3), (int) (len / 3));

        if (len < minimumSize) {
        } else {
            //recursively calls Sierpinski for the eight squares around the center
            //from left to right:
            //top
            sierpinski(window, x, y, len / 3);
            sierpinski(window, (int) (x + len / 3), y, len / 3);
            sierpinski(window, (int) (x + 2 * len / 3), y, len / 3);
            //middle
            sierpinski(window, x, (int) (y + len / 3), len / 3);
            sierpinski(window, (int) (x + 2 * len / 3), (int) (y + len / 3), len / 3);
            //bottom
            sierpinski(window, x, (int) (y + 2 * len / 3), len / 3);
            sierpinski(window, (int) (x + len / 3), (int) (y + 2 * len / 3), len / 3);
            sierpinski(window, (int) (x + 2 * len / 3), (int) (y + 2 * len / 3), len / 3);
        }
    }

}
