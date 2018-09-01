package lucky4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LuckyDigit{
	WebDriver driver;
//	String url ="http://172.20.5.3/bo/index.php";
//	String loginname ="amit";
//	String passwd = "sdla1234";
	String url ="http://192.168.4.63:8080/R1backoffice/";
	String loginname ="mondelhi";
	String passwd = "sdla12345";
	Scanner sc = new Scanner(System.in);
	AtomicInteger sequence = new AtomicInteger(0);
	ArrayList<Integer> alli2;
//	ArrayList<Integer> alli3;
//	ArrayList<Integer> alli4;
	int index = 2;
	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/home/sumitkumar/workspace/DataCompare/plugin/chromedriver_linux64/chromedriver");
		driver = new ChromeDriver();
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
  @BeforeTest
  public void login() {
	  WebElement element =driver.findElement(By.id("loginname_1"));
	  element.clear();
	  element.sendKeys(loginname);
	  WebElement ele = driver.findElement(By.id("password"));
	  ele.clear();
	  ele.sendKeys(passwd);
	  driver.findElement(By.id("loginbtn1")).click();
  }
  @Test(priority=1)
  public void selectResult() {
	 WebElement ele = driver.findElement(By.id("3"));
	 Actions ac = new Actions(driver);
	 ac.moveToElement(ele).build().perform();
	 driver.findElement(By.id("onlinedigit/numberresultfeed.php?description=Result Prize&menucode=960")).click();
  }
  @Test(priority=2)
  public void resulInsert() throws InterruptedException {
	  driver.switchTo().frame("div_middlecontent");
	  driver.findElement(By.id("dtpDrawDate")).click();
	  driver.findElement(By.cssSelector(".ui-state-highlight")).click();
	  for(int i=index;i<=2;i++) {
		  Select selectDrawTime = new Select(driver.findElement(By.id("combolottime")));
		  int count =i;
		  selectDrawTime.selectByIndex(count);
	//	  checkResultIn();
	//	  System.out.println("ok1");
		  driver.findElement(By.id("btnAdd")).click();
	//	  System.out.println("ok");
		 // List<WebElement> ele = driver.findElements(By.xpath("//input[@id='popup_ok']"))
		  WebElement ele = driver.findElement(By.xpath("//*[@id=\"pnlResult\"]/tbody/tr[1]/td[5]/font/b"));
		 // WebElement ele1 = driver.findElement(By.id("winno_1_1"));
		  //WebElement ele2 = driver.findElement(By.xpath("//*[@id='popup_message']/table/tbody/tr/td[2]"));
		  boolean check = false;
		  while(check == false) {
			  WebDriverWait wait = new WebDriverWait(driver,30);
			  String text="";
			  String text1="";
			  boolean temp = false;
			  WebElement ele1 = null;
			  WebElement ele2 = null;
			  try {
				  ele1 = driver.findElement(By.id("winno_1_1"));
				  text1 = ele1.getAttribute("value");
				  ele2 = driver.findElement(By.xpath("//*[@id='popup_message']/table/tbody/tr/td[2]"));
				  text = ele2.getText();
			  }catch(Exception e) {
				  if(text.equals("Lottery Scheme / Series not defined.")) {
					  System.out.println(text);
					  temp =checkalert(ele2);
				  }else if(!text1.isEmpty()) {
					  temp =checkalert(ele1);
					  System.out.println(text1);
				  }else {
					  temp =checkalert(ele);
				  }
			  }
//				  boolean temp = checkalert(ele,ele1);
				  if(temp==true) {
					  check=true;
					  break;
				  }else {
					  System.out.println(temp);
//					  WebElement test;
//					  test = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='div_ok']")));
//					  System.out.println("try1");
//					  test.click();
					  int tempcheck =0;
					  try {
						  System.out.println(driver.findElement(By.xpath("//*[@id=\"popup_message\"]/table/tbody/tr/td[2]")).getText());
						  System.out.println("try2");
						  Thread.sleep(1000);
						  driver.findElement(By.xpath("//*[@id='popup_ok']")).click();
					  }catch(Exception e) {
						  count = count +1;
						  selectDrawTime.selectByIndex(count);
						  tempcheck=1;
					  }
					  if(tempcheck==0) {
						  count = count +1;
						  selectDrawTime.selectByIndex(count);
					  }
//					  WebElement test1;
//					  test1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnAdd")));
//					  test1.click();
					  Thread.sleep(1000);
					  driver.findElement(By.id("btnAdd")).click();
					  System.out.println("ex");
				  }
		  }  
		  System.out.println("try");
		  insertRes();
//		  if(ele.size()>=1) {
//			  Thread.sleep(500);
//			  driver.findElement(By.id("div_ok")).click();
//			  Thread.sleep(500);
//			  count = count +1;
//			  selectDrawTime.selectByIndex(count);
	//		  System.out.println("ok");
//			  driver.findElement(By.id("btnAdd")).click();
	//		  System.out.println("ok");
			  //driver.findElement(By.id("btnSubmit")).click();
	//		  System.out.println("ok1");
//		  }
	 }
  }
//  @Test(priority=4,invocationCount=2)
  public void insertRes() throws InterruptedException {
	  String value = "";
	  for(int j=1;j<=2;j++) {
		  int count= j;//sequence.addAndGet(1);
		  System.out.println(count +" count");
		  WebElement numFirst = driver.findElement(By.id("winno_1_1"));
	//	  System.out.println(value +" 1");
		  String tempval = insertResultIn(numFirst,sc,"First",count,value);
//		  driver.quit();
		  value = tempval;
		  /*Second*/
		  //	  System.out.println(value1 +" 21");
		  if(count==1) {
			  alli2 = unique();
		  }
		  for(int i=0 ; i<10; i++) {
			  int temp=i;
			  String code = Integer.toString(temp+1);
			  Integer num = alli2.get(i);
			  String number = Integer.toString(num);
//			  Thread.sleep(500);
			  driver.findElement(By.id("winno_2_"+code)).sendKeys(number);
		      driver.findElement(By.id("winno_2_"+code)).sendKeys(number);	  
			  System.out.println(alli2.get(i));
		  }
//		  if(count==1) {
//			  alli3 = unique();
//		  }
		  int temp=0;
		  for(int i=10 ; i<30; i++) {
			  String code = Integer.toString(temp+1);
			  Integer num = alli2.get(i);
			  String number = Integer.toString(num);
//			  Thread.sleep(500);
			  driver.findElement(By.id("winno_3_"+code)).sendKeys(number);
			  System.out.println(alli2.get(i)+" "+i);
			  temp++;
		  }
//		  System.out.println(alli3);
//		  if(count==1) {
//			  alli4 = unique();
//		  }
		  int temp1=0;
		  for(int i=30 ; i<80; i++) {
			  String code = Integer.toString(temp1+1);
			  Integer num = alli2.get(i);
			  String number = Integer.toString(num);
//			  Thread.sleep(500);
			  driver.findElement(By.id("winno_4_"+code)).sendKeys(number);
			  System.out.println(alli2.get(i));
			  temp1++;
		  }
//		  System.out.println(alli4);
		  WebElement textbox = driver.findElement(By.id("winno_4_50"));
		  textbox.sendKeys(Keys.ENTER);
		  driver.findElement(By.id("btnSubmit")).click();
		  Thread.sleep(5000);
		  driver.findElement(By.id("popup_ok")).click();
		  Thread.sleep(1000);
	//	  driver.findElement(By.id("popup_ok")).click();
	  }
	  
  }
  public static ArrayList<Integer> unique(){
		Set<Integer> arrli = new 	HashSet<Integer>();
		int count =80;
		for(int i=0; i<count ; i++) {
			String randomNumber = uninum();
			int result = Integer.parseInt(randomNumber);
			if(result >=1000 && result <=9999) {
				arrli.add(result);
				//System.out.println(i);
			}else {
				if(arrli.size()<=80) {
					count = count +1;
				}else {
					break;
				}
			}		
		}
		List list = new ArrayList(arrli);
		System.out.println(list);
		return (ArrayList<Integer>) list;
	}

  public static String uninum() {
		Random r = new Random();
		String randomNumber = String.format("%04d", Integer.valueOf(r.nextInt(9999)));
		return randomNumber;
	}
	public static String insertResultIn(WebElement numFirst,Scanner sc , String prizeRank,int count ,String value) throws InterruptedException {
		String num = value;
		if(count==1) {
			System.out.println("Enter the number between 0000000 - 9999999 for "+prizeRank+" prize");
			num  = sc.nextLine();
			numFirst.sendKeys(num);
//			List<WebElement> ele = numFirst.getOptions();
//			System.out.println(ele.size());
		}else {
			System.out.println("same number is : " +num);
			numFirst.sendKeys(num);
		}
		return num;
	}
	public static boolean checkalert(WebElement ele) {
		String text = ele.getText();
		System.out.println(text);
		if(text.equals("Winning Number"))
			return true;
		else
		return false;
	}
}
