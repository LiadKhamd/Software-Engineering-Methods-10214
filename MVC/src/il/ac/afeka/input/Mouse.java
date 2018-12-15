package il.ac.afeka.input;

import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import il.ac.afeka.geom.Point;

public class Mouse implements MouseListener {

	private static Mouse theMouse;

	public static Mouse instance() {
		if (theMouse == null)
			theMouse = new Mouse();
		return theMouse;
	}

	private boolean button1Pressed;
	private boolean button2Pressed;
	private boolean button3Pressed;
	
	public boolean button1Pressed() { return button1Pressed; }
	public boolean button2Pressed() { return button2Pressed; }
	public boolean button3Pressed() { return button3Pressed; }
	
	public Point cursorPoint() {
		
		PointerInfo pointerInfo = MouseInfo.getPointerInfo();
		return new Point(pointerInfo.getLocation().getX(), pointerInfo.getLocation().getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			button1Pressed = true;
		if (e.getButton() == MouseEvent.BUTTON2)
			button2Pressed = true;
		if (e.getButton() == MouseEvent.BUTTON3)
			button3Pressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			button1Pressed = false;
		if (e.getButton() == MouseEvent.BUTTON2)
			button2Pressed = false;
		if (e.getButton() == MouseEvent.BUTTON3)
			button3Pressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
