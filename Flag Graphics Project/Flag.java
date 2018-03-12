package completed;
/* Flag project
 * Nisha McNealis
 * AP CS Period 3
 * 8/31/16
 */
import java.awt.*;
import java.util.Scanner;
import javax.swing.JFrame;

public class Flag extends JFrame{
	//the real flag colors
	private final Color DARKRED = new Color(191, 10, 48);
	private final Color DARKBLUE = new Color(0, 40, 104);
	//used for stars
	int [] xc = new int[11];
	int [] yc = new int[11];
	//Used for scale
	int scale; 
	double width;
	double height;

	public Flag(){
		init();
	}
	
	public void init(){
		setSize(600,600);
		setBackground(Color.BLACK);
		scale();
		repaint();
	}
	//Setting the scale
	public void scale(){
		Scanner input = new Scanner( System.in ); // from http://www.homeandlearn.co.uk/java/user_input.html
		System.out.print("Enter a scale factor between 200 and 500: "); //these are the sizes that look the best
		scale = input.nextInt();
	}

public void paint(Graphics g) {

	//OUTLINE
	g.setColor(Color.BLACK);
	g.drawRect(0, 100, (int)(1.9 * scale), scale);
	//g.drawRect(0, 100, (int)(0.5385 * scale), (int)(0.76 * scale));
	
	//UNION BACKGROUND
	g.setColor(DARKBLUE);
	g.fillRect(0, 100, (int)(0.76 * scale), (int)(0.5385 * scale));

	Stripes(g);	
	StarCode(g);

}
	public void Stripes(Graphics g){
		//THE STRIPES
		//Dimensions of stripes
		int x = (int)(0.76 * scale);
		int y = 100;
		int w = (int)(1.14 * scale);
		int h = (int)(0.077 * scale);
		int counter = 0;
			g.setColor(DARKRED);
			for (int i = 0; i < 13; i++){
				g.fillRect(x, y, w, h);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, w, h);
				y += (int)(0.077 * scale);
				counter ++;
				if (counter % 2 == 0){
					g.setColor(DARKRED);
				}
				else{
					g.setColor(Color.WHITE);
				}
				if (counter == 7){
					w = (int)(1.9 * scale);
					x = 0;
				}
			}
	}
	public void StarCode(Graphics g){
		//THE STARS
  		//Counters
  		int c = 0;
  		//offset
  		int xs = (int)(0.0533 * scale);
  		int ys =  115 + (int)(0.0033 * scale);
  		//Rotation
  		int r = (int)(Math.round(Math.PI * 5));

  		g.setColor(Color.WHITE);
  		for (int j = 0; j < 50; j++){
  			for (int k = 0; k <=10; k +=2){
  				double temp = Math.cos(2 * Math.PI / 5 * k + r);
  				int temp2 = (int)(Math.round(temp * 0.0315 * scale));
  				xc[k] = temp2 +xs;
  			}
  			for (int k = 1; k <=10; k +=2){
  				xc[k] = (int) (Math.round(Math.cos(2 * Math.PI / 5 * k + r) * 0.01575 * scale)+xs);

  			}
  			
  			System.out.println("\n");
  			
  			for (int n = 0; n <=10; n +=2){
  				double temp = (Math.round(Math.sin(2 * Math.PI / 5 * n + r) * 0.0315 * scale));
  				yc[n] = (int) (temp + ys);
  			}
  			for (int m = 1; m <=10; m +=2){
  				double temp = (Math.round(Math.sin(2 * Math.PI / 5 * m + r) * 0.01575 * scale));
  				yc[m] = (int) (temp + ys);
  			}
  			g.fillPolygon(xc, yc, 10);

  			int [] px = new int[6];
  			int [] py = new int[6];
  			for (int s = 0; s < 11; s+=2){
  				px[s/2] = xc[s];
  				py[s/2] = yc[s];
  			}
  			
  			g.setColor(Color.white);
  			g.fillOval((int)(Math.round(xs - 0.018 * scale)), (int)((Math.round(ys - 0.016 * scale))), (int)((0.03 * scale) + (scale / 200)), (int)(0.03 * scale) + (scale / 200));
  		
  			c ++;
  			xs += (int)(0.13 * scale);
  			if (c == 6){
  				ys += (int)(0.0533 * scale);
  				xs = (int)(0.12 * scale);
  			}
  			if (c == 11){
  				ys += (int)(0.0533 * scale);
  				xs = (int)(0.0533 * scale);
  				c = 0;
  			}
  			
  			g.setColor(Color.white);
  			if (j != 49){
  			g.fillOval((int)(Math.round(xs - 4)), (int)(Math.round(ys - 4)), (int)(0.03 * scale), (int)(0.03 * scale));
  			}
	}
	}
}
