/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Oct/24/2014
 * Last Updated:	Dec/28/2014
 * 
 * Called by the Busters class and creates the GUI
 ------------------------------------------------------------------*/
package graphicalUI;

import game.Ghost;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

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
import backgroundProcesses.Wall;

public class BustersGUI {
	
	Timer timer;
	Timer ghostTimer;
	public static final JFrame frame = new JFrame("Capture the Ghost");;
	final JLayeredPane pane = new JLayeredPane();
	final ImagePanel background;
	//the number of pixels between the players and the ghost that makes the ghost visible
	final int PROXIMITY = 75;
	
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
	
	//Flashlights
	static ImagePanel lightHor1, lightVer1, lightHor2, lightVer2, lightHor3, lightVer3, lightHor4, lightVer4;
	Rectangle2D  recH1, recH2, recH3, recH4, recV1, recV2, recV3, recV4, recG;
	
	//walls
	Wall bottomBorder;
	Wall topBorder;
	Wall rightBorder;
	Wall leftBorder;
	Wall wall1, wall2, wall3, wall4, wall5, wall6, wall7, wall8, wall9, wall10, wall11, wall12, wall13, wall14, wall15;
	Wall wall16, wall17, wall18, wall19, wall20, wall21, wall22, wall23, wall24, wall25, wall26, wall27, wall28, wall29, wall30;
	Wall wall31, wall32, wall33, wall34, wall35;
	
