/**
 * 
 */
package file;





import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

//import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;

/**
 * @author GelerGur
 *
 */
public class combineLinks {
	 public static WebDriver driver;
	/**
	 * @throws Throwable 
	 */
	 public static Document Doc() throws Throwable{
		 Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument(); 
		 return (document);
	 }
	 
	public static void combine() throws Throwable {
		// TODO Auto-generated method stub
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument(); 		
		fileWriter.setPropertys(document);
		
		
		//проверка на установленый флаг
		if(fileReader.prepare()==true) {
			fileWriter.setPropertys(Doc());
			//Открытие магазина по ссылке из файла
			driver=runF.getWebDriver();
			driver.get(fileReader.getDoc().getDocumentElement().getAttribute("shop"));
			List <WebElement> list = driver.findElements(By.cssSelector("[data-palette-listing-id]"));
			Iterator <WebElement> iterator = list.iterator();
			while(iterator.hasNext()) {				
				WebElement element = iterator.next();
				List <WebElement> targetEls = element.findElements(By.cssSelector(".v2-listing-card__info p"));
				String description = targetEls.get(0).getText();
				String currencyValue = element.findElements(By.cssSelector(".currency-value")).get(0).getText();
			
				currencyValue = currencyValue.replace(",", ".");
				double	currencyValueN = Double.parseDouble(currencyValue);
				currencyValueN= currencyValueN - 0.01;
				currencyValue= Double.toString(currencyValueN);
				
				String id = element.getAttribute("data-palette-listing-id");			
				ArrayList <String> urlShop = UrlShopMaker(fileReader.textCut(description), currencyValue);
				System.out.println(urlShop);
				System.out.println("id: " + id);
				fileWriter.setUrl(urlShop, document, id);
			}
			fileWriter.saveDoc(document);
			driver.close();
		}
		else {
			System.out.println("Анализ магазина не проводится");
			}
	}
//					
//			//Цикл формирования ссылок поиска товаров.  currency-value
//			for (int i=1; i<30; i++) {
//				
//				
//				ArrayList <String> urlShop= new ArrayList <String>();
//				String mask= "div.js-merch-stash-check-listing:nth-child("+ i +")";
//				List <WebElement> list;
//				list= null;
//				String currencyValue=null;
//										
//				list = driver.findElements(By.cssSelector(mask));
//				[data-palette-listing-id]
//				
//				//System.out.println(i);
//				//div.js-merch-stash-check-listing:nth-child(1) > a:nth-child(1) > div:nth-child(2) > div:nth-child(1) > p:nth-child(1)
//				int r =	list.size();
//				list.get(index)
//									
//				
//				if (r!=0) {
//					
//					WebElement element = driver.findElement(By.xpath("html/body/div[5]/div[4]/div/div/div[3]/div[2]/div/div["+i+"]/a/div[2]/div/p[2]/span[2]"));
//					currencyValue= element.getText();
//					element= driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div/div[3]/div[2]/div/div["+i+"]/a/div[2]/div/p[1]"));
//					urlShop= UrlShopMaker(fileReader.textCut(element.getText()), currencyValue);
//					System.out.println(urlShop);
//					fileWriter.setUrl(urlShop, document);
//					
//				}
//				else {
//					break;
//				}
//				
//				
//			}
//			
			
			
		
		
		
	
	
	 public static  ArrayList<String> UrlShopMaker (ArrayList<String> tag, String currencyValue) {
		   ArrayList <String> urlShop = new ArrayList <String>();
		
		   
		 for(int i=0; i<tag.size(); ++i) {
		//	 String mask="https://www.etsy.com/search?q=%1$s&min=%2$s&max=%2$s&order=price_asc";
				
			 String mask="https://www.etsy.com/search?q=%1$s&min=%2$s&order=price_asc";
			 urlShop.add(String.format(mask, tag.get(i), currencyValue)); 
		 }
		   
		   return (urlShop);
	   }
	 
}
