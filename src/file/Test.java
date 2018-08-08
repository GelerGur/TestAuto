package file;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Test  {
 
   public static void main(String[] args) throws Throwable {
	  
	   
	   ArrayList <String> urlShop= new ArrayList <String>();
	   urlShop.add("https://www.etsy.com/search?q=Thuja+leaf&min=58.67&max=58.67");
	   urlShop.add("https://www.etsy.com/search?q=Metal+necklace&min=58.67&max=58.67");
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument(); 
			
			Element propertys = document.createElement("propertys");
	        document.appendChild(propertys);
			
	       
	        propertys.setAttribute("prepareProducts", "false");
	        propertys.setAttribute("shop",fileReader.getDoc().getDocumentElement().getAttribute("shop"));
	        propertys.setAttribute("wait", "15000");
	        Element links = document.createElement("links");
	        propertys.appendChild(links);
	        links.setAttribute("id", "25434524");
	        for(int i=0; i < urlShop.size(); ++i) {
	        	      	
	            
	           
	        	Element link = document.createElement("link");
	            link.setTextContent(urlShop.get(i));
	            links.appendChild(link);
	            
	            
	           
	        }
	         Transformer transformer = TransformerFactory.newInstance().newTransformer();
	            DOMSource source = new DOMSource(document);
	            StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + File.separator + "Test.xml"));
		
	            transformer.transform(source, result);
	            
	            System.out.println("Документ сохранен!");
			
		}
//	tag.
 //  System.out.println(tag);
   
   
   
   
   
   
   
   public static  ArrayList<String> UrlShopMaker (ArrayList<String> tag, String currencyValue) {
	   ArrayList <String> urlShop = new ArrayList <String>();
	  // String mask="https://www.etsy.com/search?q=%1$s&min=%2$s&max=%2$s";
	   
	 for(int i=0; i<tag.size(); ++i) {
		 String mask="https://www.etsy.com/search?q=%1$s&min=%2$s&max=%2$s";
		 urlShop.add(String.format(mask, tag.get(i), currencyValue)); 
	 }
	   
	   return (urlShop);
   }
 
}
