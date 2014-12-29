package backgroundProcesses;

import game.Start;
import graphicalUI.ControlsGUI;
import graphicalUI.GhostGUI;

public class KeyBinds {
	
	final static int P_SPEED = 2;	//player speed (pixels)
	final static int G_SPEED = 3;	//ghost speed (pixels)
	final static int STOP = 0;		//stopped speed
	
	static KeyboardAnimation animation = null, animation1 = null, animation2 = null, animation3 = null, animation4 = null;
	
	public KeyBinds (){
	}
	
	/**
	 * Method to assign what each keyboard button does
	 */
	public static void keyboardMovements(){
		if (Start.keyBindsCounter == 0){
			animation = new KeyboardAnimation(GhostGUI.p1, 24);
			animation.addAction("LEFT", -P_SPEED, 0);
			animation.addAction("RIGHT", P_SPEED, 0);
			animation.addAction("UP", 0,-P_SPEED);
			animation.addAction("DOWN", 0, P_SPEED);
			
			animation1 = new KeyboardAnimation(GhostGUI.p2, 24);
			animation1.addAction("A", -P_SPEED, 0);
			animation1.addAction("D", P_SPEED, 0);
			animation1.addAction("W", 0,-P_SPEED);
			animation1.addAction("S", 0, P_SPEED);
			
			animation2 = new KeyboardAnimation(GhostGUI.p3, 24);
			animation2.addAction("J", -P_SPEED, 0);
			animation2.addAction("L", P_SPEED, 0);
			animation2.addAction("I", 0,-P_SPEED);
			animation2.addAction("K", 0, P_SPEED);
			
			animation3 = new KeyboardAnimation(GhostGUI.p4, 24);
			animation3.addAction("F", -P_SPEED, 0);
			animation3.addAction("H", P_SPEED, 0);
			animation3.addAction("T", 0,-P_SPEED);
			animation3.addAction("G", 0, P_SPEED);
			
			animation4 = new KeyboardAnimation(GhostGUI.ghost, 24);
			animation4.addAction("NUMPAD4", -G_SPEED, 0);
			animation4.addAction("NUMPAD6", G_SPEED, 0);
			animation4.addAction("NUMPAD8", 0,-G_SPEED);
			animation4.addAction("NUMPAD5", 0, G_SPEED);
			//increase the counter to allow to update the controls
			Start.keyBindsCounter++;
		}
		else{
			animation = new KeyboardAnimation(GhostGUI.p1, 24);
			animation.addAction(ControlsGUI.p1LEFT.getText(), -P_SPEED, 0);
			animation.addAction(ControlsGUI.p1RIGHT.getText(), P_SPEED, 0);
			animation.addAction(ControlsGUI.p1UP.getText(), 0,-P_SPEED);
			animation.addAction(ControlsGUI.p1DOWN.getText(), 0, P_SPEED);
			
			animation1 = new KeyboardAnimation(GhostGUI.p2, 24);
			animation1.addAction(ControlsGUI.p2LEFT.getText(), -P_SPEED, 0);
			animation1.addAction(ControlsGUI.p2RIGHT.getText(), P_SPEED, 0);
			animation1.addAction(ControlsGUI.p2UP.getText(), 0,-P_SPEED);
			animation1.addAction(ControlsGUI.p2DOWN.getText(), 0, P_SPEED);
			
			animation2 = new KeyboardAnimation(GhostGUI.p3, 24);
			animation2.addAction(ControlsGUI.p3LEFT.getText(), -P_SPEED, 0);
			animation2.addAction(ControlsGUI.p3RIGHT.getText(), P_SPEED, 0);
			animation2.addAction(ControlsGUI.p3UP.getText(), 0,-P_SPEED);
			animation2.addAction(ControlsGUI.p3DOWN.getText(), 0, P_SPEED);
			
			animation3 = new KeyboardAnimation(GhostGUI.p4, 24);
			animation3.addAction(ControlsGUI.p4LEFT.getText(), -P_SPEED, 0);
			animation3.addAction(ControlsGUI.p4RIGHT.getText(), P_SPEED, 0);
			animation3.addAction(ControlsGUI.p4UP.getText(), 0,-P_SPEED);
			animation3.addAction(ControlsGUI.p4DOWN.getText(), 0, P_SPEED);
			
			animation4 = new KeyboardAnimation(GhostGUI.ghost, 24);
			animation4.addAction(ControlsGUI.ghostLEFT.getText(), -G_SPEED, 0);
			animation4.addAction(ControlsGUI.ghostRIGHT.getText(), G_SPEED, 0);
			animation4.addAction(ControlsGUI.ghostUP.getText(), 0,-G_SPEED);
			animation4.addAction(ControlsGUI.ghostDOWN.getText(), 0, G_SPEED);
		}
	}
	
