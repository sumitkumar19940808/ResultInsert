package lucky4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LuckyFour extends UniqueNumberReturn {
	WebDriver driver;
	String url ="http://192.168.4.63:8080/R1backoffice/";
	String loginname ="mondelhi";
	String passwd = "sdla12345";
	Scanner sc = new Scanner(System.in);
	AtomicInteger sequence = new AtomicInteger(0);
	int value = 0;
	int value1 = 0;
	char letter =0;
	char letter1 =0;
	String firstPrizeNumber= "";
	String secondPrizeNumber = "";
	ArrayList<Integer> alli;
	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/home/sumitkumar/workspace/DataCompare/plugin/chromedriver_linux64/chromedriver");
		driver = new ChromeDriver();
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
  @Test(priority=1)
  public void login() {
	  WebElement element =driver.findElement(By.id("loginname_1"));
	  element.clear();
	  element.sendKeys(loginname);
	  WebElement ele = driver.findElement(By.id("password"));
	  ele.clear();
	  ele.sendKeys(passwd);
	  driver.findElement(By.id("loginbtn1")).click();
  }
  @Test(priority=2)
  public void selectResult() {
	 WebElement ele = driver.findElement(By.id("64"));
	 Actions ac = new Actions(driver);
	 ac.moveToElement(ele).build().perform();
	 driver.findElement(By.id("onlineseries/numberresultfeed.php?description=Result Prize&menucode=1008")).click();
  }
  @Test(priority=3)
  public void resulInsert() throws InterruptedException {
	  driver.switchTo().frame("div_middlecontent");
	  driver.findElement(By.id("dtpDrawDate")).click();
	  driver.findElement(By.cssSelector(".ui-state-highlight")).click();
	  Select selectDrawTime = new Select(driver.findElement(By.id("combolottime")));
	  int count =2;
	  selectDrawTime.selectByIndex(count);
//	  checkResultIn();
//	  System.out.println("ok1");
	  driver.findElement(By.id("btnAdd")).click();
//	  System.out.println("ok");
	  driver.findElement(By.id("btnSubmit")).click();
	  List<WebElement> ele = driver.findElements(By.id("popup_container"));
	  if(ele.size()>=1) {
		  Thread.sleep(500);
		  driver.findElement(By.id("div_ok")).click();
		  Thread.sleep(500);
		  count = count +1;
		  selectDrawTime.selectByIndex(count);
//		  System.out.println("ok");
		  driver.findElement(By.id("btnAdd")).click();
//		  System.out.println("ok");
		  //driver.findElement(By.id("btnSubmit")).click();
//		  System.out.println("ok1");
	  }else {
		  selectDrawTime.selectByIndex(count);
	  }
  }
  @Test(priority=4,invocationCount=2)
  public void insertRes() throws InterruptedException {
	  int count= sequence.addAndGet(1);
	  System.out.println(count +" count");
	  Select numFirst = new Select(driver.findElement(By.id("seriesno_1")));
//	  System.out.println(value +" 1");
	  int tempval = insertResultIn(numFirst,sc,"First",count,value);
	  value = tempval;
//	  System.out.println(value +" 2");
	  Select seriesFirst = new Select(driver.findElement(By.id("setsno_1")));
	  Thread.sleep(500);
	  char lettertemp = insertResultSer(seriesFirst,sc,"First",count,letter);
	  letter = lettertemp;
	  WebElement elem =driver.findElement(By.id("winno_1_1"));
	  String hellotemp = setNum(sc,"First",elem,count,firstPrizeNumber);
	  firstPrizeNumber = hellotemp;
	  /*Second*/
	  Select numSecond = new Select(driver.findElement(By.id("seriesno_2")));
//	  System.out.println(value1 +" 11");
	  int tempval1 = insertResultIn(numSecond,sc,"Second",count , value1);
	  value1 = tempval1;
//	  System.out.println(value1 +" 21");
	  Select seriesSecond = new Select(driver.findElement(By.id("setsno_2")));
	  Thread.sleep(500);
	  char lettertemp1 = insertResultSer(seriesSecond,sc,"Second",count,letter1);
	  letter1 = lettertemp1;
	  WebElement elemt =driver.findElement(By.id("winno_2_1"));
	  String hellotemp1 = setNum(sc,"Second",elemt,count,secondPrizeNumber);
	  secondPrizeNumber = hellotemp1;
//	  System.out.println("Enter number for Second prize between 1000 - 2999");
//	  String secondPrizeNumber =sc.next();
//	  driver.findElement(By.id("winno_2_1")).sendKeys(secondPrizeNumber);
	  if(count==1) {
		  alli = unique();
	  }
	  for(int i=0 ; i<20; i++) {
		  int temp=i;
		  String code = Integer.toString(temp+1);
		  Integer num = alli.get(i);
		  String number = Integer.toString(num);
		  driver.findElement(By.id("winno_3_"+code)).sendKeys(number);
	  }
	  System.out.println(alli);
	  WebElement textbox = driver.findElement(By.id("winno_3_20"));
	  textbox.sendKeys(Keys.ENTER);
	  
	  driver.findElement(By.id("btnSubmit")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.id("popup_ok")).click();
	  Thread.sleep(1000);
//	  driver.findElement(By.id("popup_ok")).click();
	  
  }
}
