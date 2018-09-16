package file;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class runF {

	public static WebDriver driver;
	// Драйвера
	// D:\\eclipse\\eclipse\\add\\geckodriver.exe src\\add\\geckodriver.exe

	public static WebDriver getWebDriver() {
		// FirefoxDriver
		// System.setProperty("webdriver.gecko.driver", "src\\add\\geckodriver.exe");
		// driver = new FirefoxDriver();

		// ChromeDriver
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
				// Закрытие попапа уведомления о кукис
				try {
					Thread.sleep(wait);
					driver.findElement(By.cssSelector("[class='width-full btn btn-outline btn-outline-black']"))
							.click();

				} catch (ElementNotVisibleException e) {
				}

				List<WebElement> list = driver.findElements(By.cssSelector("a[data-listing-id='" + id.get(i) + "']"));
				int p = 1;

				// Поиск товара на текущей странице
				while (list.size() == 0) {
					try {
						if (p < 11) {
							System.out.println(
									"Товара Id: " + id.get(i) + " URL: " + url.get(u) + " нет на странице № " + p);
							driver.findElement(
									By.cssSelector("[class='ss-icon ss-navigateright icon-smaller-lg icon-t-1']"))
									.click();
							list = driver.findElements(By.cssSelector("[data-palette-listing-id='" + id.get(i) + "']"));
							++p;
						} else {
							break;
						}
					} catch (ElementClickInterceptedException e) {
						System.out.println("Элимента нет на всех страницах");
						break;

					}  catch (WebDriverException e) {
						System.out.println("Элимента нет на всех страницах");
						break;
					}
					
					
				}

				if (list.size() > 0) {

					waitForLoad(driver);
					list.get(0).click();
					Thread.sleep(wait);
					// Переключение между вкладками
					ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());// Получение списка табов
					driver.switchTo().window(tabs2.get(1));// Переключение на второй таб в браузере
					driver.close();
					driver.switchTo().window(tabs2.get(0));// Переключение на первый таб в браузере

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

}
