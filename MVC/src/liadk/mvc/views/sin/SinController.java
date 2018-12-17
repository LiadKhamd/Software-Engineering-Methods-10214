package liadk.mvc.views.sin;

import il.ac.afeka.geom.Point;
import il.ac.afeka.input.Mouse;
import il.ac.afeka.mvc.Controller;

public class SinController extends Controller {

	public void controlActivity() {
		super.controlActivity();

		if (Mouse.instance().button1Pressed()) {

			Point origin = Mouse.instance().cursorPoint();
			Point delta;
			double f = 1;
			while (Mouse.instance().button1Pressed()) {
				delta = Mouse.instance().cursorPoint().subtract(origin);
				f = Math.abs(Math.min(delta.getX(), delta.getY()));
				
				((SinView) getView()).setFreq(f);
			}


			getView().getTopView().display();

			getView().getTopView().display();

			getView().display();
		}

	}

}
