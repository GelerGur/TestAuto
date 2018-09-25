package file;
import java.io.*;

import org.w3c.dom.Element;

@SuppressWarnings("unused")

public class Main {

   public static void main(String[] args) throws Throwable {
	   
	  FileWriter.setPropertysTrue(CombineLinks.Doc());
	   
	   int cycle = Integer.parseInt(FileReader.getDoc().getDocumentElement().getAttribute("cycle"));
	   while (cycle>0) {
	CombineLinks.combine();
	RunF.run();
	--cycle;
	System.out.println("Осталось "+cycle+" круга"); 
	   }
	}
	
	
	
	
	
	
	
}


