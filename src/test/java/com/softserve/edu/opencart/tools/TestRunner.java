package com.softserve.edu.opencart.tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.softserve.edu.opencart.pages.HomePage;

public abstract class TestRunner {
	protected WebDriver driver;
	protected final double PRECISION = 0.001;
	
	@BeforeClass
    public void beforeClass(ITestContext context) {
        System.out.println("@BeforeClass");
		System.out.println("PATH to Driver: " +
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
		System.setProperty("webdriver.chrome.driver",
				this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
	    driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("@AfterClass");
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("@BeforeMethod");
		driver.get("http://atqc-shop.epizy.com/");
    }

    @AfterMethod//(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        System.out.println("@AfterMethod");
    }

    protected HomePage loadApplication() {
        return new HomePage(driver);
    }

    protected void delayExecution(long miliseconds) {
        try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			System.err.println("Cannot thread sleep!");
		}
    }

}
