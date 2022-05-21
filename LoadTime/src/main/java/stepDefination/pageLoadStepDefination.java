package stepDefination;

import org.junit.Assert;
//import java.util.concurrent.TimeUnit;
//import java.io.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
//import junit.framework.Assert;
import cucumber.api.java.en.*;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class pageLoadStepDefination {
	
	WebDriver driver;
	Wait<WebDriver> wait;
	long loginpagetime = 0;
	
	@Given("^User is already on Login page$")
	public void user_is_already_on_Login_page() {
		System.setProperty("webdriver.chrome.driver", "path to webdriver\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("link of website");
	}

	@When("^Title of login page is Personal Info$")
	public void title_of_login_page_is_Personal_Info() {
		String title = driver.getTitle();
		System.out.println("First iteration load time:");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Birth Date year\"]")));
		Assert.assertEquals("Personal Information - Login",title);
	}
	
	@Then("^Record load time$")
	public void record_load_time() {
		Long navigationStart = (Long)((JavascriptExecutor) driver).executeScript("return window.performance.timing.navigationStart");
//		Long responseStart = (Long)((JavascriptExecutor) driver).executeScript("return window.performance.timing.responseStart");
		Long domComplete = (Long)((JavascriptExecutor) driver).executeScript("return window.performance.timing.domComplete");
//		Long backendPerformance_calc = responseStart - navigationStart;
//		Long frontendPerformance_calc = domComplete - responseStart;
		Long totalTime_calc = domComplete - navigationStart;
//		System.out.println("1 iteration back end time: " + backendPerformance_calc  +"ms");
//		System.out.println("1 iteration front end time: " + frontendPerformance_calc  +"ms");
		System.out.println("Login Page load time: " + totalTime_calc  +"ms");
	}
	
	@Then("^Refresh Page and record load time$")
	public void refresh_page_and_record_load_time() {
		driver.navigate().refresh();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Birth Date year\"]")));
		Long navigationStart = (Long)((JavascriptExecutor) driver).executeScript("return window.performance.timing.navigationStart");
//		Long responseStart = (Long)((JavascriptExecutor) driver).executeScript("return window.performance.timing.responseStart");
		Long domComplete = (Long)((JavascriptExecutor) driver).executeScript("return window.performance.timing.domComplete");
//		Long backendPerformance_calc = responseStart - navigationStart;
//		Long frontendPerformance_calc = domComplete - responseStart;
		Long totalTime_calc = domComplete - navigationStart;
