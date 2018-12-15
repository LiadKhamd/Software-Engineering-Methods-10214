package il.ac.afeka.mvc.views.button;

import java.awt.Color;

import il.ac.afeka.geom.Point;
import il.ac.afeka.geom.Rectangle;
import il.ac.afeka.graphics.Display;
import il.ac.afeka.mvc.View;
import il.ac.afeka.mvc.views.button.ButtonController;
import il.ac.afeka.mvc.views.button.ButtonModel;
import il.ac.afeka.mvc.views.button.ButtonView;

public class ButtonDemo {

	 public static void main(String[] args) {
		 
		 try {
			 View view = new View().windowViewport(new Rectangle(new Point (0.0,  0.0), new Point(1.0,1.0)), new Rectangle(new Point(100.0,100.0), new Point(400.0, 400.0)));
			 view.setBackgroundColor(Color.gray);
			 
			 ButtonView buttonView = (ButtonView)new ButtonView().windowViewport(new Rectangle(new Point(0.0, 0.0), new Point(1.0, 1.0)), new Rectangle(new Point(0.0, 0.75), new Point(0.25,1.0)));
			 buttonView.setBackgroundColor(Color.darkGray);
			 buttonView.setButtonBorderSize(0.10);
			 buttonView.setPressedColor(Color.green);
			 buttonView.setUnpressedColor(Color.gray);
			 buttonView.setController(new ButtonController());
			
			 ButtonModel buttonModel = new ButtonModel();
			 buttonView.setModel(buttonModel);
			 buttonView.getController().setModel(buttonModel);
			 
			 view.addSubview(buttonView);

			 view.display();
			 
			 view.getController().startUp();
			 
			 try { Thread.sleep(1000); } catch(InterruptedException e) {} 

		} finally {
        	Display.instance().close();
        	System.exit(0);
        }
	 }
}
