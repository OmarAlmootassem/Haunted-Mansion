/*-------------------------------------------------------------------
 * Author:			Omar Almootassem
 * Written:			Oct/24/2014
 * Last Updated:	Oct/24/2014
 * 
 * A custom JPanel that is used to display all of the images in
 * the game
 ------------------------------------------------------------------*/

package backgroundProcesses;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	
	private Image img;
	
	public ImagePanel (String img) {
		this(new ImageIcon(img).getImage());
	}
	
	/**
	 * Creates the ImagePanem
	 * @param img the image used
	 */
	public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	/**
	 * paints the image onto the ImagePanel
	 * @param g  the graphics used
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
