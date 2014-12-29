/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Oct/23/2014
 * Last Updated:	Nov/14/2014
 * 
 * Called by the IntroGUI class when the button is launched and it 
 * launches 2 different classes for the game
 ------------------------------------------------------------------*/
package game;

import backgroundProcesses.Time;


public class Start {
	//create instances of the Ghost and Busters classes
	public static int keyBindCounter = 0, keyBindsCounter = 0;
	
	Ghost ghost;
	Busters busters;
	public Start(int players){
		//launch the busters class which launches its own frame and mirrors the Ghost class to an extent
		busters = new Busters(players-1);
		//launch the Ghost class which launches its own frame and spawns player in each corner
		ghost = new Ghost(players);
		//launch the class that controls the time
		new Time();
	}

}
