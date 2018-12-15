package il.ac.afeka.mvc.views;

import java.awt.Color;

import il.ac.afeka.geom.Point;
import il.ac.afeka.geom.Rectangle;
import il.ac.afeka.graphics.Display;
import il.ac.afeka.mvc.View;
import il.ac.afeka.mvc.views.StandardSystemView;

public class StandardSystemViewDemo {


	 public static void main(String[] args) {
		 
		 try {
			 View desktop = new View().windowViewport(new Rectangle(new Point (0.0,  0.0), new Point(1.0,1.0)), new Rectangle(new Point(100.0,100.0), new Point(800.0, 600.0)));
			 desktop.setBackgroundColor(Color.gray);
			 
			 StandardSystemView systemView = new StandardSystemView("Hello, world");
			 systemView.windowViewport(new Rectangle(new Point(0.0, 0.0), new Point(1.0, 1.0)), new Rectangle(new Point(0.25, 0.25), new Point(0.75,0.75)));
					 
			 desktop.addSubview(systemView);

			 StandardSystemView anotherSystemView = new StandardSystemView("Workspace");

			 anotherSystemView.windowViewport(new Rectangle(new Point(0.0, 0.0), new Point(1.0, 1.0)), new Rectangle(new Point(0.20, 0.20), new Point(0.5,0.8)));
			 
			 anotherSystemView.setBackgroundColor(Color.cyan);
			 
			 desktop.addSubview(anotherSystemView);

			 desktop.display();
			 
			 desktop.getController().startUp();
			 
			 try { Thread.sleep(1000); } catch(InterruptedException e) {} 

		} finally {
       	Display.instance().close();
       	System.exit(0);
       }
	 }
}

