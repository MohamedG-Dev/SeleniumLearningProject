package selenium.Learning.SeleniumLearningProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ClassTwo {
	@Test
	public void methodTwoA() {
		System.out.println("inside methodTwoA of class Two");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://google.com/");
		driver.close();
	}

	@Test
	public void methodTwoB() {
		System.out.println("inside methodTwoB of class Two");
	}

	@Test
	public void methodTwoC() {
		System.out.println("inside methodTwoC of class Two");
	}
}
