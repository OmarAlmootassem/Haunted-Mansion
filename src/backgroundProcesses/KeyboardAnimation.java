/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Oct/29/2014
 * Last Updated:	Oct/29/2014
 * 
 * Class the moves the 2-5 entities around using keyboard input
 ------------------------------------------------------------------*/

package backgroundProcesses;

import graphicalUI.GhostGUI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class KeyboardAnimation implements ActionListener{
	
	private final static int P_HEIGHT = 32;
	private final static int P_WIDTH = 27;
	
	private final static String PRESSED = "pressed ";
	private final static String RELEASED = "released ";
	
	public static int movementDirection1 = 2;
	public static int movementDirection2 = 2;
	public static int movementDirection3 = 2;
	public static int movementDirection4 = 2;

	private JComponent component;
	private Timer timer;
	private Map<String, Point> pressedKeys = new HashMap<String, Point>();

	public KeyboardAnimation(JComponent component, int delay){
		this.component = component;

		timer = new Timer(delay, this);
		timer.setInitialDelay(0);
	}

	/**
	 *  Method that creates an action and adds it to a keyStroke
	 * @param keyStroke - see KeyStroke.getKeyStroke(String) for the format of
	 *                    		 of the String. Except the "pressed|released" keywords
	 *                   	     are not to be included in the string.
	 * @param deltaX
	 * @param deltaY
	 */
	public void addAction(String keyStroke, int deltaX, int deltaY){
		//Separate the key identifier from the modifiers of the KeyStroke
		int offset = keyStroke.lastIndexOf(" ");
		String key = offset == -1 ? keyStroke :  keyStroke.substring(offset + 1);
		String modifiers = keyStroke.replace(key, "");

		//Get the InputMap and ActionMap of the component

		InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = component.getActionMap();

		//Create Action and add binding for the pressed key

		Action pressedAction = new AnimationAction(key, new Point(deltaX, deltaY));
		String pressedKey = modifiers + PRESSED + key;
		KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(pressedKey);
		inputMap.put(pressedKeyStroke, pressedKey);
		actionMap.put(pressedKey, pressedAction);

		//Create Action and add binding for the released key

		Action releasedAction = new AnimationAction(key, null);
		String releasedKey = modifiers + RELEASED + key;
		KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke(releasedKey);
		inputMap.put(releasedKeyStroke, releasedKey);
		actionMap.put(releasedKey, releasedAction);
	}
	
	/**
	 * Method invoked whenever a key is pressed or released
	 * @param key
	 * @param moveDelta
	 */
	private void handleKeyEvent(String key, Point moveDelta){
		//Keep track of which keys are pressed
		if (moveDelta == null)
			pressedKeys.remove(key);
		else
			pressedKeys.put(key, moveDelta);

		//Start the Timer when the first key is pressed
   		if (pressedKeys.size() == 1){
   			timer.start();
   		}

		//Stop the Timer when all keys have been released
   		if (pressedKeys.size() == 0){
   			timer.stop();
   		}
	}
	
	/**
	 * Method invoked when the Timer starts
	 */
	public void actionPerformed(ActionEvent e){
		moveComponent();
	}
	
	/**
	 * Move the component to its new location
	 */
	private void moveComponent(){
		int componentWidth = component.getSize().width;
		int componentHeight = component.getSize().height;

		Dimension parentSize = component.getParent().getSize();
		int parentWidth  = parentSize.width -7;	//-7 to account for left border width
		int parentHeight = parentSize.height - 10;	//-10 to account for the bottom border height

		//Calculate new move

		int deltaX = 0;
		int deltaY = 0;

		for (Point delta : pressedKeys.values()){
			deltaX += delta.x;
			deltaY += delta.y;
		}

		//Determine next X position
		int nextX = Math.max(component.getLocation().x + deltaX, 10);

		if ( nextX + componentWidth > parentWidth){
			nextX = parentWidth - componentWidth;
		}

		//Determine next Y position
		int nextY = Math.max(component.getLocation().y + deltaY, 30);

		if ( nextY + componentHeight > parentHeight){
			nextY = parentHeight - componentHeight;
		}
		
		if (component == GhostGUI.p1){
			//get the movement direction for light position
			if (deltaY < 0)
				movementDirection1 = 1;
			else if (deltaX > 0)
				movementDirection1 = 2;
			else if (deltaY > 0)
				movementDirection1 = 3;
			else if (deltaX < 0)
				movementDirection1 = 4;
			System.out.println("P1 movement Direction: " + movementDirection1);//debug
		}
		else if (component == GhostGUI.p2){
			//get the movement direction for light position
			if (deltaY < 0)
				movementDirection2 = 1;
			else if (deltaX > 0)
				movementDirection2 = 2;
			else if (deltaY > 0)
				movementDirection2 = 3;
			else if (deltaX < 0)
				movementDirection2 = 4;
			System.out.println("P2 movement Direction: " + movementDirection2);//debug
		}
		else if (component == GhostGUI.p3){
			//get the movement direction for light position
			if (deltaY < 0)
				movementDirection3 = 1;
			else if (deltaX > 0)
				movementDirection3 = 2;
			else if (deltaY > 0)
				movementDirection3 = 3;
			else if (deltaX < 0)
				movementDirection3 = 4;
			System.out.println("P3 movement Direction: " + movementDirection3);//debug
		}
		else if (component == GhostGUI.p4){
			//get the movement direction for light position
			if (deltaY < 0)
				movementDirection4 = 1;
			else if (deltaX > 0)
				movementDirection4 = 2;
			else if (deltaY > 0)
				movementDirection4 = 3;
			else if (deltaX < 0)
				movementDirection4 = 4;
			System.out.println("P4 movement Direction: " + movementDirection4);//debug
		}
		
   		/*
   		 * Bounds for the movement to stop when it hits any of the walls
   		 */
   		if (component.getX() + P_WIDTH >= 50 && component.getX() <= 60 && component.getY() + P_HEIGHT >= 72 && component.getY() <= 132){	//wall1
   			if (component.getX() >= 55)
   				nextX = 62;
   			else if (component.getX() < 55)
   				nextX = 48 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 50 && component.getX() <= 87 && component.getY() + P_HEIGHT >= 132 && component.getY() <= 142){	//wall2
   			if (component.getY() >= 137)
   				nextY = 144;
   			else if (component.getY() < 137)
   				nextY = 129 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 87 && component.getX() <= 97 && component.getY() + P_HEIGHT>= 132 && component.getY() <= 157){	//wall3
   			if (component.getX() >= 92)
   				nextX = 99;
   			else if (component.getX() < 92)
   				nextX = 85 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 87 && component.getX() <= 102 && component.getY() + P_HEIGHT >= 157&& component.getY() <= 167){	//wall4
   			if (component.getY() >= 162)
   				nextY = 169;
   			else if (component.getY() < 162)
   				nextY = 155 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 142 && component.getX() <= 157 && component.getY() + P_HEIGHT >= 157 && component.getY() <= 167){	//wall5
   			if (component.getY() >= 162)
   				nextY = 169;
   			else if (component.getY() < 162)
   				nextY = 155 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 147 && component.getX() <= 157 && component.getY() + P_HEIGHT >= 85 && component.getY() <= 160){	//wall6
   			if (component.getX() >= 152)
   				nextX = 159;
   			else if (component.getX() < 152)
   				nextX = 145 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 147 && component.getX() <= 157 && component.getY() + P_HEIGHT >= 20 && component.getY() <= 45){	//wall7
   			if (component.getX() >= 152)
   				nextX = 159;
   			else if (component.getX() < 152)
   				nextX = 145 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 147 && component.getX() <= 157 && component.getY() + P_HEIGHT >= 207 && component.getY() <= 244){	//wall8
   			if (component.getX() >= 152)
   				nextX = 159;
   			else if (component.getX() < 152)
   				nextX = 145 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 147 && component.getX() <= 157 && component.getY() + P_HEIGHT >= 284 && component.getY() <= 339){	//wall9
   			if (component.getX() >= 152)
   				nextX = 159;
   			else if (component.getX() < 152)
   				nextX = 145 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 122 && component.getX() <= 147 && component.getY() + P_HEIGHT >= 284 && component.getY() <= 294){	//wall10
   			if (component.getY() >=289)
   				nextY = 296;
   			else if (component.getY() < 289)
   				nextY = 282 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 50 && component.getX() <= 82 && component.getY() + P_HEIGHT >= 284 && component.getY() <= 294){	//wall11
   			if (component.getY() >=289)
   				nextY = 296;
   			else if (component.getY() < 289)
   				nextY = 282 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 50 && component.getX() <= 60 && component.getY() + P_HEIGHT >= 232 && component.getY() <= 292){	//wall12
   			if (component.getX() >= 55)
   				nextX = 62;
   			else if (component.getX() < 55)
   				nextX = 48 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 50 && component.getX() <= 107 && component.getY() + P_HEIGHT >= 232 && component.getY() <= 242){	//wall13
   			if (component.getY() >=237)
   				nextY = 244;
   			else if (component.getY() < 237)
   				nextY = 230 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 87 && component.getX() <= 97 && component.getY() + P_HEIGHT >= 207 && component.getY() <= 232){	//wall14
   			if (component.getX() >= 92)
   				nextX = 99;
   			else if (component.getX() < 92)
   				nextX = 85 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 87 && component.getX() <= 152 && component.getY() + P_HEIGHT >= 207 && component.getY() <= 217){	//wall15
   			if (component.getY() >=212)
   				nextY = 219;
   			else if (component.getY() < 212)
   				nextY = 205 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 197 && component.getX() <= 207 && component.getY() + P_HEIGHT >= 20 && component.getY() <= 80){	//wall16
   			if (component.getX() >= 202)
   				nextX = 209;
   			else if (component.getX() < 202)
   				nextX = 195 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 197 && component.getX() <= 207 && component.getY() + P_HEIGHT >= 120 && component.getY() <= 167){	//wall17
   			if (component.getX() >= 202)
   				nextX = 209;
   			else if (component.getX() < 202)
   				nextX = 195 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 197 && component.getX() <= 272 && component.getY() + P_HEIGHT >= 142 && component.getY() <= 152){	//wall18
   			if (component.getY() >=147)
   				nextY = 154;
   			else if (component.getY() < 147)
   				nextY = 140 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 312 && component.getX() <= 387 && component.getY() + P_HEIGHT >= 142 && component.getY() <= 152){	//wall19
   			if (component.getY() >=147)
   				nextY = 154;
   			else if (component.getY() < 147)
   				nextY = 140 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 387 && component.getX() <= 397 && component.getY() + P_HEIGHT >= 20 && component.getY() <= 80){	//wall20
   			if (component.getX() >= 392)
   				nextX = 399;
   			else if (component.getX() < 392)
   				nextX = 385 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 387 && component.getX() <= 397 && component.getY() + P_HEIGHT >= 120 && component.getY() <= 167){	//wall21
   			if (component.getX() >= 392)
   				nextX = 399;
   			else if (component.getX() < 392)
   				nextX = 385 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 387 && component.getX() <= 397 && component.getY() + P_HEIGHT >= 207 && component.getY() <= 337){	//wall22
   			if (component.getX() >= 392)
   				nextX = 399;
   			else if (component.getX() < 392)
   				nextX = 385 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 312 && component.getX() <= 387 && component.getY() + P_HEIGHT >= 286 && component.getY() <= 296){	//wall23
   			if (component.getY() >= 291)
   				nextY = 298;
   			else if (component.getY() < 291)
   				nextY = 284 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 197 && component.getX() <= 272 && component.getY() + P_HEIGHT >= 286 && component.getY() <= 296){	//wall24
   			if (component.getY() >= 291)
   				nextY = 298;
   			else if (component.getY() < 291)
   				nextY = 284 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 197 && component.getX() <= 207 && component.getY() + P_HEIGHT >= 232 && component.getY() <= 288){	//wall25
   			if (component.getX() >= 202)
   				nextX = 209;
   			else if (component.getX() < 202)
   				nextX = 195 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 197 && component.getX() <= 272 && component.getY() + P_HEIGHT >= 232 && component.getY() <= 242){	//wall26
   			if (component.getY() >= 237)
   				nextY = 244;
   			else if (component.getY() < 237)
   				nextY = 230 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 312 && component.getX() <= 387 && component.getY() + P_HEIGHT >= 232 && component.getY() <= 242){	//wall27
   			if (component.getY() >= 237)
   				nextY = 244;
   			else if (component.getY() < 237)
   				nextY = 230 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 577 && component.getX() <= 587 && component.getY() + P_HEIGHT >= 284 && component.getY() <= 339){	//wall28
   			if (component.getX() >= 582)
   				nextX = 589;
   			else if (component.getX() < 582)
   				nextX = 575 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 577 && component.getX() <= 587 && component.getY() + P_HEIGHT >= 207 && component.getY() <= 244){	//wall29
   			if (component.getX() >= 582)
   				nextX = 589;
   			else if (component.getX() < 582)
   				nextX = 575 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 437 && component.getX() <= 577 && component.getY() + P_HEIGHT >= 207 && component.getY() <= 217){	//wall30
   			if (component.getY() >= 212)
   				nextY = 219;
   			else if (component.getY() < 212)
   				nextY = 205 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 437 && component.getX() <= 587 && component.getY() + P_HEIGHT >= 157 && component.getY() <= 167){	//wall31
   			if (component.getY() >= 162)
   				nextY = 169;
   			else if (component.getY() < 162)
   				nextY = 155 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 437 && component.getX() <= 447 && component.getY() + P_HEIGHT >= 70 && component.getY() <= 160){	//wall32
   			if (component.getX() >= 442)
   				nextX = 449;
   			else if (component.getX() < 442)
   				nextX = 435 - P_WIDTH;
   			nextY = component.getY();
   		}else if (component.getX() + P_WIDTH >= 437 && component.getX() <= 462 && component.getY() + P_HEIGHT >= 70 && component.getY() <= 80){	//wall33
   			if (component.getY() >= 75)
   				nextY = 82;
   			else if (component.getY() < 75)
   				nextY = 68 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 502 && component.getX() <= 587 && component.getY() + P_HEIGHT >= 70 && component.getY() <= 80){	//wall34
   			if (component.getY() >= 75)
   				nextY = 82;
   			else if (component.getY() < 75)
   				nextY = 68 - P_HEIGHT;
   			nextX = component.getX();
   		}else if (component.getX() + P_WIDTH >= 577 && component.getX() <= 587 && component.getY() + P_HEIGHT >= 70 && component.getY() <= 120){	//wall35
   			if (component.getX() >= 585)
   				nextX = 589;
   			else if (component.getX() < 582)
   				nextX = 575 - P_WIDTH;
   			nextY = component.getY();
   		}   		
		//Move the component
		component.setLocation(nextX, nextY);
	}
	
	/**
	 * Action to keep track of the key and a Point to represent the movement of
	 * the component. A null Point is specified when the key is released
	 */
	private class AnimationAction extends AbstractAction implements ActionListener{
		private Point moveDelta;

		public AnimationAction(String key, Point moveDelta){
			super(key);

			this.moveDelta = moveDelta;
		}

		public void actionPerformed(ActionEvent e){
			handleKeyEvent((String)getValue(NAME), moveDelta);
		}
	}
}