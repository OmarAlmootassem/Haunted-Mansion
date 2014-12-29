/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Oct/23/2014
 * Last Updated:	Oct/24/2014
 * 
 * Called by the main method. Is used to collect number of players.
 * As well as who is the ghost
 ------------------------------------------------------------------*/
package game;

import graphicalUI.IntroGUI;

public class PlayerInfo {
	//create an instance of the IntroGUI
	IntroGUI gui;
	
	static int PLAYERS;
	public PlayerInfo(){
		//Display the GUI for the player selection screen
		gui = new IntroGUI();
		//get the number of players and launch the game after clicking the button
		PLAYERS = gui.checkClicked();
	}
}
