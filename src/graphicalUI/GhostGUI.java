/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Oct/24/2014
 * Last Updated:	Dec/28/2014
 * 
 * Called by the Ghost class and creates the GUI
 ------------------------------------------------------------------*/
package graphicalUI;

import game.Busters;
import game.Ghost;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import backgroundProcesses.ImagePanel;
import backgroundProcesses.KeyBinds;
import backgroundProcesses.KeyboardAnimation;
import backgroundProcesses.Wall;

public class GhostGUI {
	
	Timer timer;
	public final static JFrame frame = new JFrame("You are the Ghost");
	final static JLayeredPane pane = new JLayeredPane();
	final ImagePanel background;
	static int playerCount;
	
	//health
	JPanel health;
	public static JLabel healthLabel;
	ImagePanel heart;
	int healthCount = 100;
	
	//time
	JPanel time;
	public static JLabel timeLabel;
	ImagePanel clock;
	int timeCount = 300;
	
	//Players and ghost
	public static ImagePanel p1, p2, p3, p4, ghost;
	//players lives
	public static boolean p1Life = true, p2Life = true, p3Life = true, p4Life = true;
	
	//Flashlight
	public static ImagePanel lightHor1, lightVer1, lightHor2, lightVer2, lightHor3, lightVer3, lightHor4, lightVer4;
	int p1ActionCount = 2, p2ActionCount  = 2, p3ActionCount = 2, p4ActionCount = 2;
	//Light length testing
	public static BufferedImage image;	//image with a blank maze for pixel colour detection
	public static int bounds = 0;	//length of the flashlight
	public static int leftLightX = 0;	//integer to find the position of the flashlight when the player is facing left
	public static int upLightY = 0; //integer to find the position of the flashlight when the player is facing up
	//Ghost Distances
	static double distP1, distP2, distP3, distP4;
	
	//walls
	Wall bottomBorder;
	Wall topBorder;
	Wall rightBorder;
	Wall leftBorder;
	Wall wall1, wall2, wall3, wall4, wall5, wall6, wall7, wall8, wall9, wall10, wall11, wall12, wall13, wall14, wall15;
	Wall wall16, wall17, wall18, wall19, wall20, wall21, wall22, wall23, wall24, wall25, wall26, wall27, wall28, wall29, wall30;
	Wall wall31, wall32, wall33, wall34, wall35;
	
