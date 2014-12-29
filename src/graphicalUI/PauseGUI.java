/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Nov/14/2014
 * Last Updated:	Dec/28/2014
 * 
 * Creates the Pause Menu which is shown when the Escape button is 
 * pressed
 ------------------------------------------------------------------*/
package graphicalUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backgroundProcesses.Time;

public class PauseGUI extends JFrame{
	JPanel panel = new JPanel();
	JButton resume = new JButton("Resume");
	JButton controls = new JButton ("Controls");
	JButton howToPlay = new JButton ("How To Play");
	JLabel timeLeft = new JLabel ();
	
	public PauseGUI(){
		//stop the time
		Time.stopTime();
		showTimeLeft();	//display how much time is left
		
		//add actionlistener for the resume button which hides the window
		resume.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);	//hide the frame
				GhostGUI.frame.requestFocus();	//set the focus on the ghost screen
				Time.restartTime();	//start the time again
			}
		});
		//add actionlistener for the controls button that displays the controls frame
		controls.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showControls();
			}
		});
		//add actionlistener for the hotToPlay button and display the rules frame
		howToPlay.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showRules();
			}
		});
		
		panel.add(timeLeft);
		panel.add(controls);
		panel.add(howToPlay);
		panel.add(resume);
		this.add(panel);

		this.setSize(100, 160);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	/**
	 * sets the text for the time left
	 */
	public void showTimeLeft(){
		timeLeft.setText("Time Left: " + GhostGUI.getTimeLeft());
	}
	
	/**
	 * shows the controls frame when the controls button is pressed
	 */
	public void showControls(){
		new ControlsGUI();
	}
	
	/**
	 * displays the frame that shows the rules and how the game is played
	 */
	public void showRules(){
		JFrame frame = new JFrame("How To Play");
		JLabel label = new JLabel();
			label.setText("<html>The rules for the game are simple. Either catch the ghost, or the ghost catches you."
					+ " You can look at the controls from the pause menu, and you can also modify them.\n"
					+ " The players are supposed to survive till the 300 seconds (5 minutes) are up or they can try and kill"
					+ " the ghost.\n The ghost needs to capture all the players before time runs out or they will lose.");
			
		frame.add(label);
		frame.setSize(450, 150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
}
