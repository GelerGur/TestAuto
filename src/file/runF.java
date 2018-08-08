package file;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class runF {

	public static WebDriver driver;
//D:\\eclipse\\eclipse\\add\\geckodriver.exe  src\\add\\geckodriver.exe
	public static WebDriver getWebDriver() {
		//System.setProperty("webdriver.gecko.driver", "src\\add\\geckodriver.exe");
	//	driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
		return (driver);
	}

	public static void run() throws Throwable {
		ArrayList<String> id = fileReader.getId();
		getWebDriver();
		int wait = Integer.parseInt(fileReader.getDoc().getDocumentElement().getAttribute("wait"));

		for (int i = 0; i < id.size(); ++i) {

			ArrayList<String> url = fileReader.getUrlsId(i);
			System.out.println(url);
			System.out.println(i + 1);
			for (int u = 0; u < url.size(); ++u) {
				driver.get(url.get(u));
				waitForLoad(driver);

				List<WebElement> list = driver
						.findElements(By.cssSelector(".search-listings-group a[data-listing-id='" + id.get(i) + "']"));
				int p = 1;
				while (list.size() == 0) {
					try {
						if (p < 11) {
							System.out.println("������ Id: "+id.get(i)+" URL: "+url.get(u) +" ��� �� �������� � "+ p);
							driver.findElement(
									By.xpath("//div//span[@class='ss-icon ss-navigateright icon-smaller-lg icon-t-1']"))
									.click();
							list = driver.findElements(
									By.cssSelector(".search-listings-group a[data-listing-id='" + id.get(i) + "']"));
							++p;
						} else {
							break;
						}
					} catch (ElementClickInterceptedException e) {
						System.out.println("�������� ��� �� ���� ���������");
						break;

					}
				}

				if (list.size() > 0) {
					list.get(0).click();
					waitForLoad(driver);
					Thread.sleep(wait);
				}

			}

		}

		driver.close();

	}

	static void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	/// ����� ��������� ������� �������� �� �������� � ���������� true/false
	/// (����������/�� ����������).
	/// "iClassName" = By.Id("id"), By.CssSelector("selector") � �.�.
	/// </summary>

}