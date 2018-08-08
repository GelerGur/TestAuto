package file;
import java.io.*;

import org.w3c.dom.Element;

@SuppressWarnings("unused")

public class main {

   public static void main(String[] args) throws Throwable {
	   
	  fileWriter.setPropertysTrue(combineLinks.Doc());
	   
	   int cycle = Integer.parseInt(fileReader.getDoc().getDocumentElement().getAttribute("cycle"));
	   while (cycle>0) {
	combineLinks.combine();
	runF.run();
	--cycle;
	System.out.println("Осталось "+cycle+" круга"); 
	   }
	}
	
	
	
	
	
	
	
}


