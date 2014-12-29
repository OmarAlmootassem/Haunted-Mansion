/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Oct/24/2014
 * Last Updated:	Dec/28/2014
 * 
 * Called by the Start class, it launches the GhostGUI and is the 
 * brains behind the ghost mechanics
 ------------------------------------------------------------------*/
package game;

import graphicalUI.GhostGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

import backgroundProcesses.ImagePanel;
import backgroundProcesses.KeyBinds;
import backgroundProcesses.Time;

public class Ghost {	
	
	//Ghost Action
	final static int BOUNDARY = 40;		//if player is within BOUNDARY pixels of the ghost, the player can be captures
	public static int health = 0;		//dead player's health
	
	//public static Timer drain;
	
	public Ghost(int players){
		//launch the Graphical User Interface
		GhostGUI gui = new GhostGUI(players);
	}
	
	/**
	 * Checks for obstacles that will block the light
	 * @param light
	 * @param player
	 * @param orientation
	 */
	public static void getLightObstacles(final ImagePanel light, final ImagePanel player, final int orientation){
		final Timer timer = new Timer (1, null);
		timer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//if the light orientation is up
				if (orientation == 1){
					light.setLocation(player.getX() + 5, player.getY() - GhostGUI.upLightY);
					//get RGB value of the pixel
					int c = GhostGUI.image.getRGB(light.getX() + 10, light.getY() - GhostGUI.bounds + GhostGUI.upLightY);
					if (GhostGUI.bounds < 50)
						GhostGUI.bounds++;
					
					int  red = (c & 0x00ff0000) >> 16;
					int  green = (c & 0x0000ff00) >> 8;
					int  blue = c & 0x000000ff;
					// and the Java Color is ...
					Color color = new Color(red,green,blue);
					System.out.println(String.valueOf(color) + " location: " + GhostGUI.bounds);	//testing color
					//stop the timer when light is turned off
					if (!light.isVisible()){
						GhostGUI.bounds = 0;
						timer.stop();
					}
					//if the light hits the wall or it reaches it maximum, stop the timer and move light
					else if (red == 64 || GhostGUI.bounds == 50){
						light.setSize(20, GhostGUI.bounds);
						light.setLocation(player.getX() + 5, player.getY() - GhostGUI.bounds);
						timer.stop();
						moveLight(light, player, GhostGUI.bounds, orientation);
						GhostGUI.bounds = 0;
						System.out.println(orientation);
					}
				}
				//if the light orientation is right
				else if (orientation == 2){
					light.setLocation(player.getX() + 30, player.getY());
					//get RGB value of the pixel
					int c = GhostGUI.image.getRGB(light.getX() + GhostGUI.bounds, light.getY() + 10);
					if (GhostGUI.bounds < 50)
						GhostGUI.bounds++;
					
					int  red = (c & 0x00ff0000) >> 16;
					int  green = (c & 0x0000ff00) >> 8;
					int  blue = c & 0x000000ff;
					// and the Java Color is ...
					Color color = new Color(red,green,blue);
					System.out.println(String.valueOf(color) + " location: " + GhostGUI.bounds);	//testing color
					//stop the timer when light is turned off
					if (!light.isVisible()){
						GhostGUI.bounds = 0;
						timer.stop();
					}
					//if the light hits the wall or it reaches it maximum, stop the timer and move light
					else if (red == 64 || GhostGUI.bounds == 50){
						light.setSize(GhostGUI.bounds, 20);
						timer.stop();
						moveLight(light, player, GhostGUI.bounds, orientation);
						GhostGUI.bounds = 0;
						System.out.println(orientation);
					}
				}
				//if the light orientation is down
				else if (orientation == 3){
					light.setLocation(player.getX() + 5, player.getY() + 32);
					//get RGB value of the pixel
					int c = GhostGUI.image.getRGB(light.getX() + 10, light.getY() + GhostGUI.bounds);
					if (GhostGUI.bounds < 50)
						GhostGUI.bounds++;
					
					int  red = (c & 0x00ff0000) >> 16;
					int  green = (c & 0x0000ff00) >> 8;
					int  blue = c & 0x000000ff;
					// and the Java Color is ...
					Color color = new Color(red,green,blue);
					System.out.println(String.valueOf(color) + " location: " + GhostGUI.bounds);	//testing color
					//stop the timer when light is turned off
					if (!light.isVisible()){
						GhostGUI.bounds = 0;
						timer.stop();
					}
					//if the light hits the wall or it reaches it maximum, stop the timer and move light
					else if (red == 64 || GhostGUI.bounds == 50){
						light.setSize(20, GhostGUI.bounds);
						timer.stop();
						moveLight(light, player, GhostGUI.bounds, orientation);
						GhostGUI.bounds = 0;
						System.out.println(orientation);
					}
				}
				//if the light orientation is left
				else if (orientation == 4){
					light.setLocation(player.getX() - GhostGUI.leftLightX, player.getY());
					//get RGB value of the pixel
					int c = GhostGUI.image.getRGB(light.getX() - GhostGUI.bounds + GhostGUI.leftLightX, light.getY() + 10);
					if (GhostGUI.bounds < 50)
						GhostGUI.bounds++;
					
					int  red = (c & 0x00ff0000) >> 16;
					int  green = (c & 0x0000ff00) >> 8;
					int  blue = c & 0x000000ff;
					// and the Java Color is ...
					Color color = new Color(red,green,blue);
					System.out.println(String.valueOf(color) + " location: " + GhostGUI.bounds);	//testing color
					//stop the timer when light is turned off
					if (!light.isVisible()){
						GhostGUI.bounds = 0;
						timer.stop();
					}
					//if the light hits the wall or it reaches it maximum, stop the timer and move light
					else if (red == 64 || GhostGUI.bounds == 50){
						light.setSize(GhostGUI.bounds, 20);
						light.setLocation(player.getX() - GhostGUI.bounds, player.getY());
						timer.stop();
						moveLight(light, player, GhostGUI.bounds, orientation);
						GhostGUI.bounds = 0;
						System.out.println(orientation);
					}
				}
				
			}
		});
		timer.start();
	}
	
	/**
	 * Method that keeps moving the light after checking for obstacles
	 * @param light
	 * @param player
	 * @param bounds
	 * @param orientation
	 */
	public static void moveLight(final ImagePanel light, final ImagePanel player, final int bounds, final int orientation){
		final Timer timer = new Timer (1, null);
		timer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int red; //the red value of a colour in RGB
				int point; //special point for the light to prevent an ArrayIndexOutOfBoundsException
				//if the light orientation is up
				if (orientation == 1){
					GhostGUI.upLightY = bounds;
					//keep the light moving with the player
					light.setLocation(player.getX() + 5, player.getY() - GhostGUI.upLightY);
					point = light.getY();
					//if the point is out of frame, set it into the frame
					if (point < 30){
						point = 31;
					}
					//get the colour of the previous pixel
					int c = GhostGUI.image.getRGB(light.getX() + 10, point);
					red = (c & 0x00ff0000) >> 16;
					//if the point is modified, then modify the red to another value to re-read the colour
					if (point == 31){
						red = 238;
					}
					//if colour isn't dark gray, check for colour again
					if (red != 64){
						timer.stop();
						getLightObstacles(light, player, orientation);
					}
				}
				//if the light orientation is right
				else if (orientation == 2){
					//keep the light moving with the player
					light.setLocation(player.getX() + 30, player.getY());
					point = light.getX() + bounds;
					//if the point is out of frame, set it into the frame
					if (point > 627){
						point = 627;
					}
					//get the colour of the previous pixel
					int c = GhostGUI.image.getRGB(point, light.getY() + 10);
					red = (c & 0x00ff0000) >> 16;
					//if the point is modified, then modify the red to another value to re-read the colour
					if (point == 627){
						red = 238;
					}
					//if colour isn't dark gray, check for colour again
					if (red != 64){
						timer.stop();
						getLightObstacles(light, player, orientation);
					}
				}
				//if the light orientation is down
				else if (orientation == 3){
					//keep the light moving with the player
					light.setLocation(player.getX() + 5, player.getY() + 32);
					point = light.getY() + bounds;
					//if the point is out of frame, set it into the frame
					if (point > 335){
						point = 337;
					}
					//get the colour of the previous pixel
					int c = GhostGUI.image.getRGB(light.getX() + 10, point);
					red = (c & 0x00ff0000) >> 16;
					//if the point is modified, then modify the red to another value to re-read the colour
					if (point == 337){
						red = 238;
					}
					//if colour isn't dark gray, check for colour again
					if (red != 64){
						timer.stop();
						getLightObstacles(light, player, orientation);
					}
				}
				//if the light orientation is left
				else if (orientation == 4){
					GhostGUI.leftLightX = bounds;
					//keep the light moving with the player
					light.setLocation(player.getX() - GhostGUI.leftLightX, player.getY());
					point = light.getX();
					//if the point is out of frame, set it into the frame
					if (point < 10){
						point = 5;
					}
					//get the colour of the previous pixel
					int c = GhostGUI.image.getRGB(point, light.getY() + 10);
					red = (c & 0x00ff0000) >> 16;
					//if the point is modified, then modify the red to another value to re-read the colour
					if (point == 5){
						red = 238;
					}
					//if colour isn't dark gray, check for colour again
					if (red != 64){
						timer.stop();
						getLightObstacles(light, player, orientation);
					}
				}
			}
		});
		timer.start();
	}
	
	/**
	 * Ghost grabs the player and drops them after a certain amount of time
	 * @param ghost
	 * @param player
	 */
	public static void ghostAction(final ImagePanel ghost,final ImagePanel player){
		//change the dead player's life to false
		if (player == GhostGUI.p1)
			GhostGUI.p1Life = false;
		else if (player == GhostGUI.p2)
			GhostGUI.p2Life = false;
		else if (player == GhostGUI.p3)
			GhostGUI.p3Life = false;
		else if (player == GhostGUI.p4)
			GhostGUI.p4Life = false;
		
		//x and y distances for player location
		final int playerX = ghost.getX() - player.getX();//-ve: player to right //+ve: player to left
		final int playerY = ghost.getY() - player.getY();//-ve: player below	//+ve: player above
		final long time = System.currentTimeMillis() + 3000;
		final Timer timer = new Timer (1, null);
		timer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//if distance between player and ghost is mostly vertical
				if (Math.abs(playerX) < Math.abs(playerY)){
					//if player is under ghost
					if (playerY <= 0){
						player.setLocation(ghost.getX(), ghost.getY() + 10);
					}
					//else if player is above
					else if (playerY > 0){
						player.setLocation(ghost.getX(), ghost.getY() - 10);
					}
				}
				//else if distance between player and ghost is mostly horizontal
				else if (Math.abs(playerY) < Math.abs(playerX)){
					//if player is to the right of the ghost
					if (playerX <= 0){
						player.setLocation(ghost.getX() + 10, ghost.getY());
					}
					//else if player is to the left of the ghost
					else if (playerX > 0){
						player.setLocation(ghost.getX() - 10, ghost.getY());
					}
				}
				
				//stop the player from moving
				KeyBinds.stopMovement(player);
				
				//after "end" amount of time, the timer stops
				if (System.currentTimeMillis() > time){
					System.out.println("FINISHED");	//debugging
					timer.stop();
				}
			}
		});
		timer.start();
	}
	
	/**
	 * method to revive the captured player
	 * @param deadPlayer
	 * @param player1
	 * @param player2
	 * @param player3
	 */
	public static void revive (final ImagePanel deadPlayer, final ImagePanel player1, final ImagePanel player2, 
								final ImagePanel player3){
		final Timer timer = new Timer(1250 ,null);
		timer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * All players left alive can heal the dead players. The more players healing, the faster the process
				 * goes. If the player is dead but is placed close enough to another dead player, they can't revive each other
				 */
				if ((player1.getX() <= deadPlayer.getX() + 20 && player1.getX() >= deadPlayer.getX() - 20) 
						&& (player1.getY() <= deadPlayer.getY() + 20 && player1.getY() >= deadPlayer.getY() - 20) && (GhostGUI.playerAlive(player1))){
					System.out.println("Player 1 reviving " + health);
					health+=5;
				}
				if ((player2.getX() <= deadPlayer.getX() + 20 && player2.getX() >= deadPlayer.getX() - 20) 
						&& (player2.getY() <= deadPlayer.getY() + 20 && player2.getY() >= deadPlayer.getY() - 20) && (GhostGUI.playerAlive(player2))){
					System.out.println("Player 2 reviving " + health);
					health+=5;
				}
				if ((player3.getX() <= deadPlayer.getX() + 20 && player3.getX() >= deadPlayer.getX() - 20) 
						&& (player3.getY() <= deadPlayer.getY() + 20 && player3.getY() >= deadPlayer.getY() - 20) && (GhostGUI.playerAlive(player3))){
					System.out.println("Player 3 reviving " + health);
					health+=5;
				}
				
				//if player is fully healed
				if (health > 100){
					System.out.println("timer stopped");
					timer.stop();
					health = 0;
					
					//revive the players and enable movement
					if (deadPlayer == GhostGUI.p1){
						GhostGUI.p1Life = true;
						System.out.println("P1: " + GhostGUI.p1Life);	//debug
						KeyBinds.restartMovement(GhostGUI.p1);
					}
					if (deadPlayer == GhostGUI.p2){
						GhostGUI.p2Life = true;
						System.out.println("P2: " + GhostGUI.p2Life);	//debug
						KeyBinds.restartMovement(GhostGUI.p2);
					}
					if (deadPlayer == GhostGUI.p3){
						GhostGUI.p3Life = true;
						System.out.println("P3: " + GhostGUI.p3Life);	//debug
						KeyBinds.restartMovement(GhostGUI.p3);
					}
					if (deadPlayer == GhostGUI.p4){
						GhostGUI.p4Life = true;
						System.out.println("P4: " + GhostGUI.p4Life);	//debug
						KeyBinds.restartMovement(GhostGUI.p4);
					}
				}
			}
		});
		timer.setDelay(1250);
		timer.start();
	}
	
	/**
	 * calculates the ghost's health after being hit by flashlights
	 * @param ghost
	 * @param light1
	 * @param light2
	 * @param light3
	 * @param light4
	 */
	public static void ghostHealth(final Rectangle2D ghost, final Rectangle2D light){	
		
		if (ghost.intersects(light)){
			System.out.println("intersection");
			final Timer drain = new Timer(500, null);

			drain.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					GhostGUI.healthLabel.setText(String.valueOf((Integer.parseInt(GhostGUI.healthLabel.getText()) - 1)));
					
					if (Integer.parseInt(GhostGUI.healthLabel.getText()) <= 0){
						GhostGUI.healthLabel.setText("0");
						drain.stop();
						System.out.println("Ghost dead");
						Time.stopTime();
					}
					if (ghost.intersects(light)){
						drain.stop();
						System.out.println("intersection removed");
					}
				}
			});
			drain.setDelay(500);
			drain.start();
		}
	}
}
