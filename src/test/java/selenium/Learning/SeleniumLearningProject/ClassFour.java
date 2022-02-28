package selenium.Learning.SeleniumLearningProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ClassFour {
	@Test
	public void methodFourA() {
		System.out.println("inside methodFourA of class ClassFour");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://yahoo.com/");
		driver.close();
	}

	@Test
	public void methodFourB() {
		System.out.println("inside methodFourB of class ClassFour");
	}

	@Test
	public void methodFourC() {
		System.out.println("inside methodFourC of class ClassFour");
	}
}
