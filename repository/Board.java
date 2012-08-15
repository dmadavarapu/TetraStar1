/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Source.Base;
import Source.StarAtlas;
import Source.StarMaps;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JComponent;
import util.XMLManager;

public class Board extends JComponent implements ActionListener {

	private static final long serialVersionUID = 1L;
	Hashtable<String, StarMaps> htStarMaps = null;
	Hashtable<String, StarAtlas> htAtlas = null;
        Hashtable<String, Base> htBase = null;

        public Board() {
            XMLManager xParser = new XMLManager();
            xParser.LoadXML();
            htStarMaps = xParser.GetStarMaps();
            htAtlas = xParser.GetStarAtlas();
            htBase = xParser.GetBase();
            setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        Enumeration<String> keys = htStarMaps.keys();
        while( keys.hasMoreElements() ) {
          String key = keys.nextElement().toString();
          StarMaps map = htStarMaps.get(key);
          g2d.drawImage(map.star, map.loc.myRow, map.loc.myCol, this);
          }
        
        Enumeration<String> keysAtlas = htAtlas.keys();
        while( keysAtlas.hasMoreElements() ) {
          String key = keysAtlas.nextElement().toString();
          StarAtlas map = htAtlas.get(key);
          g2d.drawImage(map.star, map.loc.myRow, map.loc.myCol, this);
          }
        
         Enumeration<String> keysBase = htBase.keys();
         while( keysBase.hasMoreElements() ) {
          String key = keysBase.nextElement().toString();
          Base map = htBase.get(key);
          g2d.drawImage(map.BaseImage, map.loc.myRow, map.loc.myCol, this);
          }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();  
    }

   
}
