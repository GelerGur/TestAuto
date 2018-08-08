package file;
import java.io.*;
import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class fileReader {

	
	//Чтение текста с файла
	public static void readerIo() throws Exception {		
		FileReader readIo = new FileReader ("TextFile.txt");			
		int c;
        while((c=readIo.read())!=-1){             
            System.out.print((char)c);
        }         
		readIo.close();
	}
	
	
	
	//Чтение с xml файла взято тут:
	//https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
	//https://javaswing.wordpress.com/2010/03/14/java_dom_xml/
public static void xmlReder() throws Exception {
	
	//	System.out.println(getDoc().getDocumentElement().getNodeName());
		NodeList nList = getDoc().getElementsByTagName("links");
     //   System.out.println("----------------------------");
        
       
        
        for (int temp = 0; temp < nList.getLength(); temp++) {
           Node nNode = nList.item(temp);
         //  System.out.println(nNode.getNodeName());
           
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
              Element eElement = (Element) nNode;
              
              System.out.println("id : " + eElement.getAttribute("id"));           
              System.out.println("link : "+ eElement.getElementsByTagName("link").item(0).getTextContent());
            
            
            //    System.out.print(nameAttrib.getNodeValue());
            
            
            
           }
        }
				
	}


//Разделение текста на блоки по Заглавные буквы.
public static ArrayList<String> textCut(String string) {    
   
	  string =string.trim();	  
	   String dI= string.toLowerCase();	 
	   String out = "";
	   ArrayList<String> tag = new ArrayList<String>();
	   for 	(int i=0; i< string.length(); ++i) {
	   	  if (string.charAt(i)==dI.charAt(i)){
	   		out= out +string.charAt(i);  
	   	  }
	   		else {
	   			out=out.trim();
	   			out=out.replace(" ", "+");	  
	   			tag.add(out);	   		
	   			out= ""+string.charAt(i);
	   		}
	   	 
	   }
	  tag.remove(0);
	   return (tag);

}

//Чтение атрибута prepareProducts с документа и возврат флага.
public static boolean prepare() throws Exception  {
	boolean pp = Boolean.parseBoolean(getDoc().getDocumentElement().getAttribute("prepareProducts"));
	if (pp==true) {
		return (true);
	}
	else {
		return (false);
	}
}		


	//Чтение файла и возврат документа
	public static Document  getDoc() throws Exception {
		File file = new File ("Test.xml");	
		
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		f.setValidating(false);//?
		DocumentBuilder builder = f.newDocumentBuilder();
		Document document = builder.parse(file);
		document.getDocumentElement().normalize();
		
		return (document);
	}
	
	
	//	Возвращает список Id	
	public static ArrayList<String> getId() throws Exception {
		
		NodeList nList = getDoc().getElementsByTagName("links");
		ArrayList <String> listId = new ArrayList <String>(); 	        
		
	        
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	           Node nNode = nList.item(temp);
	         
	           
	           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	              Element eElement = (Element) nNode; 
	            listId.add(eElement.getAttribute("id"));	                    
	    }
	          
	   } 
	      
	        return (listId);
	}
	
	
	
	public static ArrayList<String> getUrlsId(int numId) throws Exception {
		NodeList nList = getDoc().getElementsByTagName("links");
		ArrayList <String> urlsList = new ArrayList <String>(); 
		
		Node nNode = nList.item(numId);
		
		Element eElement = (Element) nNode; 
		
		
		NodeList urlList = eElement.getChildNodes();
        
        for (int urls=0; urls<urlList.getLength(); urls++) {  
        	 Node urlNode = urlList.item(urls);
        	
        	if (urlNode.getNodeType() == Node.ELEMENT_NODE)
        		urlsList.add(urlList.item(urls).getTextContent());     
        
        }
		
		return (urlsList);
	}
	
	
	
	
}



	

