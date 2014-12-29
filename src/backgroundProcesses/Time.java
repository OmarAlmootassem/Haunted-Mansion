/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Nov/14/2014
 * Last Updated:	Nov/14/2014
 * 
 * A background process that counts down the time until it reaches 0
 * then launches the end screen
 ------------------------------------------------------------------*/
package backgroundProcesses;

import graphicalUI.BustersGUI;
import graphicalUI.GhostGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Time {
	
	public static Timer timer;
	
	public Time(){
		//start a timer with a 1 second delay that changes the time till it reaches 0
		timer = new Timer(1000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//change the time on both labels in both frames
				GhostGUI.timeLabel.setText(String.valueOf(Integer.parseInt(GhostGUI.timeLabel.getText()) - 1));
				BustersGUI.timeLabel.setText(String.valueOf(Integer.parseInt(BustersGUI.timeLabel.getText()) - 1));	
				
				//if time reaches 0 seconds, stop the timer and show the end screen
				if (Integer.parseInt(GhostGUI.timeLabel.getText()) == 0){
					timer.stop();
					//show end screen
				}	
			}
		});
		//make the delay 1000ms(1s)
		timer.setDelay(1000);
		timer.start();	//start the timer
	}
	
	/**
	 * stops the timer
	 */
	public static void stopTime(){
		timer.stop();
	}
	
	/**
	 * restarts the timer
	 */
	public static void restartTime(){
		timer.start();
	}

}
