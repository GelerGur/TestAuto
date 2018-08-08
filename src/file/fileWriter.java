package file;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class fileWriter {

	 private static final String FILENAME = "Test.xml";
	
	public static void writerIn() throws Exception{
		
		FileWriter FileIO = new FileWriter ("TextFile.txt");
		
		FileIO.write("First word\nThe wold is myne\n");
		
		
		FileIO.close();
		System.out.println("File OK");
	}
	
	/*
	 *   */
	public static void writerXML() throws Exception{
		
		
	Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
 
            // �������� �������
            Element propertys = document.createElement("propertys");
            document.appendChild(propertys);
 
            // ������� ���� wait
            Element wait = document.createElement("wait");
            wait.setTextContent("1000");
            propertys.appendChild(wait);
 
            /*/ ���������� ����� �������� � ��
            Attr sec = document.createAttribute("sec");
            sec.setTextContent("1");
            wait.setAttributeNode(sec);
 
            */// ��� ����� ������� ���
            // staff.setAttribute("id", "1");
             
            // ���������� ������ link
            Element links = document.createElement("links");
           
            propertys.appendChild(links);
            
            Element link = document.createElement("link");
            link.setTextContent("http:\\");
            links.appendChild(link);
            
            
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + File.separator + FILENAME));
	
            transformer.transform(source, result);
            
            System.out.println("�������� ��������!");
             
    
            
	}
	
	public static Document setPropertys (Document document) throws Throwable{
		
		//Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument(); 
		
		Element propertys = document.createElement("propertys");
        document.appendChild(propertys);
		
       
        propertys.setAttribute("prepareProducts", "false"); //false
        propertys.setAttribute("shop",fileReader.getDoc().getDocumentElement().getAttribute("shop"));
        propertys.setAttribute("wait", fileReader.getDoc().getDocumentElement().getAttribute("wait"));
        propertys.setAttribute("cycle", fileReader.getDoc().getDocumentElement().getAttribute("cycle"));
        return (document);
        
	}
	
public static void setPropertysTrue (Document document) throws Throwable{
		
		//Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument(); 
		
		Element propertys = document.createElement("propertys");
        document.appendChild(propertys);
		
       
        propertys.setAttribute("prepareProducts", "true"); //false
        propertys.setAttribute("shop",fileReader.getDoc().getDocumentElement().getAttribute("shop"));
        propertys.setAttribute("wait", fileReader.getDoc().getDocumentElement().getAttribute("wait"));
        propertys.setAttribute("cycle", fileReader.getDoc().getDocumentElement().getAttribute("cycle"));
        
        saveDoc(document);
        
        
	}
	
	

        
        public static void saveDoc (Document document) throws Throwable{
        	
        	Transformer transformer = TransformerFactory.newInstance().newTransformer();
        	transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        	
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + File.separator + "Test.xml"));
	
            transformer.transform(source, result);
            
            System.out.println("�������� ��������!");
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
