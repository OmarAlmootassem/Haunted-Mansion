/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Nov/16/2014
 * Last Updated:	Nov/16/2014
 * 
 * Creates the Controls GUI that allows the players to change them
 ------------------------------------------------------------------*/
package graphicalUI;

import game.Start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import backgroundProcesses.KeyBinds;
import backgroundProcesses.SpringUtilities;

public class ControlsGUI extends JFrame{
	JPanel p, p1, p2, p3;
	JLabel l, text;
	JButton setControls;
	int count = 0;
	
	//strings to save the controls into
	public static String p1Up, p2Up, p3Up, p4Up, ghostUp, 
				p1Down, p2Down, p3Down, p4Down , ghostDown,
				p1Right, p2Right, p3Right, p4Right, ghostRight,
				p1Left, p2Left, p3Left, p4Left, ghostLeft,
				p1Action, p2Action, p3Action, p4Action, ghostAction;
	//TextFields where the control input is
	public static JTextField p1UP, p2UP, p3UP, p4UP, ghostUP,
				p1DOWN, p2DOWN, p3DOWN, p4DOWN, ghostDOWN,
				p1RIGHT, p2RIGHT, p3RIGHT, p4RIGHT, ghostRIGHT,
				p1LEFT, p2LEFT, p3LEFT, p4LEFT, ghostLEFT,
				p1ACTION, p2ACTION, p3ACTION, p4ACTION, ghostACTION;
	
	public ControlsGUI(){
		this.setTitle("Controls");
		
		//show the controls and allow for them to be changed
		addControls();
		
		this.add(p);
		this.setSize(250, 780);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * Method that adds the use interface for the controls GUI
	 */
	public void addControls(){
		p = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		//note for the person changing the controls
		text = new JLabel("<html>You cannot have the same KeyBind <br>for multiple players. When changing <br>the "
				+ "controls, make sure you follow<br> the correct scheme.");
		p3.add(text);
		
		String[] labels = {"Player 1 UP: ", "Player 1 DOWN: ", "Player 1 LEFT: ", "Player 1 RIGHT: ", "Player 1 Action: "
				,"Player 2 UP: ", "Player 2 DOWN: ", "Player 2 LEFT: ", "Player 2 RIGHT: ", "Player 2 Action: "
				,"Player 3 UP: ", "Player 3 DOWN: ", "Player 3 LEFT: ", "Player 3 RIGHT: ", "Player 3 Action: "
				,"Player 4 UP: ", "Player 4 DOWN: ", "Player 4 LEFT: ", "Player 4 RIGHT: ", "Player 4 Action: "
				,"Ghost UP: ", "Ghost DOWN: ", "Ghost LEFT: ", "Ghost RIGHT: ", "Ghost Action: "};
		int numPairs = labels.length;

		//Create and populate the panel.
		p1 = new JPanel(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
		    l = new JLabel(labels[i], JLabel.TRAILING);
		    p.add(l);
		    addTextField(count);
		    count++;
		}
		//Lay out the panel.
		SpringUtilities.makeCompactGrid(p,
		                                numPairs, 2, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad
		setControls = new JButton("Set Controls");
			setControls.setLocation(700, 125);
		p2.add(setControls);
		
		//add actionlistener to setControls button
		setControls.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//save the edited controls
				p1Up = p1UP.getText();
				p1Down = p1DOWN.getText();
				p1Right = p1RIGHT.getText();
				p1Left = p1LEFT.getText();
				p1Action = p1ACTION.getText();
				p2Up = p2UP.getText();
				p2Down = p2DOWN.getText();
				p2Right = p2RIGHT.getText();
				p2Left = p2LEFT.getText();
				p2Action = p2ACTION.getText();
				p3Up = p3UP.getText();
				p3Down = p3DOWN.getText();
				p3Right = p3RIGHT.getText();
				p3Left = p3LEFT.getText();
				p3Action = p3ACTION.getText();
				p4Up = p4UP.getText();
				p4Down = p4DOWN.getText();
				p4Right = p4RIGHT.getText();
				p4Left = p4LEFT.getText();
				p4Action = p4ACTION.getText();
				ghostUp = ghostUP.getText();
				ghostDown = ghostDOWN.getText();
				ghostRight = ghostRIGHT.getText();
				ghostLeft = ghostLEFT.getText();
				ghostAction = ghostACTION.getText();
				
				//change the KeyBinds
				KeyBinds.keyboardMovements();
			}	
		});
		
