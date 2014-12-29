/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Dec/28/2014
 * Last Updated:	Dec/28/2014
 * 
 * Called when the game finishes if the ghost wins
 ------------------------------------------------------------------*/

package game;

import graphicalUI.BustersGUI;
import graphicalUI.GhostGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GhostWin {
	
	JFrame frame = new JFrame ("Ghost Wins");
	JPanel panel = new JPanel ();
	
	JLabel label = new JLabel("The Ghost Wins The Game!");
	JButton exit = new JButton ("Exit");
	
	public GhostWin(){
		//close all other windows
		BustersGUI.frame.dispose();
		GhostGUI.frame.dispose();
		
		//set the title shown on top
		label.setFont(new Font("Serif", Font.PLAIN, 25));
		
		
		//add actionListener for exit Button
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//close window
				frame.dispose();
				System.exit(0);
				}
			});
		
		
		frame.add(panel);
		panel.add(label);
		panel.add(exit);
		
		frame.setVisible(true);
		frame.setSize(300, 110);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