	public BustersGUI(int players){
		//add the background
		background = new ImagePanel (new ImageIcon ("images/Busters_Background.png").getImage());
		
		//Add the health label and image
		heart = new ImagePanel (new ImageIcon ("images/Busters_Heart.png").getImage());
		healthLabel = new JLabel(String.valueOf(healthCount));
			healthLabel.setFont(new Font("", Font.BOLD, 22));
			healthLabel.setForeground(Color.WHITE);
		health = new JPanel();
			health.add(heart);
			health.add(healthLabel);
			health.setLocation(0, 0);
			health.setSize(75, 35);
			health.setOpaque(false);
		
		//Add the time panel and image
		clock = new ImagePanel (new ImageIcon ("images/Busters_Clock.png").getImage());
		timeLabel = new JLabel(String.valueOf(timeCount));
			timeLabel.setFont(new Font("", Font.BOLD, 22));
			timeLabel.setForeground(Color.BLUE);
		time = new JPanel();
			time.add(clock);
			time.add(timeLabel);
			time.setLocation(603, 0);
			time.setSize(75, 35);
			time.setOpaque(false);
			
		//adding the walls
		bottomBorder = new Wall(0, 672, 1280, 20);
		topBorder = new Wall (0, 40, 1280, 20);
		rightBorder = new Wall (0, 40, 20, 720);
		leftBorder  = new Wall (1254, 40, 20, 720);
		wall1 = new Wall (100, topBorder.getY() + 100, 20, 125);
		wall2 = new Wall (wall1.getX(), wall1.getY() + 125, 75, 20);
		wall3 = new Wall (wall2.getX() + 75, wall2.getY(), 20, 50);
		wall4 = new Wall (wall3.getX(), wall3.getY() + 50, 30, 20);
		wall5 = new Wall (wall4.getX() + 110, wall4.getY(), 30, 20);
		wall6 = new Wall (wall5.getX() + 10, topBorder.getY() + 130, 20, 150);
		wall7 = new Wall (wall5.getX() + 10, topBorder.getY(), 20, 50);
		wall8 = new Wall (wall6.getX(), wall5.getY() + 100, 20, 75);
		wall9 = new Wall (wall8.getX(), wall8.getY() + 155, 20, 110);
		wall10 = new Wall (wall9.getX() - 50, wall9.getY(), 50, 20);
		wall11 = new Wall (wall10.getX() - 145, wall10.getY(), 65, 20);
		wall12 = new Wall (wall11.getX(), wall4.getY() + 150, 20, 120);
		wall13 = new Wall (wall12.getX(), wall12.getY(), 115, 20);
		wall14 = new Wall (wall3.getX(), wall8.getY(), 20, 50);
		wall15 = new Wall (wall14.getX(), wall14.getY(), 130, 20);
		wall16 = new Wall (wall7.getX() + 100, wall7.getY(), 20, 120);
		wall17 = new Wall (wall16.getX(), wall16.getY() + 200, 20, 95);
		wall18 = new Wall (wall17.getX(), wall4.getY() - 30, 150, 20);
		wall19 = new Wall (wall18.getX() + 230, wall18.getY(), 150, 20);
		wall20 = new Wall (wall16.getX() + 380, wall16.getY(), 20, 120);
		wall21 = new Wall (wall17.getX() + 380, wall17.getY(), 20, 95);
		wall22 = new Wall (wall21.getX(), wall21.getY() + 175, 20, 260);
		wall23 = new Wall (wall19.getX(), bottomBorder.getY() -100, 150, 20);
		wall24 = new Wall (wall18.getX(), wall23.getY(), 150, 20);
		wall25 = new Wall (wall16.getX(), wall22.getY() + 50, 20, 110);
		wall26 = new Wall (wall25.getX(), wall25.getY(), 150, 20);
		wall27 = new Wall (wall26.getX() + 230, wall26.getY(), 150, 20);
		wall28 = new Wall (leftBorder.getX() - 100, wall9.getY(), 20, 110);
		wall29 = new Wall (wall28.getX(), wall22.getY(), 20, 75);
		wall30 = new Wall (wall22.getX() + 100, wall22.getY(), 280, 20);
		wall31 = new Wall (wall30.getX(), wall30.getY() - 100, 300, 20);
		wall32 = new Wall (wall31.getX(), topBorder.getY() + 100, 20, 180);
		wall33 = new Wall (wall32.getX(), wall32.getY(), 50, 20);
		wall34 = new Wall (wall33.getX() + 130, wall33.getY(), 170, 20);
		wall35 = new Wall (wall29.getX(), wall34.getY(), 20, 100);
		
		//players
		p1 = new ImagePanel (new ImageIcon ("images/players/bustersPanel/Girl1.png").getImage());
		p2 = new ImagePanel (new ImageIcon ("images/players/bustersPanel/Boy1.png").getImage());
		p3 = new ImagePanel (new ImageIcon ("images/players/bustersPanel/Girl2.png").getImage());
		p4 = new ImagePanel (new ImageIcon ("images/players/bustersPanel/Boy2.png").getImage());
		ghost = new ImagePanel (new ImageIcon ("images/players/bustersPanel/ghost.png").getImage());
		//flashlights
		lightHor1 = new ImagePanel (new ImageIcon ("images/Busters_Light_H.png").getImage());
		lightVer1 = new ImagePanel (new ImageIcon ("images/Busters_Light_V.png").getImage());
		lightHor2 = new ImagePanel (new ImageIcon ("images/Busters_Light_H.png").getImage());
		lightVer2 = new ImagePanel (new ImageIcon ("images/Busters_Light_V.png").getImage());
		lightHor3 = new ImagePanel (new ImageIcon ("images/Busters_Light_H.png").getImage());
		lightVer3 = new ImagePanel (new ImageIcon ("images/Busters_Light_V.png").getImage());
		lightHor4 = new ImagePanel (new ImageIcon ("images/Busters_Light_H.png").getImage());
		lightVer4 = new ImagePanel (new ImageIcon ("images/Busters_Light_V.png").getImage());
			lightHor1.setVisible(false);
			lightVer1.setVisible(false);
			lightHor2.setVisible(false);
			lightVer2.setVisible(false);
			lightHor3.setVisible(false);
			lightVer3.setVisible(false);
			lightHor4.setVisible(false);
			lightVer4.setVisible(false);
			
		//instantiate timer that watches the position of the entities and updates their location
		timer = new Timer(5, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//update health
				healthLabel.setText(GhostGUI.getGhostHealth());
				//get the player position from the other frame and adjust it to the frame size
				p1.setLocation(GhostGUI.getP1X()*2, GhostGUI.getP1Y()*2);
				p2.setLocation(GhostGUI.getP2X()*2, GhostGUI.getP2Y()*2);
				p3.setLocation(GhostGUI.getP3X()*2, GhostGUI.getP3Y()*2);
				p4.setLocation(GhostGUI.getP4X()*2, GhostGUI.getP4Y()*2);
				ghost.setLocation(GhostGUI.getGhostX()*2, GhostGUI.getGhostY()*2);
					ghost.setVisible(false);
					//Ghost's bounding box
				recG = new Rectangle (ghost.getX(), ghost.getY(), ghost.getWidth(), ghost.getHeight());
				//decide whether or not to display the ghost depending on its proximity on to the players
				if (checkProximity(p1) || checkProximity(p2) || checkProximity(p3) || checkProximity(p4)){
					//if the ghost is not already added, add the ghost
					ghost.setVisible(true);
				}else{
					//remove the ghost
					ghost.setVisible(false);	
				}
				//add lights and check if they are on or not
				//p1 horizontal light
				lightHor1.setLocation(GhostGUI.lightHor1.getX()*2, GhostGUI.lightHor1.getY()*2);
				lightHor1.setSize(GhostGUI.lightHor1.getWidth() * 2, GhostGUI.lightHor1.getHeight() * 2);
				if (GhostGUI.lightHor1.isVisible()){
					//if the light is on, show the light
					lightHor1.setVisible(true);
					//creating the bounding box for the light
					recH1 = new Rectangle (lightHor1.getX(), lightHor1.getY(), lightHor1.getWidth(), lightHor1.getHeight());
					//check if ghost is hit by light
					ghost.setVisible(checkLightIntersection(recG, recH1));
				}else{
					//otherwise hide it
					lightHor1.setVisible(false);
					recH1 = new Rectangle (0, 0, 0, 0);
				}
				//p1 vertical light
				lightVer1.setLocation(GhostGUI.lightVer1.getX()*2, GhostGUI.lightVer1.getY()*2);
				lightVer1.setSize(GhostGUI.lightVer1.getWidth() * 2, GhostGUI.lightVer1.getHeight() * 2);
				if (GhostGUI.lightVer1.isVisible()){
					//if the light is on, show the light
					lightVer1.setVisible(true);
					//creating the bounding box for the light
					recV1 = new Rectangle (lightVer1.getX(), lightVer1.getY(), lightVer1.getWidth(), lightVer1.getHeight());
					//check if ghost is hit by light
					ghost.setVisible(checkLightIntersection(recG, recV1));
				}else{
					//otherwise hide it
					lightVer1.setVisible(false);
					recV1 = new Rectangle (0, 0, 0, 0);
				}
				//p2 horizontal light
				lightHor2.setLocation(GhostGUI.lightHor2.getX()*2, GhostGUI.lightHor2.getY()*2);
				lightHor2.setSize(GhostGUI.lightHor2.getWidth() * 2, GhostGUI.lightHor2.getHeight() * 2);
				if (GhostGUI.lightHor2.isVisible()){
					//if the light is on, show the light
					lightHor2.setVisible(true);
					//creating the bounding box for the light
					recH2 = new Rectangle (lightHor2.getX(), lightHor2.getY(), lightHor2.getWidth(), lightHor2.getHeight());
					//check if ghost is hit by light
					ghost.setVisible(checkLightIntersection(recG, recH2));
				}else{
					//otherwise hide it
					lightHor2.setVisible(false);
				}
				//p2 vertical light
				lightVer2.setLocation(GhostGUI.lightVer2.getX()*2, GhostGUI.lightVer2.getY()*2);
				lightVer2.setSize(GhostGUI.lightVer2.getWidth() * 2, GhostGUI.lightVer2.getHeight() * 2);
				if (GhostGUI.lightVer2.isVisible()){
					//if the light is on, show the light
					lightVer2.setVisible(true);
					//creating the bounding box for the light
					recV2 = new Rectangle (lightVer2.getX(), lightVer2.getY(), lightVer2.getWidth(), lightVer2.getHeight());
					//check if ghost is hit by light
					ghost.setVisible(checkLightIntersection(recG, recV2));
				}else{
					//otherwise hide it
					lightVer2.setVisible(false);
				}
				//p3 horizontal light
				lightHor3.setLocation(GhostGUI.lightHor3.getX()*2, GhostGUI.lightHor3.getY()*2);
				lightHor3.setSize(GhostGUI.lightHor3.getWidth() * 2, GhostGUI.lightHor3.getHeight() * 2);
				if (GhostGUI.lightHor3.isVisible()){
					//if the light is on, show the light
					lightHor3.setVisible(true);
					//creating the bounding box for the light
					recH3 = new Rectangle (lightHor3.getX(), lightHor3.getY(), lightHor3.getWidth(), lightHor3.getHeight());
					//check if ghost is hit by light
					ghost.setVisible(checkLightIntersection(recG, recH3));
				}else{
					//otherwise hide it
					lightHor3.setVisible(false);
				}
				//p3 vertical light
				lightVer3.setLocation(GhostGUI.lightVer3.getX()*2, GhostGUI.lightVer3.getY()*2);
				lightVer3.setSize(GhostGUI.lightVer3.getWidth() * 2, GhostGUI.lightVer3.getHeight() * 2);
				if (GhostGUI.lightVer3.isVisible()){
					//if the light is on, show the light
					lightVer3.setVisible(true);
					//creating the bounding box for the light
					recV3 = new Rectangle (lightVer3.getX(), lightVer3.getY(), lightVer3.getWidth(), lightVer3.getHeight());
					//check if ghost is hit by light
					ghost.setVisible(checkLightIntersection(recG, recV3));
				}else{
					//otherwise hide it
					lightVer3.setVisible(false);
				}
				//p4 horizontal light
				lightHor4.setLocation(GhostGUI.lightHor4.getX()*2, GhostGUI.lightHor4.getY()*2);
				lightHor4.setSize(GhostGUI.lightHor4.getWidth() * 2, GhostGUI.lightHor4.getHeight() * 2);
				if (GhostGUI.lightHor4.isVisible()){
					//if the light is on, show the light
					lightHor4.setVisible(true);
					//creating the bounding box for the light
					recH4 = new Rectangle (lightHor4.getX(), lightHor4.getY(), lightHor4.getWidth(), lightHor4.getHeight());
					//check if ghost is hit by light
					ghost.setVisible(checkLightIntersection(recG, recH4));
				}else{
					//otherwise hide it
					lightHor4.setVisible(false);
				}
				//p4 vertical light
				lightVer4.setLocation(GhostGUI.lightVer4.getX()*2, GhostGUI.lightVer4.getY()*2);
				lightVer4.setSize(GhostGUI.lightVer4.getWidth() * 2, GhostGUI.lightVer4.getHeight() * 2);
				if (GhostGUI.lightVer4.isVisible()){
					//if the light is on, show the light
					lightVer4.setVisible(true);
					//creating the bounding box for the light
					recV4 = new Rectangle (lightVer4.getX(), lightVer4.getY(), lightVer4.getWidth(), lightVer4.getHeight());
					//check if ghost is hit by light
					ghost.setVisible(checkLightIntersection(recG, recV4));
				}else{
					//otherwise hide it
					lightVer4.setVisible(false);
				}
				//check condition on the players and ghost
				GhostGUI.checkCondition();
			}	
		});
		//start the timer
		timer.start();
		
		//Timer for ghost health
		ghostTimer = new Timer (525, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//player 1
				if (GhostGUI.lightHor1.isVisible()){
					Ghost.ghostHealth(recG, recH1);
				}
				else if (GhostGUI.lightVer1.isVisible()){
					Ghost.ghostHealth(recG, recV1);
				}
				//player 2
				if (GhostGUI.lightHor2.isVisible()){
					Ghost.ghostHealth(recG, recH2);
				}
				else if (GhostGUI.lightVer2.isVisible()){
					Ghost.ghostHealth(recG, recV2);
				}
				//player 3
				if (GhostGUI.lightHor3.isVisible()){
					Ghost.ghostHealth(recG, recH3);
				}
				else if (GhostGUI.lightVer3.isVisible()){
					Ghost.ghostHealth(recG, recV3);
				}
				//player 4
				if (GhostGUI.lightHor4.isVisible()){
					Ghost.ghostHealth(recG, recH4);
				}
				else if (GhostGUI.lightVer4.isVisible()){
					Ghost.ghostHealth(recG, recV4);
				}
			}
		});
		ghostTimer.setDelay(525);
		ghostTimer.start();
		
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
		//players - add the appropriate number of players
		if (players >= 1)
			pane.add(p1, JLayeredPane.MODAL_LAYER);
		if (players >= 2)
			pane.add(p2, JLayeredPane.MODAL_LAYER);
		if (players >= 3)
			pane.add(p3, JLayeredPane.MODAL_LAYER);
		if (players >= 4)
			pane.add(p4, JLayeredPane.MODAL_LAYER);
		//Ghost
		pane.add(ghost, JLayeredPane.MODAL_LAYER);
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
		frame.setSize(1280,720);
		frame.setLocationRelativeTo(null);	//set frame location in middle of screen
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	/**
	 * checks if the ghost is close enough to the players to be visible
	 * @param ip - the player
	 * @return true is close enough/ false is not close enough
	 */
	public boolean checkProximity(ImagePanel ip){
		//check the positive and negative boundaries of the player positions
		if ((ghost.getX() <= ip.getX() + PROXIMITY) && (ghost.getX() > ip.getX() - PROXIMITY) &&
				(ghost.getY() <= ip.getY() + PROXIMITY) && (ghost.getY() > ip.getY() - PROXIMITY)){
			return true;	//if close enough
		}
		else{
			return false;	//if not close enough
		}
	}
	
	/**
	 * makes the ghost visible if the ghost is hit by a light
	 * @param ghost
	 * @param light
	 * @return
	 */
	public boolean checkLightIntersection (Rectangle2D ghost, Rectangle2D light){
		//checks if ghost is hit by light
		if (ghost.intersects(light)){
			return true;	//if hit, return true
		}
		else{
			return false;	//if not hit, return false
		}
	}
}