		//add both panels to the main panel
		p.add(p3);
		p.add(p1);
		p.add(p2);		
	}
	
	/**
	 * method that adds special textFields for each label
	 * @param count
	 */
	public void addTextField(int count){
		if (Start.keyBindCounter == 0){
			switch(count){
			//Case 0 to 4 are for player 1
				case 0:
					p1UP = new JTextField("UP", 10);
				    l.setLabelFor(p1UP);
				    p.add(p1UP);
				    break;
				case 1:    
					p1DOWN = new JTextField("DOWN", 10);
				    l.setLabelFor(p1DOWN);
				    p.add(p1DOWN);
				    break;
				case 2:    
					p1LEFT = new JTextField("LEFT", 10);
				    l.setLabelFor(p1LEFT);
				    p.add(p1LEFT);
				    break;
				case 3:    
					p1RIGHT = new JTextField("RIGHT", 10);
				    l.setLabelFor(p1RIGHT);
				    p.add(p1RIGHT);
				    break;
				case 4:    
					p1ACTION = new JTextField("SLASH", 10);
				    l.setLabelFor(p1ACTION);
				    p.add(p1ACTION);
				    break;
			//Case 5 to 9 are for player 2
				case 5:
					p2UP = new JTextField("W", 10);
				    l.setLabelFor(p2UP);
				    p.add(p2UP);
				    break;
				case 6:    
					p2DOWN = new JTextField("S", 10);
				    l.setLabelFor(p2DOWN);
				    p.add(p2DOWN);
				    break;
				case 7:    
					p2LEFT = new JTextField("A", 10);
				    l.setLabelFor(p2LEFT);
				    p.add(p2LEFT);
				    break;
				case 8:    
					p2RIGHT = new JTextField("D", 10);
				    l.setLabelFor(p2RIGHT);
				    p.add(p2RIGHT);
				    break;
				case 9:    
					p2ACTION = new JTextField("E", 10);
				    l.setLabelFor(p2ACTION);
				    p.add(p2ACTION);
				    break;
			//Case 10 to 14 are for player 3
				case 10:
					p3UP = new JTextField("I", 10);
				    l.setLabelFor(p3UP);
				    p.add(p3UP);
				    break;
				case 11:    
					p3DOWN = new JTextField("K", 10);
				    l.setLabelFor(p3DOWN);
				    p.add(p3DOWN);
				    break;
				case 12:    
					p3LEFT = new JTextField("J", 10);
				    l.setLabelFor(p3LEFT);
				    p.add(p3LEFT);
				    break;
				case 13:    
					p3RIGHT = new JTextField("L", 10);
				    l.setLabelFor(p3RIGHT);
				    p.add(p3RIGHT);
				    break;
				case 14:    
					p3ACTION = new JTextField("O", 10);
				    l.setLabelFor(p3ACTION);
				    p.add(p3ACTION);
				    break;
			//Case 15 to 19 are for player 4	
				case 15:
					p4UP = new JTextField("T", 10);
				    l.setLabelFor(p4UP);
				    p.add(p4UP);
				    break;
				case 16:    
					p4DOWN = new JTextField("G", 10);
				    l.setLabelFor(p4DOWN);
				    p.add(p4DOWN);
				    break;
				case 17:    
					p4LEFT = new JTextField("F", 10);
				    l.setLabelFor(p4LEFT);
				    p.add(p4LEFT);
				    break;
				case 18:    
					p4RIGHT = new JTextField("H", 10);
				    l.setLabelFor(p4RIGHT);
				    p.add(p4RIGHT);
				    break;	
				case 19:    
					p4ACTION = new JTextField("Y", 10);
				    l.setLabelFor(p4ACTION);
				    p.add(p4ACTION);
				    break;
			//Case 20 to 24 are for ghost	
				case 20:
					ghostUP = new JTextField("NUMPAD8", 10);
				    l.setLabelFor(ghostUP);
				    p.add(ghostUP);
				    break;
				case 21:    
					ghostDOWN = new JTextField("NUMPAD5", 10);
				    l.setLabelFor(ghostDOWN);
				    p.add(ghostDOWN);
				    break;
				case 22:    
					ghostLEFT = new JTextField("NUMPAD4", 10);
				    l.setLabelFor(ghostLEFT);
				    p.add(ghostLEFT);
				    break;
				case 23:    
					ghostRIGHT = new JTextField("NUMPAD6", 10);
				    l.setLabelFor(ghostRIGHT);
				    p.add(ghostRIGHT);
				    break;
				case 24:    
					ghostACTION = new JTextField("NUMPAD0", 10);
				    l.setLabelFor(ghostACTION);
				    p.add(ghostACTION);
				    Start.keyBindCounter++;
				    break;
			}
		}else{
			switch(count){
			//Case 0 to 4 are for player 1
				case 0:
					p1UP = new JTextField(p1Up, 10);
				    l.setLabelFor(p1UP);
				    p.add(p1UP);
				    break;
				case 1:    
					p1DOWN = new JTextField(p1Down, 10);
				    l.setLabelFor(p1DOWN);
				    p.add(p1DOWN);
				    break;
				case 2:    
					p1LEFT = new JTextField(p1Left, 10);
				    l.setLabelFor(p1LEFT);
				    p.add(p1LEFT);
				    break;
				case 3:    
					p1RIGHT = new JTextField(p1Right, 10);
				    l.setLabelFor(p1RIGHT);
				    p.add(p1RIGHT);
				    break;
				case 4:    
					p1ACTION = new JTextField(p1Action, 10);
				    l.setLabelFor(p1ACTION);
				    p.add(p1ACTION);
				    break;
			//Case 5 to 9 are for player 2
				case 5:
					p2UP = new JTextField(p2Up, 10);
				    l.setLabelFor(p2UP);
				    p.add(p2UP);
				    break;
				case 6:    
					p2DOWN = new JTextField(p2Down, 10);
				    l.setLabelFor(p2DOWN);
				    p.add(p2DOWN);
				    break;
				case 7:    
					p2LEFT = new JTextField(p2Left, 10);
				    l.setLabelFor(p2LEFT);
				    p.add(p2LEFT);
				    break;
				case 8:    
					p2RIGHT = new JTextField(p2Right, 10);
				    l.setLabelFor(p2RIGHT);
				    p.add(p2RIGHT);
				    break;
				case 9:    
					p2ACTION = new JTextField(p2Action, 10);
				    l.setLabelFor(p2ACTION);
				    p.add(p2ACTION);
				    break;
			//Case 10 to 14 are for player 3
				case 10:
					p3UP = new JTextField(p3Up, 10);
				    l.setLabelFor(p3UP);
				    p.add(p3UP);
				    break;
				case 11:    
					p3DOWN = new JTextField(p3Down, 10);
				    l.setLabelFor(p3DOWN);
				    p.add(p3DOWN);
				    break;
				case 12:    
					p3LEFT = new JTextField(p3Left, 10);
				    l.setLabelFor(p3LEFT);
				    p.add(p3LEFT);
				    break;
				case 13:    
					p3RIGHT = new JTextField(p3Right, 10);
				    l.setLabelFor(p3RIGHT);
				    p.add(p3RIGHT);
				    break;
				case 14:    
					p3ACTION = new JTextField(p3Action, 10);
				    l.setLabelFor(p3ACTION);
				    p.add(p3ACTION);
				    break;
			//Case 15 to 19 are for player 4	
				case 15:
					p4UP = new JTextField(p4Up, 10);
				    l.setLabelFor(p4UP);
				    p.add(p4UP);
				    break;
				case 16:    
					p4DOWN = new JTextField(p4Down, 10);
				    l.setLabelFor(p4DOWN);
				    p.add(p4DOWN);
				    break;
				case 17:    
					p4LEFT = new JTextField(p4Left, 10);
				    l.setLabelFor(p4LEFT);
				    p.add(p4LEFT);
				    break;
				case 18:    
					p4RIGHT = new JTextField(p4Right, 10);
				    l.setLabelFor(p4RIGHT);
				    p.add(p4RIGHT);
				    break;	
				case 19:    
					p4ACTION = new JTextField(p4Action, 10);
				    l.setLabelFor(p4ACTION);
				    p.add(p4ACTION);
				    break;
			//Case 20 to 24 are for ghost	
				case 20:
					ghostUP = new JTextField(ghostUp, 10);
				    l.setLabelFor(ghostUP);
				    p.add(ghostUP);
				    break;
				case 21:    
					ghostDOWN = new JTextField(ghostDown, 10);
				    l.setLabelFor(ghostDOWN);
				    p.add(ghostDOWN);
				    break;
				case 22:    
					ghostLEFT = new JTextField(ghostLeft, 10);
				    l.setLabelFor(ghostLEFT);
				    p.add(ghostLEFT);
				    break;
				case 23:    
					ghostRIGHT = new JTextField(ghostRight, 10);
				    l.setLabelFor(ghostRIGHT);
				    p.add(ghostRIGHT);
				    break;
				case 24:    
					ghostACTION = new JTextField(ghostAction, 10);
				    l.setLabelFor(ghostACTION);
				    p.add(ghostACTION);
				    break;
			}
		}
	}

}
