/*
Graham Hughes
Mrs. Hemiup
November 2, 2015
Lab 8
*/
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Font;
import java.awt.Canvas;

public class Tree extends Canvas
{
	public Tree()
	{
		setBackground(Color.WHITE);
	}

	public void paint( Graphics window )
	{
		window.setColor(Color.RED);
		window.setFont(new Font("TAHOMA",Font.BOLD,12));
		window.drawString("Lab 8B - Tree Lab", 50, 50);
		
		tree(window);
                star(window);
                ornaments(window);
                present(window);
	}
	
	public void tree(Graphics window)
	{
		int[] treeXPoints = {400,300,350,250,300,200,600,500,550,450,500};
		int[] treeYPoints = {100,200,200,300,300,400,400,300,300,200,200};
		Polygon tree = new Polygon(treeXPoints,treeYPoints,treeXPoints.length);
		window.setColor(Color.GREEN);
		window.fillPolygon(tree);
                window.setColor(new Color(156, 93, 82));
                window.fillRect(390, 400, 30, 50);
	}

	public void star(Graphics window)
	{
            int[] starXPoints = {380,390,400, 410,420,412,420,400,380,388};
            int[] starYPoints = {100,100,90 ,100 ,100,112,124,115,124,112};
            Polygon star = new Polygon(starXPoints,starYPoints,starXPoints.length);
            window.setColor(Color.YELLOW);
            window.drawPolygon(star);
            window.fillPolygon(star);
            window.setColor(Color.BLACK);
            window.drawPolygon(star);
	}
        
        public void ornaments(Graphics window){
            int[] ornamentXPoints = {390, 415, 367, 370, 347,408, 256, 520, 497, 350, 400};
            int[] ornamentYPoints = {130, 200, 170, 267, 300, 300, 375, 350, 340, 360, 370};
            Color[] ornamentColors  = {Color.RED,Color.BLUE, Color.PINK, Color.CYAN,Color.RED,Color.BLUE, Color.RED, Color.pink,Color.BLUE, Color.ORANGE, Color.PINK};
            for(int i =0; i<ornamentXPoints.length; i++){
                window.setColor(ornamentColors[i]);
                window.fillOval(ornamentXPoints[i], ornamentYPoints[i], 20, 20);
            }
        }
        
        public void present(Graphics window){
            window.setColor(Color.red);
            window.fillRect(300, 420, 50, 30);
            window.setColor(Color.BLUE);
            window.fillRect(325, 420, 5, 30);
            window.fillRect(300,430, 50, 5);
        }
}