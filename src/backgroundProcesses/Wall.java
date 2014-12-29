/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Oct/25/2014
 * Last Updated:	Oct/25/2014
 * 
 * Method that makes creating walls much easier and takes less lines
 ------------------------------------------------------------------*/
package backgroundProcesses;

import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Wall extends JPanel{
	public Wall (int xLoc, int yLoc, int x, int y){
		//create the JPanel and give it these SET properties
		this.setBackground(Color.DARK_GRAY);
		this.setLocation(xLoc, yLoc);
		this.setSize(x, y);
		
		//System.out.println("x: " + xLoc + "\t y:" + yLoc + "\t xdistance:" + x + "\t yDistance:" + y);
	}

}
