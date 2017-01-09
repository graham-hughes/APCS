/*
 Graham Hughes
 Mrs. Hemiup
 April 12, 2016
 APCS Second Semester Final Project Original Fractal
 My original fractal is a smiley face with smiley face eyes and a smiley face smile
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class OriginalFractal extends JPanel {

    private final double fraction;
    private final int largestSmiley;
    private final int smallestSmiley;

    public OriginalFractal(double f, int ss, int ls) {
        fraction = f;
        smallestSmiley = ss;
        largestSmiley = ls;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(700, 600));

    }

    @Override
    public void paint(Graphics window) {
        window.setColor(Color.GREEN);

        //Original smiley. For this fractal, the same method controls both
        //recursion and drawing
        Smiley(window, largestSmiley, largestSmiley + 50, largestSmiley + 50);

    }

    //This method controls both recursions and drawing
    public void Smiley(Graphics window, int radius, int x, int y) {
        //draws yellow circle with black outline
        window.setColor(Color.yellow);
        window.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        window.setColor(Color.black);
        window.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        //makes eyes and smile out of more smiley faces if the radius is still big enough
        if (radius > smallestSmiley) {
            //smiley eyes
            Smiley(window, (int) (radius / fraction), x - radius / 2, y - radius / 2);
            Smiley(window, (int) (radius / fraction), x + radius / 2, y - radius / 2);

            //smiley smile (each block of two is a mirrored set of smiley faces)
            Smiley(window, (int) (radius / (3 * fraction)), (int) (x - radius / 2.1), y + radius / 3);
            Smiley(window, (int) (radius / (3 * fraction)), (int) (x + radius / 2.1), y + radius / 3);

            Smiley(window, (int) (radius / (3 * fraction)), (int) (x - radius / 2.7), (int) (y + radius / 2.2));
            Smiley(window, (int) (radius / (3 * fraction)), (int) (x + radius / 2.7), (int) (y + radius / 2.2));

            Smiley(window, (int) (radius / (3 * fraction)), (int) (x - radius / 4.3), (int) (y + radius / 1.9));
            Smiley(window, (int) (radius / (3 * fraction)), (int) (x + radius / 4.3), (int) (y + radius / 1.9));

            Smiley(window, (int) (radius / (3 * fraction)), (int) (x - radius / 12), (int) (y + radius / 1.8));
            Smiley(window, (int) (radius / (3 * fraction)), (int) (x + radius / 12), (int) (y + radius / 1.8));

            //if this is the last set of smiley faces, gives them regular black eyes and a black smile
        } else {
            window.fillOval(x - radius / 2, y - radius / 2, radius / 3, radius / 3);
            window.fillOval(x + radius / 2 - radius / 3, y - radius / 2, radius / 3, radius / 3);
            window.drawArc(x - radius / 2, y, radius, radius / 2, -20, -140);
        }
    }

}