	public GhostGUI(int players){
		playerCount = players;
		//load background Image
		background = new ImagePanel (new ImageIcon ("images/Ghosts_Background.png").getImage());
		//load image to allow better wall detection, there is no need to display the image
		try {
			image = ImageIO.read(new File("images/Blank Map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Add the health label and image
		heart = new ImagePanel (new ImageIcon ("images/Ghosts_Heart.png").getImage());
		healthLabel = new JLabel(String.valueOf(healthCount));
			healthLabel.setFont(new Font("", Font.BOLD, 12));
			healthLabel.setForeground(Color.WHITE);
		health = new JPanel();
			health.add(heart);
			health.add(healthLabel);
			health.setLocation(0, 0);
			health.setSize(75, 35);
			health.setOpaque(false);
		
		//Add the time panel and image
		clock = new ImagePanel (new ImageIcon ("images/Ghosts_Clock.png").getImage());
		timeLabel = new JLabel(String.valueOf(timeCount));
			timeLabel.setFont(new Font("", Font.BOLD, 12));
			timeLabel.setForeground(Color.BLUE);
		time = new JPanel();
			time.add(clock);
			time.add(timeLabel);
			time.setLocation(300, 0);
			time.setSize(75, 35);
			time.setOpaque(false);
			
		//adding the walls
		bottomBorder = new Wall(0, 672/2, 1280/2, 20/2);
		topBorder = new Wall (0, 40/2, 1280/2, 20/2);
		rightBorder = new Wall (0, 40/2, 20/2, 720/2);
		leftBorder  = new Wall (1254/2, 40/2, 20/2, 720/2);
		wall1 = new Wall (100/2, topBorder.getY() + 100/2, 20/2, 125/2);
		wall2 = new Wall (wall1.getX(), wall1.getY() + 125/2, 75/2, 20/2);
		wall3 = new Wall (wall2.getX() + 75/2, wall2.getY(), 20/2, 50/2);
		wall4 = new Wall (wall3.getX(), wall3.getY() + 50/2, 30/2, 20/2);
		wall5 = new Wall (wall4.getX() + 110/2, wall4.getY(), 30/2, 20/2);
		wall6 = new Wall (wall5.getX() + 10/2, topBorder.getY() + 130/2, 20/2, 150/2);
		wall7 = new Wall (wall5.getX() + 10/2, topBorder.getY(), 20/2, 50/2);
		wall8 = new Wall (wall6.getX(), wall5.getY() + 100/2, 20/2, 75/2);
		wall9 = new Wall (wall8.getX(), wall8.getY() + 155/2, 20/2, 110/2);
		wall10 = new Wall (wall9.getX() - 50/2, wall9.getY(), 50/2, 20/2);
		wall11 = new Wall (wall10.getX() - 145/2, wall10.getY(), 65/2, 20/2);
		wall12 = new Wall (wall11.getX(), wall4.getY() + 150/2, 20/2, 120/2);
		wall13 = new Wall (wall12.getX(), wall12.getY(), 115/2, 20/2);
		wall14 = new Wall (wall3.getX(), wall8.getY(), 20/2, 50/2);
		wall15 = new Wall (wall14.getX(), wall14.getY(), 130/2, 20/2);
		wall16 = new Wall (wall7.getX() + 100/2, wall7.getY(), 20/2, 120/2);
		wall17 = new Wall (wall16.getX(), wall16.getY() + 200/2, 20/2, 95/2);
		wall18 = new Wall (wall17.getX(), wall4.getY() - 30/2, 150/2, 20/2);
		wall19 = new Wall (wall18.getX() + 230/2, wall18.getY(), 150/2, 20/2);
		wall20 = new Wall (wall16.getX() + 380/2, wall16.getY(), 20/2, 120/2);
		wall21 = new Wall (wall17.getX() + 380/2, wall17.getY(), 20/2, 95/2);
		wall22 = new Wall (wall21.getX(), wall21.getY() + 175/2, 20/2, 260/2);
		wall23 = new Wall (wall19.getX(), bottomBorder.getY() -100/2, 150/2, 20/2);
		wall24 = new Wall (wall18.getX(), wall23.getY(), 150/2, 20/2);
		wall25 = new Wall (wall16.getX(), wall22.getY() + 50/2, 20/2, 110/2);
		wall26 = new Wall (wall25.getX(), wall25.getY(), 150/2, 20/2);
		wall27 = new Wall (wall26.getX() + 230/2, wall26.getY(), 150/2, 20/2);
		wall28 = new Wall (leftBorder.getX() - 100/2, wall9.getY(), 20/2, 110/2);
		wall29 = new Wall (wall28.getX(), wall22.getY(), 20/2, 75/2);
		wall30 = new Wall (wall22.getX() + 100/2, wall22.getY(), 280/2, 20/2);
		wall31 = new Wall (wall30.getX(), wall30.getY() - 100/2, 300/2, 20/2);
		wall32 = new Wall (wall31.getX(), topBorder.getY() + 100/2, 20/2, 180/2);
		wall33 = new Wall (wall32.getX(), wall32.getY(), 50/2, 20/2);
		wall34 = new Wall (wall33.getX() + 130/2, wall33.getY(), 170/2, 20/2);
		wall35 = new Wall (wall29.getX(), wall34.getY(), 20/2, 100/2);
		
		
		//players + ghost
		p1 = new ImagePanel (new ImageIcon ("images/players/ghostPanel/Girl1.png").getImage());
		p2 = new ImagePanel (new ImageIcon ("images/players/ghostPanel/Boy1.png").getImage());
		p3 = new ImagePanel (new ImageIcon ("images/players/ghostPanel/Girl2.png").getImage());
		p4 = new ImagePanel (new ImageIcon ("images/players/ghostPanel/Boy2.png").getImage());
		ghost = new ImagePanel (new ImageIcon ("images/players/ghostPanel/ghost.png").getImage());
		//set the locations
		p1.setLocation(25/2, 65/2);
		p2.setLocation(1190/2, 65/2);
		p3.setLocation(25/2, 600/2);
		p4.setLocation(1190/2, 600/2);
		ghost.setLocation(560/2, 360/2);
		
		//flashlights
		lightHor1 = new ImagePanel (new ImageIcon ("images/Ghost_Light_H.png").getImage());
		lightVer1 = new ImagePanel (new ImageIcon ("images/Ghost_Light_V.png").getImage());
		lightHor2 = new ImagePanel (new ImageIcon ("images/Ghost_Light_H.png").getImage());
		lightVer2 = new ImagePanel (new ImageIcon ("images/Ghost_Light_V.png").getImage());
		lightHor3 = new ImagePanel (new ImageIcon ("images/Ghost_Light_H.png").getImage());
		lightVer3 = new ImagePanel (new ImageIcon ("images/Ghost_Light_V.png").getImage());
		lightHor4 = new ImagePanel (new ImageIcon ("images/Ghost_Light_H.png").getImage());
		lightVer4 = new ImagePanel (new ImageIcon ("images/Ghost_Light_V.png").getImage());
			lightHor1.setVisible(false);
			lightVer1.setVisible(false);
			lightHor2.setVisible(false);
			lightVer2.setVisible(false);
			lightHor3.setVisible(false);
			lightVer3.setVisible(false);
			lightHor4.setVisible(false);
			lightVer4.setVisible(false);
		//updating the location of the flashlights
		//Player 1
		pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0, false), "p1Action");
		pane.getActionMap().put("p1Action", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//if the player activates the light
				if (p1ActionCount % 2 == 0){
					//depending on orientation, display the correct light
					if (KeyboardAnimation.movementDirection1 == 2 || KeyboardAnimation.movementDirection1 == 4){
						lightHor1.setVisible(true);
						Ghost.getLightObstacles(lightHor1, p1, KeyboardAnimation.movementDirection1);
					}
					else if (KeyboardAnimation.movementDirection1 == 1 || KeyboardAnimation.movementDirection1 == 3){
						lightVer1.setVisible(true);
						Ghost.getLightObstacles(lightVer1, p1, KeyboardAnimation.movementDirection1);
					}
					System.out.println("pressed ON");	//debug
					
					p1ActionCount++;
				}
				//if the player deactivates the light
				else{
					System.out.println("pressed OFF");
					//hide both lights, the movement direction does not matter
					lightHor1.setVisible(false);
					lightVer1.setVisible(false);
					p1ActionCount++;
				}
			}
		});
		//Player 2
		pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, false), "p2Action");
		pane.getActionMap().put("p2Action", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//if the player activates the light
				if (p2ActionCount % 2 == 0){
					//depending on orientation, display the correct light
					if (KeyboardAnimation.movementDirection2 == 2 || KeyboardAnimation.movementDirection2 == 4){
						lightHor2.setVisible(true);
						Ghost.getLightObstacles(lightHor2, p2, KeyboardAnimation.movementDirection2);
					}
					else if (KeyboardAnimation.movementDirection2 == 1 || KeyboardAnimation.movementDirection2 == 3){
						lightVer2.setVisible(true);
						Ghost.getLightObstacles(lightVer2, p2, KeyboardAnimation.movementDirection2);
					}
					System.out.println("pressed ON");	//debug
					
					p2ActionCount++;
				}
				//if the player deactivates the light
				else{
					System.out.println("pressed OFF");
					//hide both lights, the movement direction does not matter
					lightHor2.setVisible(false);
					lightVer2.setVisible(false);
					p2ActionCount++;
				}
			}
		});
		//Player 3
		pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0, false), "p3Action");
		pane.getActionMap().put("p3Action", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//if the player activates the light
				if (p3ActionCount % 2 == 0){
					//depending on orientation, display the correct light
					if (KeyboardAnimation.movementDirection3 == 2 || KeyboardAnimation.movementDirection3 == 4){
						lightHor3.setVisible(true);
						Ghost.getLightObstacles(lightHor3, p3, KeyboardAnimation.movementDirection3);
					}
					else if (KeyboardAnimation.movementDirection3 == 1 || KeyboardAnimation.movementDirection3 == 3){
						lightVer3.setVisible(true);
						Ghost.getLightObstacles(lightVer3, p3, KeyboardAnimation.movementDirection3);
					}
					System.out.println("pressed ON");	//debug
					
					p3ActionCount++;
				}
				//if the player deactivates the light
				else{
					System.out.println("pressed OFF");
					//hide both lights, the movement direction does not matter
					lightHor3.setVisible(false);
					lightVer3.setVisible(false);
					p3ActionCount++;
				}
			}
		});
		//Player 4
		pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0, false), "p4Action");
		pane.getActionMap().put("p4Action", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//if the player activates the light
				if (p4ActionCount % 2 == 0){
					//depending on orientation, display the correct light
					if (KeyboardAnimation.movementDirection4 == 2 || KeyboardAnimation.movementDirection4 == 4){
						lightHor4.setVisible(true);
						Ghost.getLightObstacles(lightHor4, p4, KeyboardAnimation.movementDirection4);
					}
					else if (KeyboardAnimation.movementDirection4 == 1 || KeyboardAnimation.movementDirection4 == 3){
						lightVer4.setVisible(true);
						Ghost.getLightObstacles(lightVer4, p4, KeyboardAnimation.movementDirection4);
					}
					System.out.println("pressed ON");	//debug
					
					p4ActionCount++;
				}
				//if the player deactivates the light
				else{
					System.out.println("pressed OFF");
					//hide both lights, the movement direction does not matter
					lightHor4.setVisible(false);
					lightVer4.setVisible(false);
					p4ActionCount++;
				}
			}
		});		
		//Ghost
		pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0, false), "ghostAction");
		pane.getActionMap().put("ghostAction", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//get the distance between the ghost and the 4 players
				distP1 = Math.hypot(ghost.getX() - p1.getX(), ghost.getY() - p1.getY());
				distP2 = Math.hypot(ghost.getX() - p2.getX(), ghost.getY() - p2.getY());
				distP3 = Math.hypot(ghost.getX() - p3.getX(), ghost.getY() - p3.getY());
				distP4 = Math.hypot(ghost.getX() - p4.getX(), ghost.getY() - p4.getY());
				System.out.println ("Distance: P1 " + distP1 + " P2 " + distP2 + " P3" + distP3 + " P4" + distP4);	//debug
				//if the distance between 1 player and the ghost is less than 40
				if (distP1 < 40 || distP2 < 40 || distP3 < 40 || distP4 <40){
					//if player 1 is closest, carry player 1
					if (distP1 < distP2 && distP1 < distP3 && distP1 < distP4){
						Ghost.ghostAction(ghost, p1);
					}
					//if player 2 is closest, carry player 2
					else if (distP2 < distP1 && distP2 < distP3 && distP2 < distP4){
						Ghost.ghostAction(ghost, p2);
					}
					//if player 3 is closest, carry player 3
					else if (distP3 < distP1 && distP3 < distP2 && distP3 < distP4){
						Ghost.ghostAction(ghost, p3);
					}
					//if player 4 is closest, carry player 4
					else if (distP4 < distP1 && distP4 < distP2 && distP4 < distP3){
						Ghost.ghostAction(ghost, p4);
					}
				}
				//if players are too far
				else{
					System.out.println("Not close enough");
				}
			}
		});
			
		//start keyBoard Animation process
		KeyBinds.keyboardMovements();
		
		//reviving players
			Timer revive = new Timer (1500, null);
			revive.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if (p1Life == false){
						//turn off the player's flashlight
						lightHor1.setVisible(false);
						lightVer1.setVisible(false);
						System.out.println("P1 dead");
						Ghost.revive(p1, p2, p3, p4);
					}
					if (p2Life == false){
						//turn off the player's flashlight
						lightHor2.setVisible(false);
						lightVer2.setVisible(false);
						System.out.println("P2 dead");
						Ghost.revive(p2, p1, p3, p4);
					}
					if (p3Life == false){
						//turn off the player's flashlight
						lightHor3.setVisible(false);
						lightVer3.setVisible(false);
						System.out.println("P3 dead");
						Ghost.revive(p3, p1, p2, p4);
					}
					if (p4Life == false){
						//turn off the player's flashlight
						lightHor4.setVisible(false);
						lightVer4.setVisible(false);
						System.out.println("P4 dead");
						Ghost.revive(p4, p1, p2, p3);
					}
				}
			});
			revive.setDelay(1500);
			revive.start();
		
		
		//pressing the escape button displays the pause menu
		pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "ESCAPE pressed");
		pane.getActionMap().put("ESCAPE pressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new PauseGUI();
			}
		});
		
	
		//Add content to the pane and frame
		pane.add(background,JLayeredPane.DEFAULT_LAYER);
		//walls
		pane.add(topBorder, JLayeredPane.PALETTE_LAYER);
		pane.add(bottomBorder, JLayeredPane.PALETTE_LAYER);
		pane.add(rightBorder, JLayeredPane.PALETTE_LAYER);
		pane.add(leftBorder, JLayeredPane.PALETTE_LAYER);
		pane.add(wall1, JLayeredPane.PALETTE_LAYER);
		pane.add(wall2, JLayeredPane.PALETTE_LAYER);
		pane.add(wall3, JLayeredPane.PALETTE_LAYER);
		pane.add(wall4, JLayeredPane.PALETTE_LAYER);
		pane.add(wall5, JLayeredPane.PALETTE_LAYER);
		pane.add(wall6, JLayeredPane.PALETTE_LAYER);
		pane.add(wall7, JLayeredPane.PALETTE_LAYER);
		pane.add(wall8, JLayeredPane.PALETTE_LAYER);
		pane.add(wall9, JLayeredPane.PALETTE_LAYER);
		pane.add(wall10, JLayeredPane.PALETTE_LAYER);
		pane.add(wall11, JLayeredPane.PALETTE_LAYER);
		pane.add(wall12, JLayeredPane.PALETTE_LAYER);
		pane.add(wall13, JLayeredPane.PALETTE_LAYER);
		pane.add(wall14, JLayeredPane.PALETTE_LAYER);
		pane.add(wall15, JLayeredPane.PALETTE_LAYER);
		pane.add(wall16, JLayeredPane.PALETTE_LAYER);
		pane.add(wall17, JLayeredPane.PALETTE_LAYER);
		pane.add(wall18, JLayeredPane.PALETTE_LAYER);
		pane.add(wall19, JLayeredPane.PALETTE_LAYER);
		pane.add(wall20, JLayeredPane.PALETTE_LAYER);
		pane.add(wall21, JLayeredPane.PALETTE_LAYER);
		pane.add(wall22, JLayeredPane.PALETTE_LAYER);
		pane.add(wall23, JLayeredPane.PALETTE_LAYER);
		pane.add(wall24, JLayeredPane.PALETTE_LAYER);
		pane.add(wall25, JLayeredPane.PALETTE_LAYER);
		pane.add(wall26, JLayeredPane.PALETTE_LAYER);
		pane.add(wall27, JLayeredPane.PALETTE_LAYER);
		pane.add(wall28, JLayeredPane.PALETTE_LAYER);
		pane.add(wall29, JLayeredPane.PALETTE_LAYER);
		pane.add(wall30, JLayeredPane.PALETTE_LAYER);
		pane.add(wall31, JLayeredPane.PALETTE_LAYER);
		pane.add(wall32, JLayeredPane.PALETTE_LAYER);
		pane.add(wall33, JLayeredPane.PALETTE_LAYER);
		pane.add(wall34, JLayeredPane.PALETTE_LAYER);
		pane.add(wall35, JLayeredPane.PALETTE_LAYER);
		//ghost
		pane.add(ghost, JLayeredPane.MODAL_LAYER);
		//players - depending on how many players are playing, add the appropriate number of players in
		if (players >= 2)
			pane.add(p1, JLayeredPane.MODAL_LAYER);
		if (players >= 3)
			pane.add(p2, JLayeredPane.MODAL_LAYER);
		if (players >= 4)
			pane.add(p3, JLayeredPane.MODAL_LAYER);
		if (players >= 5)
			pane.add(p4, JLayeredPane.MODAL_LAYER);
		//Flashlights
		pane.add(lightHor1, JLayeredPane.DRAG_LAYER);
		pane.add(lightVer1, JLayeredPane.DRAG_LAYER);
		pane.add(lightHor2, JLayeredPane.DRAG_LAYER);
		pane.add(lightVer2, JLayeredPane.DRAG_LAYER);
		pane.add(lightHor3, JLayeredPane.DRAG_LAYER);
		pane.add(lightVer3, JLayeredPane.DRAG_LAYER);
		pane.add(lightHor4, JLayeredPane.DRAG_LAYER);
		pane.add(lightVer4, JLayeredPane.DRAG_LAYER);
		//other
		pane.add(health,JLayeredPane.DRAG_LAYER);
		pane.add(time, JLayeredPane.DRAG_LAYER);
		frame.add(pane);
		
		//Frame Defaults
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640,373);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	/**
	 * methods to get the x and y position of the players and the ghost
	 * @return x or y position
	 */
	public static int getP1X(){ return p1.getX();}
	public static int getP1Y(){ return p1.getY();}
	public static int getP2X(){ return p2.getX();}
	public static int getP2Y(){ return p2.getY();}
	public static int getP3X(){ return p3.getX();}
	public static int getP3Y(){ return p3.getY();}
	public static int getP4X(){ return p4.getX();}
	public static int getP4Y(){ return p4.getY();}
	public static int getGhostX(){ return ghost.getX();}
	public static int getGhostY(){ return ghost.getY();}
	
	/**
	 * gets whether a player is still alive or dead
	 * @param player
	 * @return
	 */
	public static boolean playerAlive(ImagePanel player){
		if (player == p1){
			return p1Life;
		}
		else if (player == p2){
			return p2Life;
		}
		else if (player == p3){
			return p3Life;
		}
		else if (player == p4){
			return p4Life;
		}
		else{
			return true;
		}
	}
	
	public static void checkCondition(){
		//depending on the number of players, check for their lives
		if (playerCount == 2){
			if (!p1Life){
				Busters.endGame(1);
				System.out.println("1 player dead");
			}
		}
		else if (playerCount == 3){
			if (!p1Life && !p2Life){
				Busters.endGame(1);
				System.out.println("2 players dead");
			}
		}
		else if (playerCount == 4){
			if (!p1Life && !p2Life && !p3Life){
				Busters.endGame(1);
				System.out.println("3 players dead");
			}
		}
		else if (playerCount == 5){
			if (!p1Life && !p2Life && !p3Life && !p4Life){
				Busters.endGame(1);
				System.out.println("4 players dead");
			}
		}
		
		//check the other options
		if (healthLabel.getText().equals("0")){
			Busters.endGame(2);
			System.out.println("ghost dead");
		}
		else if (timeLabel.getText().equals("0")){
			Busters.endGame(3);
			System.out.println("Time over");
		}
	}
	
	/**
	 * @return the time left
	 */
	public static String getTimeLeft(){
		return timeLabel.getText();
	}	
	
	public static String getGhostHealth(){
		return healthLabel.getText();
	}
}
