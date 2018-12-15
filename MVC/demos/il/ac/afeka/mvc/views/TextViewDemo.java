package il.ac.afeka.mvc.views;

import java.awt.Color;

import il.ac.afeka.geom.Point;
import il.ac.afeka.geom.Rectangle;
import il.ac.afeka.graphics.Display;
import il.ac.afeka.mvc.View;
import il.ac.afeka.mvc.views.TextView;

public class TextViewDemo {


	 public static void main(String[] args) {
		 
		 try {
			 View view = new View().windowViewport(new Rectangle(new Point (0.0,  0.0), new Point(1.0,1.0)), new Rectangle(new Point(100.0,100.0), new Point(400.0, 400.0)));
			 view.setBackgroundColor(Color.gray);
			 
			 TextView textView = new TextView("Hello, world");
			 textView.windowViewport(new Rectangle(new Point(0.0, 0.0), new Point(1.0, 1.0)), new Rectangle(new Point(0.0, 0.0), new Point(1.0,0.125)));
			 textView.setBackgroundColor(Color.yellow);
					 
			 view.addSubview(textView);

			 view.display();
			 
			 view.getController().startUp();
			 
			 try { Thread.sleep(1000); } catch(InterruptedException e) {} 

		} finally {
       	Display.instance().close();
       	System.exit(0);
       }
	 }
}

