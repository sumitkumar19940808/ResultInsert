package lucky4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UniqueNumberReturn {
	
//	public static void main(String args[]) {
//		unique();
//	}
	public static ArrayList<Integer> unique(){
		Set<Integer> arrli = new HashSet<Integer>();
		int count =20;
		for(int i=0; i<count ; i++) {
			String randomNumber = uninum();
			int result = Integer.parseInt(randomNumber);
			if(result >=1000 && result <=2999) {
				arrli.add(result);
				//System.out.println(i);
			}else {
				if(arrli.size()<=20) {
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
//	public static ArrayList<Integer> unique() {
//		ArrayList<Integer> arrli = new ArrayList<Integer>();
//		int count =20;
//		for(int i=0; i<count ; i++) {
//			String randomNumber = uninum();
//			int result = Integer.parseInt(randomNumber);
//			if(result >=1000 && result <=2999) {
//				arrli.add(result);
//				System.out.println(i);
				
//			}else {
//				if(arrli.size()<=20) {
//					count = count +1;
//				}else {
//					break;
//				}
//			}
//				
//		}
//		System.out.println(arrli);
//	    if
//		return arrli;
//	}
	public static String uninum() {
		Random r = new Random();
		String randomNumber = String.format("%04d", Integer.valueOf(r.nextInt(2999)));
		return randomNumber;
	}
	public static int insertResultIn(Select numFirst,Scanner sc , String prizeRank,int count ,int value) throws InterruptedException {
		int num = value;
		if(count==1) {
			System.out.println("Enter the number between 00 - 99 for "+prizeRank+" prize");
			num  = sc.nextInt();
			numFirst.selectByIndex(num);
		}else {
			System.out.println("same number is : " +num);
			numFirst.selectByIndex(num);
		}
		return num;
	}
	public static char insertResultSer(Select seriesFirst,Scanner sc,String prizeRank,int count,char letter) throws InterruptedException {
		int selectSeries = 0;
		char temp1=letter;
		if(count==1) {
		  System.out.println("Enter the series between 'A','B','C','D','E','G','H','J','K','L' for "+prizeRank+" prize");
		  String series  = sc.next();
		  Thread.sleep(500);
		  char[] serie = {'A','B','C','D','E','G','H','J','K','L'};
		  for(int i=0;i<serie.length;i++) {
			  char temp = serie[i];
			  temp1 = series.charAt(0);
			  if(temp1==temp) {
				  selectSeries=i;
			  }
		  }
		  seriesFirst.selectByIndex(selectSeries);
		}else {
			char[] serie = {'A','B','C','D','E','G','H','J','K','L'};
			  for(int i=0;i<serie.length;i++) {
				  char temp = serie[i];
				  //temp1 = series.charAt(0);
				  if(temp1==temp) {
					  selectSeries=i;
				  }
			  }
			seriesFirst.selectByIndex(selectSeries);
		}
		return temp1;
	}
	public static String setNum(Scanner sc,String temp,WebElement elem,int count,String firstPrizeNumber) {
		if(count ==1) {
			System.out.println("Enter number for "+temp+" prize between 1000 - 2999");
			firstPrizeNumber = sc.next();
			elem.sendKeys(firstPrizeNumber);
		}else {
			elem.sendKeys(firstPrizeNumber);
		}
		return firstPrizeNumber;
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
