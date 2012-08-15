/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import java.awt.Image;
import java.util.Hashtable;
import javax.swing.ImageIcon;
import util.Location;

public class Base {
                
                public Image BaseImage;
		public String Base_Id;
		public Location loc;
		public String BaseImgPath;
		//public Hashtable<String, Base> htBase1 = null;
		
		public Base(String id, String imagePath, int xx, int yy)
                {
			 Base_Id = id;
		         loc = new Location(xx,yy);
		         BaseImgPath = imagePath;

		         ImageIcon ii =
		         new ImageIcon(this.getClass().getResource(BaseImgPath));
                         BaseImage = ii.getImage();
                        
		}
                 
	
}
