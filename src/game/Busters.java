/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Oct/24/2014
 * Last Updated:	Dec/28/2014
 * 
 * Called by the Start class, it launches the BustersGUI and is the 
 * brains behind the busters mechanics
 ------------------------------------------------------------------*/
package game;

import graphicalUI.BustersGUI;

public class Busters {
	static int count = 0;
	
	public Busters(int players){
		//launch the Graphical User Interface
		BustersGUI gui = new BustersGUI(players);
		
	}
	
	/**
	 * displays the end game message
	 * @param result	- if 1: ghost wins
	 * 					- if 2: players win
	 * 					- if 3: time ran out so, players win
	 */
	public static void endGame(int result){
		if (count == 0){
			//ghost win
			if (result == 1){
				new GhostWin();
				count++;
			}
			//player win
			else if (result == 2){
				new PlayersWin();
				count++;
			}
			//time ran out
			else if (result == 3){
				new TimeRanOut();
				count++;
			}
		}
	}
}
