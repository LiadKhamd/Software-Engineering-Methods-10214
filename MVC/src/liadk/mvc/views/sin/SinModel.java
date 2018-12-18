package liadk.mvc.views.sin;

import java.util.ArrayList;

import il.ac.afeka.geom.Point;
import il.ac.afeka.mvc.Model;

public class SinModel extends Model {

	private double frequency;

	public SinModel() {
		super();
		frequency = 1;
	}

	public void SetFrequency(double f) {
		if (f < 10) {
			frequency = f;
			notifyViews();
		}
	}

	public ArrayList<Point> getFuncCalc(int start, int end) {
		ArrayList<Point> p = new ArrayList<>();
		for (double i = start; i <= end; i += 0.02) {
			p.add(new Point((double) i,  Math.sin(frequency * i)));
		}
		return p;
	}

}
