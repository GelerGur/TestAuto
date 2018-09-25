package file;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class FileWriter {

	 private static final String FILENAME = "Test.xml";
	

	/*
	 *   */
	public static void writerXML() throws Exception{
		
		
	Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
 
            // Корневой элемент
            Element propertys = document.createElement("propertys");
            document.appendChild(propertys);
 
            // Элемент типа wait
            Element wait = document.createElement("wait");
            wait.setTextContent("1000");
            propertys.appendChild(wait);
                         
            // Определяем ссылка link
            Element links = document.createElement("links");
           
            propertys.appendChild(links);
            
            Element link = document.createElement("link");
            link.setTextContent("http:\\");
            links.appendChild(link);
            
            
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + File.separator + FILENAME));
	
            transformer.transform(source, result);
            
            System.out.println("Документ сохранен!");
             
    
            
	}
//Заполнение шапки документа	
	public static Document setPropertys (Document document) throws Throwable{
		
		Element propertys = document.createElement("propertys");
        document.appendChild(propertys);
		
       
        propertys.setAttribute("prepareProducts", "false"); //false
        propertys.setAttribute("shop",FileReader.getDoc().getDocumentElement().getAttribute("shop"));
        propertys.setAttribute("wait", FileReader.getDoc().getDocumentElement().getAttribute("wait"));
        propertys.setAttribute("cycle", FileReader.getDoc().getDocumentElement().getAttribute("cycle"));
        return (document);
        
	}
//Сохранение шапки документа	
public static void setPropertysTrue (Document document) throws Throwable{
		
		Element propertys = document.createElement("propertys");
        document.appendChild(propertys);
		
       
        propertys.setAttribute("prepareProducts", "true"); //false
        propertys.setAttribute("shop",FileReader.getDoc().getDocumentElement().getAttribute("shop"));
        propertys.setAttribute("wait", FileReader.getDoc().getDocumentElement().getAttribute("wait"));
        propertys.setAttribute("cycle", FileReader.getDoc().getDocumentElement().getAttribute("cycle"));
        
        saveDoc(document);
        
        
	}
	
	

        
        public static void saveDoc (Document document) throws Throwable{
        	
        	Transformer transformer = TransformerFactory.newInstance().newTransformer();
        	transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        	
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + File.separator + "Test.xml"));
	
            transformer.transform(source, result);
            
            System.out.println("Документ сохранен!");
        }
        
	
	public static Document setUrl (ArrayList<String> urlShop, Document document, String id) throws Throwable{
		

		Node propertys = document.getChildNodes().item(0);
		
		
		  Element links = document.createElement("links");
	      propertys.appendChild(links);
		        links.setAttribute("id", id);
		        
		   for(int i=0; i < urlShop.size(); ++i) {
   	      	
	            Element link = document.createElement("link");
	            link.setTextContent(urlShop.get(i));
	            links.appendChild(link);
	                      
	        }
		   
		   return (document);
	}
	
}

