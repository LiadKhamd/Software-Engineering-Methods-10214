package liadk.mvc.views.sin;

import java.awt.Color;
import java.util.ArrayList;

import il.ac.afeka.geom.Point;
import il.ac.afeka.geom.Rectangle;
import il.ac.afeka.graphics.Display;
import il.ac.afeka.mvc.View;

public class SinView extends View {

	public SinView() {
		super();
		setController(new SinController());
	}

	public void displayView() {

		Rectangle rc = getWindow();
		int sPx = rc.getOrigin().getX().intValue();
		int ePx = rc.getCorner().getX().intValue();

		ArrayList<Point> p = ((SinModel) model).getFuncCalc(sPx, ePx);
		for (Point point : p) {
			Point t = displayTransformation().applyTo(point);
			Display.instance().drawLine(Color.RED, t.getX(), t.getY(), t.getX(), t.getY());
		}
	}
}
