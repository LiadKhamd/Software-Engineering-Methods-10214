package il.ac.afeka.mvc.views.button;

import il.ac.afeka.input.Mouse;
import il.ac.afeka.mvc.Controller;

public class ButtonController extends Controller {
	
	boolean respondedToPress = false;
	
	public void controlActivity() {
		super.controlActivity();
				
		if (Mouse.instance().button1Pressed() && !respondedToPress) {
			((ButtonModel)getModel()).flip();
			respondedToPress = true;
		}
		else if (!Mouse.instance().button1Pressed()) {
			respondedToPress = false;
		}
		
	}

}
