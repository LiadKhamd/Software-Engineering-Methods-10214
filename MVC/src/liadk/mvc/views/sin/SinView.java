package liadk.mvc.views.sin;

import java.awt.Color;
import java.util.ArrayList;

import il.ac.afeka.geom.Point;
import il.ac.afeka.geom.Rectangle;
import il.ac.afeka.graphics.Display;
import il.ac.afeka.mvc.View;

public class SinView extends View {

	private int start, end;

	public SinView(int start, int end) {
		super();
		this.start = start;
		this.end = end;
		setController(new SinController());
	}

	public void displayView() {

		Rectangle rc = displayBox();
		double sPx = rc.getOrigin().getX();
		double sPy = rc.getOrigin().getX();
		double ePx = rc.getCorner().getX();
		double w = rc.getWidth();
		
		Point cen=rc.getCenter();

		ArrayList<Point> p = ((SinModel) model).getFuncCalc(start, end);
//		for (int i = 0; i < p.size(); i++) {
//			if ((sPx + w / 2 + p.get(i).getX()) > sPx && (sPx + w / 2 + p.get(i).getX() < ePx))
//				Display.instance().drawLine(Color.RED, (int) (sPx + w / 2 + p.get(i).getX()),
//						(int) (sPy - p.get(i).getY() + 100), (int) (sPx + w / 2 + p.get(i).getX()),
//						(int) (sPy - p.get(i).getY())+ 100);
//		}
		
		for (int i = 0; i < p.size(); i++) {
		if ((sPx + w / 2 + p.get(i).getX()) > sPx && (sPx + w / 2 + p.get(i).getX() < ePx))
			Display.instance().drawLine(Color.RED, (int) (sPx + w / 2 + p.get(i).getX()),
					(int) (p.get(i).getY() + cen.getY()), (int) (sPx + w / 2 + p.get(i).getX()),
					(int) (p.get(i).getY()+ cen.getY()));
	}
	}

	public void setFreq(double f) {
		((SinModel) model).SetFrequency(f);
	}

}
