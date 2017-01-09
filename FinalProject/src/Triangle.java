/*
 Graham Hughes
 Mrs. Hemiup
 April 12, 2016
 APCS Second Semester Final Project Tree Fractal
 Smiley Serpienski Triangle
*/

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class Triangle extends JPanel {
    private final int minimumSize;
    
    public Triangle(int min) {
        minimumSize = min;
        
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    public void paint(Graphics window) {
        window.setColor(Color.BLACK);

        sierpinski(window, (long) 400.0, 100, 500);
    }

    //draws an equilateral triangle with a length of len and a leftmost point of x,y
    //and calls a smiley that fits in the center
    public void drawTriangle(Graphics window, long len, int x, int y) {
        window.drawPolygon(new int[]{x,(int)(x+len/2),(int)(x+len)},new int[]{y, (int)(y - len), y},3);
        smiley(window, (int) ((len)/(1+Math.sqrt(5))), (int) (x + len/2), (int) (y - (len)/(1+Math.sqrt(5))));
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

    public void sierpinski(Graphics window, long len, int x, int y) {
        //recursion logic. Draws triangle if minimum size is met, otherwise, otherwise, tries again
        //with smaller triangles
        if (len < minimumSize) {
            drawTriangle(window, len, x, y);

        } else {
            sierpinski(window, len / 2, x, y);
            sierpinski(window, len / 2, (int) (x + len / 2), y);
            sierpinski(window, len / 2, (int) (x + len / 4), (int) (y - len / 2));

        }
    }

}
