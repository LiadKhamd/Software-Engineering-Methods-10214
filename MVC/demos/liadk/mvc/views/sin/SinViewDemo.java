package liadk.mvc.views.sin;

import java.awt.Color;

import il.ac.afeka.geom.Point;
import il.ac.afeka.geom.Rectangle;
import il.ac.afeka.graphics.Display;
import il.ac.afeka.mvc.View;
import il.ac.afeka.mvc.views.button.ButtonController;
import il.ac.afeka.mvc.views.button.ButtonModel;
import il.ac.afeka.mvc.views.button.ButtonView;

public class SinViewDemo {

	public static void main(String[] args) {
		try {
			View desktop = new View().windowViewport(new Rectangle(new Point(0.0, 0.0), new Point(1.0, 1.0)),
					new Rectangle(new Point(100.0, 100.0), new Point(1000.0, 600.0)));
			desktop.setBackgroundColor(Color.gray);

			SinView s1 = new SinView();

			s1.windowViewport(new Rectangle(new Point(-3 * Math.PI, 2.0), new Point(3 * Math.PI, -2.0)),
					new Rectangle(new Point(0.1, 0.1), new Point(0.5, 0.5)));

			SinModel sinModel = new SinModel();

			s1.setModel(sinModel);

			s1.getController().setModel(sinModel);

			desktop.addSubview(s1);

			SinView s2 = new SinView();

			s2.windowViewport(new Rectangle(new Point(-Math.PI, 1.5), new Point(8 * Math.PI, -1.5)),
					new Rectangle(new Point(0.2, 0.6), new Point(0.8, 0.9)));

			s2.setModel(sinModel);

			s2.getController().setModel(sinModel);

			desktop.addSubview(s2);

			desktop.display();

			desktop.getController().startUp();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

		} finally {
			Display.instance().close();
			System.exit(0);
		}

	}

}
