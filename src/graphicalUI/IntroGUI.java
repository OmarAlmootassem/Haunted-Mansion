/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Oct/23/2014
 * Last Updated:	Oct/24/2014
 * 
 * Called by the playerInfo class to build the GUI for the player
 * selection
 ------------------------------------------------------------------*/
package graphicalUI;

import game.Start;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import debugging.Debug;

public class IntroGUI {
	//create instance of Debug class
	Debug debug = new Debug();
	
	static final int MIN_PLAYERS = 2;
	static final int MAX_PLAYERS = 5;
	
	int players;
	
	//create components for the Window
	JFrame introFrame;
	JPanel panel;
	static JSlider playerNumSlider;
	JLabel playerNumLabel;
	JLabel title;
	JLabel note;
	JCheckBox debugVisible;
	JButton start;
	
	/**
	 *  Builds the Graphical User Interface for the intro frame
	 */
	public IntroGUI(){
		//assign values to introFrame and panel
		introFrame = new JFrame("Choose your players");
		panel = new JPanel();
		
		//initialize the playerNumSlider and set values
		playerNumSlider = new JSlider(MIN_PLAYERS, MAX_PLAYERS, 5);
			playerNumSlider.setMajorTickSpacing(1);
			playerNumSlider.setPaintLabels(true);
		//create info labels and button to start the game
		title = new JLabel("Haunted Mansion");
			title.setFont(new Font("", Font.PLAIN, 45));	//make the font bigger 45
		playerNumLabel = new JLabel("Players: ");
		note = new JLabel("One of the players will be the ghost and will have their own window");
		debugVisible = new JCheckBox("Show debug window");
		start = new JButton ("Start");
		
		//add objects to panel and introFrame
		panel.add(title);
		panel.add(playerNumLabel);
		panel.add(playerNumSlider);
		panel.add(debugVisible);
		panel.add(note);
		panel.add(start);
		introFrame.add(panel);
		
		//Frame Defaults
		introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		introFrame.setSize(395,215);
		introFrame.setLocationRelativeTo(null);	//set frame location in middle of screen
		introFrame.setResizable(false);
		introFrame.setVisible(true);
	}
	
	/**
	 * method that returns the number of players in the game
	 * @return the value of the slider
	 */
	public static int getPlayers(){
		return playerNumSlider.getValue();
	}
	
	/**
	 * action listener for the start button
	 * @return the number of players
	 */
	public int checkClicked(){
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				introFrame.setVisible(false); //hide the current Frame
				players = getPlayers();	//get the number of players
				new Start(players);	//launch the game
				
				Debug.showHide(debugVisible.isSelected());
				Debug.gameStarted();	//Debugging
			}
		});
		return players;
	}

}
