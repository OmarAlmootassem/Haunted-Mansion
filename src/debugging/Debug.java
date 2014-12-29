/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Oct/24/2014
 * Last Updated:	Oct/24/2014
 * 
 * Debug class. can be called by any method to ensure that the
 * expected action is occurring. All the output is printed in a 
 * hidden frame
 ------------------------------------------------------------------*/
package debugging;

import graphicalUI.IntroGUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Debug {
	static int players;
	static Boolean visible;
	static JFrame frame;
	static JTextArea output;
	JScrollPane scroll;
	public Debug(){
		//initialize frame, output and scroll
		frame = new JFrame ("Debug Window");
		output = new JTextArea();		
		scroll = new JScrollPane (output, 
				     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//add scroll to frame	
		frame.add(scroll);
		
		//Frame Defaults
		frame.setSize(395,200);
		frame.setResizable(true);
		frame.setVisible(false);	
	}
	
	/**
	 * called only once, used to determine whether or not to display
	 * the debug window
	 * @param bool
	 */
	public static void showHide(Boolean bool){
		//make frame visible or hidden depending on user input
		if (bool == true){
			frame.setVisible(true);
			output.append("Debug window is visible\n");
		}
		else{
			frame.setVisible(false);
			output.append("Debug window is hidden\n");
		}	
	}

	/**
	 * Checks the number of players the the game starts with
	 */
	public static void gameStarted(){
		players = IntroGUI.getPlayers();
		output.append("Started with " + players + " players\n");	//DEBUGGING	getting number of players and starting the game
	}
}
