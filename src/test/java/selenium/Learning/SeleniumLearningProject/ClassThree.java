package selenium.Learning.SeleniumLearningProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ClassThree {
	@Test
	public void methodThreeA() {
		System.out.println("inside methodThreeA of class ClassThree");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://facebook.com/");
		driver.close();
	}

	@Test
	public void methodThreeB() {
		System.out.println("inside methodThreeB of class ClassThree");
	}

	@Test
	public void methodThreeC() {
		System.out.println("inside methodThreeC of class ClassThree");
	}
}