//		System.out.println("2 iteration back end time: " + backendPerformance_calc  +"ms");
//		System.out.println("2 iteration front end time: " + frontendPerformance_calc  +"ms");
		loginpagetime = totalTime_calc;
	}
	
	Calendar cal;
	Calendar cal2;
	Date d1;
	Date d2;
	long diffMilliSeconds = 0;
	long dashboardloadtime = 0;

	@Then("^User logs in with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" and record load time$")
	public void user_logs_in_with_and_and_record_load_time(String firstname, String lastname, String bookingnumber, String month, String day, String year) {
		driver.findElement(By.id("firstname-text")).sendKeys(firstname);
		driver.findElement(By.id("lastname-text")).sendKeys(lastname);
		driver.findElement(By.id("bookingNumber-text")).sendKeys(bookingnumber);
		Select drpMonth = new Select(driver.findElement(By.id("Birth Date month")));
		drpMonth.selectByVisibleText(month);
		Select drpDate = new Select(driver.findElement(By.id("Birth Date Date")));
		drpDate.selectByVisibleText(day); 
		//new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Birth Date year\"]")));
		driver.findElement(By.id("Birth Date year")).sendKeys(year);
		
		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label='Access your booking']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(80)).until(ExpectedConditions.presenceOfElementLocated(By.className("listImageIcon")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Dashboard load time: " + diffMilliSeconds +"ms");
		}
	
	@Then("^Logout and logs in again with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" and record load time$")
	public void logout_and_logs_in_again_and_record_load_time(String firstname, String lastname, String bookingnumber, String month, String day, String year) {
		
		//Logout
		driver.findElement(By.cssSelector("[aria-label='Logout']")).click();
		driver.findElement(By.id("logout-span-id")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Birth Date year\"]")));

		//Logs in again
		driver.findElement(By.id("firstname-text")).sendKeys(firstname);
		driver.findElement(By.id("lastname-text")).sendKeys(lastname);
		driver.findElement(By.id("bookingNumber-text")).sendKeys(bookingnumber);
		Select drpMonth = new Select(driver.findElement(By.id("Birth Date month")));
		drpMonth.selectByVisibleText(month);
		Select drpDate = new Select(driver.findElement(By.id("Birth Date Date")));
		drpDate.selectByVisibleText(day); 
		//new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Birth Date year\"]")));
		driver.findElement(By.id("Birth Date year")).sendKeys(year);
		
		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label='Access your booking']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(80)).until(ExpectedConditions.presenceOfElementLocated(By.className("listImageIcon")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		dashboardloadtime = d2.getTime() - d1.getTime();
	}
	
	
	@Then("^Go to Personal Info and record load time$")
	public void go_to_personal_info_and_record_load_time() {
		cal = Calendar.getInstance();
//		driver.findElement(By.cssSelector("[aria-label=\"Personal Information required  of Jennifer step Incomplete \"]")).click();
		driver.findElement(By.xpath("//a[text()='Personal Information']")).click();
//		String text = driver.findElement(By.xpath("//*[@class='mainPayHeader']/h1")).getAttribute("innerHTML");
//		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.attributeContains(By.xpath("//*[@class='mainPayHeader'][contains(.,'Personal Information')]"), "class", "mainPayHeader"));
//		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(By.id("middlename_parent_div2")));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Personal Info Page load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Payment method and record load time$")
	public void go_to_payment_method_and_record_load_time(){
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Payment Method']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Payment method load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Passage contract and record load time$")
	public void go_to_passage_contract_and_record_load_time(){
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Parental Consent' or text()='Passenger Contract']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Passenger contract load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Travel Documents and record load time$")
	public void go_to_travel_documents_and_record_load_time(){
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Travel Documents']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Travel documents load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Security Photo and record load time$")
	public void go_to_security_photo_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Security Photo']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Security photo load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Emergency return airport and record load time$")
	public void go_to_emergency_return_airport_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Emergency Return Airport']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Emergency return airport load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Travel information and record load time$")
	public void go_to_travel_information_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Travel Information']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Travel information load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}

	@Then("^Go to Add a celebration and record load time$")
	public void go_to_add_a_celebration_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Add a Celebration']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Add a celebration load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));
		
		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}

	@Then("^Go to Profile photo and record load time$")
	public void go_to_profile_photo_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Profile Photo']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Profile photo load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}

	@Then("^Go to Medallion ordering and record load time$")
	public void go_to_medallion_ordering_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Choose your Medallion� wearable']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Medallion Ordering load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));
		
		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}

	@Then("^Go to Health status and record load time$")
	public void go_to_health_status_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Health Status']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
//		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Health Status']")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Health status load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	//Second iteration
	@Then("^Starting second iteration$")
	public void starting_second_iteration() {
		System.out.println("");
		System.out.println("Second iteration load time:");
		System.out.println("Login Page load time: " + loginpagetime  +"ms");
		System.out.println("Login Page load time: " + dashboardloadtime  +"ms");
	}
	
	@Then("^Go to Personal Info again and record load time$")
	public void go_to_personal_info_again_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Personal Information']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Personal Info Page load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));
		
		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Payment method again and record load time$")
	public void go_to_payment_method_again_and_record_load_time(){
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Payment Method']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Payment method load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Passage contract again and record load time$")
	public void go_to_passage_contract_again_and_record_load_time(){
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Parental Consent' or text()='Passenger Contract']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Passenger contract load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Travel Documents again and record load time$")
	public void go_to_travel_documents_again_and_record_load_time(){
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Travel Documents']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Travel documents load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Security Photo again and record load time$")
	public void go_to_security_photo_again_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Security Photo']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Security photo load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Emergency return airport again and record load time$")
	public void go_to_emergency_return_airport_again_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Emergency Return Airport']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Emergency return airport load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
	
	@Then("^Go to Travel information again and record load time$")
	public void go_to_travel_information_again_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Travel Information']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Travel information load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}

	@Then("^Go to Add a celebration again and record load time$")
	public void go_to_add_a_celebration_again_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Add a Celebration']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Add a celebration load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));
		
		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}

	@Then("^Go to Profile photo again and record load time$")
	public void go_to_profile_photo_again_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Profile Photo']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Profile photo load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}

	@Then("^Go to Medallion ordering again and record load time$")
	public void go_to_medallion_ordering_again_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Choose your Medallion� wearable']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Medallion Ordering load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));
		
		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}

	@Then("^Go to Health status again and record load time$")
	public void go_to_health_status_again_and_record_load_time() {
		cal = Calendar.getInstance();
		driver.findElement(By.xpath("//a[text()='Health Status']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
//		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Health Status']")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Health status load time: " + diffMilliSeconds +"ms");
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label=\"Go Back to previous page\"]")));

		cal = Calendar.getInstance();
		driver.findElement(By.cssSelector("[aria-label=\"Go Back to previous page\"]")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingIndicator")));
		cal2 = Calendar.getInstance();
		d1 = cal.getTime();
		d2 = cal2.getTime();
		diffMilliSeconds = d2.getTime() - d1.getTime();
		System.out.println("Back to dashboard load time: " + diffMilliSeconds +"ms");
	}
}