	/**
	 * stops the player from moving
	 * @param player
	 */
	public static void stopMovement(ImagePanel player){
		if (Start.keyBindsCounter ==  1){
			if (player == GhostGUI.p1){
				System.out.println("Player 1");
				animation.addAction("LEFT", STOP, STOP);
				animation.addAction("RIGHT", STOP, STOP);
				animation.addAction("UP", STOP, STOP);
				animation.addAction("DOWN", STOP, STOP);
			}
			else if (player == GhostGUI.p2){
				animation1.addAction("A", STOP, STOP);
				animation1.addAction("D", STOP, STOP);
				animation1.addAction("W", STOP, STOP);
				animation1.addAction("S", STOP, STOP);
			}
			else if (player == GhostGUI.p3){
				animation2.addAction("J", STOP, STOP);
				animation2.addAction("L", STOP, STOP);
				animation2.addAction("I", STOP, STOP);
				animation2.addAction("K", STOP, STOP);
			}
			else if (player == GhostGUI.p4){
				animation3.addAction("F", STOP, STOP);
				animation3.addAction("H", STOP, STOP);
				animation3.addAction("T", STOP, STOP);
				animation3.addAction("G", STOP, STOP);
			}	
		}
		else{
			if (player == GhostGUI.p1){
				System.out.println("Player 1");
				animation.addAction(ControlsGUI.p1LEFT.getText(), STOP, STOP);
				animation.addAction(ControlsGUI.p1RIGHT.getText(), STOP, STOP);
				animation.addAction(ControlsGUI.p1UP.getText(), STOP, STOP);
				animation.addAction(ControlsGUI.p1DOWN.getText(), STOP, STOP);
			}
			else if (player == GhostGUI.p2){
				animation1.addAction(ControlsGUI.p2LEFT.getText(), STOP, STOP);
				animation1.addAction(ControlsGUI.p2RIGHT.getText(), STOP, STOP);
				animation1.addAction(ControlsGUI.p2UP.getText(), STOP, STOP);
				animation1.addAction(ControlsGUI.p2DOWN.getText(), STOP, STOP);
			}
			else if (player == GhostGUI.p3){
				animation2.addAction(ControlsGUI.p3LEFT.getText(), STOP, STOP);
				animation2.addAction(ControlsGUI.p3RIGHT.getText(), STOP, STOP);
				animation2.addAction(ControlsGUI.p3UP.getText(), STOP, STOP);
				animation2.addAction(ControlsGUI.p3DOWN.getText(), STOP, STOP);
			}
			else if (player == GhostGUI.p4){
				animation3.addAction(ControlsGUI.p4LEFT.getText(), STOP, STOP);
				animation3.addAction(ControlsGUI.p4RIGHT.getText(), STOP, STOP);
				animation3.addAction(ControlsGUI.p4UP.getText(), STOP, STOP);
				animation3.addAction(ControlsGUI.p4DOWN.getText(), STOP, STOP);
			}
		}
	}
	
	/**
	 * Restarts movement after player is revived
	 * @param player
	 */
	public static void restartMovement(ImagePanel player){
		if (Start.keyBindsCounter ==  1){
			if (player == GhostGUI.p1){
				System.out.println("Player 1");
				animation.addAction("LEFT", -P_SPEED, 0);
				animation.addAction("RIGHT", P_SPEED, 0);
				animation.addAction("UP", 0, -P_SPEED);
				animation.addAction("DOWN", 0, P_SPEED);
			}
			else if (player == GhostGUI.p2){
				animation1.addAction("A", -P_SPEED, 0);
				animation1.addAction("D", P_SPEED, 0);
				animation1.addAction("W", 0, -P_SPEED);
				animation1.addAction("S", 0, P_SPEED);
			}
			else if (player == GhostGUI.p3){
				animation2.addAction("J", -P_SPEED, 0);
				animation2.addAction("L", P_SPEED, 0);
				animation2.addAction("I", 0, -P_SPEED);
				animation2.addAction("K", 0, P_SPEED);
			}
			else if (player == GhostGUI.p4){
				animation3.addAction("F", -P_SPEED, 0);
				animation3.addAction("H", P_SPEED, 0);
				animation3.addAction("T", 0, -P_SPEED);
				animation3.addAction("G", 0, P_SPEED);
			}	
		}
		else{
			if (player == GhostGUI.p1){
				System.out.println("Player 1");
				animation.addAction(ControlsGUI.p1LEFT.getText(), -P_SPEED, 0);
				animation.addAction(ControlsGUI.p1RIGHT.getText(), P_SPEED, 0);
				animation.addAction(ControlsGUI.p1UP.getText(), 0, -P_SPEED);
				animation.addAction(ControlsGUI.p1DOWN.getText(), 0, P_SPEED);
			}
			else if (player == GhostGUI.p2){
				animation1.addAction(ControlsGUI.p2LEFT.getText(), -P_SPEED, 0);
				animation1.addAction(ControlsGUI.p2RIGHT.getText(), P_SPEED, 0);
				animation1.addAction(ControlsGUI.p2UP.getText(), 0, -P_SPEED);
				animation1.addAction(ControlsGUI.p2DOWN.getText(), 0, P_SPEED);
			}
			else if (player == GhostGUI.p3){
				animation2.addAction(ControlsGUI.p3LEFT.getText(), -P_SPEED, 0);
				animation2.addAction(ControlsGUI.p3RIGHT.getText(), P_SPEED, 0);
				animation2.addAction(ControlsGUI.p3UP.getText(), 0, -P_SPEED);
				animation2.addAction(ControlsGUI.p3DOWN.getText(), 0, P_SPEED);
			}
			else if (player == GhostGUI.p4){
				animation3.addAction(ControlsGUI.p4LEFT.getText(), -P_SPEED, 0);
				animation3.addAction(ControlsGUI.p4RIGHT.getText(), P_SPEED, 0);
				animation3.addAction(ControlsGUI.p4UP.getText(), 0, -P_SPEED);
				animation3.addAction(ControlsGUI.p4DOWN.getText(), 0, P_SPEED);
			}
		}
	}

}
