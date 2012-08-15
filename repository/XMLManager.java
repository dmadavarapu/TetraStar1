/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Source.Base;
import Source.StarAtlas;
import Source.StarMaps;
import java.io.File;
import java.util.Hashtable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

 
public class XMLManager {
	
	public Hashtable<String, StarMaps> htStarMaps = new Hashtable<String, StarMaps>(); 
	public Hashtable<String, StarAtlas> htStarAtlas = new Hashtable<String, StarAtlas>(); 
        public Hashtable<String, Base> htBase = new Hashtable<String, Base>();
	
	public Hashtable<String, StarMaps> GetStarMaps()
	{
		return htStarMaps;
	}
	
	public Hashtable<String, StarAtlas> GetStarAtlas()
	{
		return htStarAtlas;
	}
        public Hashtable<String, Base> GetBase() 
        {
                return htBase;
        }
        

	public void LoadXML() {
	 try {
 
		File fXmlFile = new File("src/Resources/ResourceProperties.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
 
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		PopulateStarMaps(doc);
		PopulateStarAtlas(doc);
                PopulateBase(doc);
	
	  } catch (Exception e) {
		e.printStackTrace();
	  }
  }
	
 public void PopulateStarMaps(Document doc){
	 String starId;
	 String imagePath;
	 int x;
	 int y;
	 String direction;
	 
	 
	 try{
		 
		 NodeList nList1 = doc.getElementsByTagName("starmaps");
			NodeList nList = nList1.item(0).getChildNodes();
			System.out.println("-----------------------");
	 
			for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			   Node nNode = nList.item(temp);
			   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
			      Element eElement = (Element) nNode;
			      
			     starId = getTagValue("id", eElement);
			 	 imagePath = getTagValue("path", eElement);
			 	 x = Integer.parseInt(getTagValue("x", eElement));
			 	 y = Integer.parseInt(getTagValue("y", eElement));
			 	 direction = getTagValue("direction", eElement);
			 	 
			 	 StarMaps sMap = new StarMaps(starId, imagePath, x, y, direction);
			 	 htStarMaps.put(starId, sMap);
			      
			     
			      System.out.println("Star id : " + starId);
			      System.out.println("Y : " + y);
                              System.out.println("X : " + x);
			      System.out.println("direction : " + direction);
			      System.out.println("path : " + imagePath);
			   }
			}
		 
	 } catch (Exception e) {
			e.printStackTrace();
	}
 }
 
 public void PopulateStarAtlas(Document doc){
	
	 String atlasId;
	 String imagePath;
	 int x;
	 int y;
	 String direction;
	 String star;
	 Hashtable<String, StarMaps> htStar = new Hashtable<String, StarMaps>(); 
	 
	 NodeList nList1 = doc.getElementsByTagName("staratlas");
		NodeList nList = nList1.item(0).getChildNodes();
		System.out.println("-----------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

		   Node nNode = nList.item(temp);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		       	Element eElement = (Element) nNode;
		      
		       	atlasId = getTagValue("id", eElement);
			 	 imagePath = getTagValue("path", eElement);
			 	 x = Integer.parseInt(getTagValue("x", eElement));
			 	 y = Integer.parseInt(getTagValue("y", eElement));
			 	 direction = getTagValue("direction", eElement);
		   
                   
		   if(nNode.hasChildNodes())
		   {
			   NodeList nChildList = nNode.getLastChild().getChildNodes();
			   for (int ctemp = 0; ctemp < nChildList.getLength(); ctemp++) {

				   Node nChildNode = nChildList.item(ctemp);
				   if (nChildNode.getNodeType() == Node.ELEMENT_NODE) {

				       	Element eChildElement = (Element) nChildNode;
				       	star = getTagValue("star", eChildElement);
				       	if(htStarMaps != null)
				       	{
				       		htStar.put(star, htStarMaps.get(star));
				       	}
				   }
			   }
		    }
		   	 StarAtlas sAtlas = new StarAtlas(atlasId, imagePath, x, y, direction,htStarMaps);
		   	 htStarAtlas.put(atlasId, sAtlas);
		   }
		}
 }
 public void PopulateBase(Document doc)
 {
         String BaseId;
	 String imagePath;
	 int x;
	 int y;
                 	 
	 NodeList nList1 = doc.getElementsByTagName("base");
		NodeList nList = nList1.item(0).getChildNodes();
		System.out.println("-----------------------");
                //System.out.println(nList.getLength());
                
                for(int temp = 0; temp < nList.getLength(); temp++)
                {
                       NodeList nList2 = nList.item(temp).getChildNodes();
                       for(int cnt=0 ; cnt < nList2.getLength(); cnt++)
                       {
                                Node child = nList2.item(cnt);
                                if(child.getNodeType() == Node.ELEMENT_NODE)
                                {
                                            Element eElement = (Element) child;

                                            BaseId = getTagValue("id", eElement);
                                            imagePath = getTagValue("path", eElement);
                                            x = Integer.parseInt(getTagValue("x", eElement));
                                            y = Integer.parseInt(getTagValue("y", eElement));
                                                                                        
                                             System.out.println("BaseId : " + BaseId);
                                             System.out.println("Y : " + y);
                                             System.out.println("X : " + x);
                                             System.out.println("path : " + imagePath);
                                            
                                            Base b = new Base(BaseId,imagePath,x,y);
                                            htBase.put(BaseId,b);
                                                                   
                                }
                       } 
                }

		
 }
 
 
  private static String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	Node nValue = (Node) nlList.item(0);
	return nValue.getNodeValue();
  }

  
 
}

    

