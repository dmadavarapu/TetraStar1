/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Preshu
 */
import Source.*;
import util.Location;
import util.Cryptography;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class Star extends JComponent implements ActionListener, Imap {

	private static final long serialVersionUID = 1L;
	
    public Image star;
    public Location loc;
    public String direction;
	public String mapImgPath;
	public boolean bStolen = false;
	public boolean bEncrypted = false;

    public Star(String imagePath, int xx, int yy, String dir) {
        loc = new Location(xx,yy);
        direction = dir;
        mapImgPath = imagePath;

        ImageIcon ii =
            new ImageIcon(imagePath);
        star = ii.getImage();

        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(star, loc.myRow, loc.myCol, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
 
        loc.myRow += 1;
        loc.myCol += 1;

        if (loc.myCol > 240) {
        	loc.myCol = -45;
            loc.myRow = -45;
        }
        repaint();  
    }
    
    @Override
	public boolean isEncrypted(){
		return true;
	}
	
    @Override
	public boolean isStolen(){
		return bStolen;
	}
	
    @Override
	public void Encrypt(){
		bEncrypted = true;
		direction = Cryptography.encrypt(direction);
	}
    @Override
	public void Decrypt(){
		direction = Cryptography.decrypt(direction);
		
	}
    @Override
	public void Steal(){
		bStolen = true;
		
	}
    @Override
	public void DisplayInfo(){
		
	}
}