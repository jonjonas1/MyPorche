package com.Online16;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeconPorche {
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		System.out.println("setting Up...");
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@BeforeTest
	public void navigateToHomePage() {
		System.out.println("Welcome to HomePage");
		
	}
	@Test
	public void buyPorche() {
		driver.get("https://www.porsche.com/usa/modelstart/");
		//3. Select model 718
		driver.findElement(By.className("b-teaser-preview-wrapper")).click();
		String price=driver.findElement(By.className("m-14-model-price")).getText();
		System.out.println(price.substring(0, price.length()-4)); //////
		String Nprice=price.substring(0, price.length()-4);
		String digitPrice="";
		for(int i=0; i<Nprice.length();i++) {
			if(Character.isDigit(Nprice.charAt(i))) {
				digitPrice+=Nprice.charAt(i);
			}else {
				continue;
			}
		}
		
		int intPrice=Integer.parseInt(digitPrice);
		System.out.println(intPrice); 		//////////

		//5. Click on Build & Price under 718 Cayman
//		driver.findElement(By.className("m-14-quick-link")).click();
		driver.get("https://goo.gl/gS8TTF");
		String BasePrice=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[1]/div[2]")).getText();
		System.out.println(BasePrice);
		System.out.println(converter(BasePrice)); ///////
		int BasePr = converter(BasePrice);
//6. Verify that Base price displayed on the page is same as the price from step 4	
		assertEquals(BasePr, intPrice);
		System.out.print(BasePr==intPrice); //
		System.out.print(" Base price displayed on the page is same as the price from step 4"+"\n");
//7.Verify that Price for Equipment is 0
		String EquipPrice=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		System.out.println(EquipPrice+" Equipment price");
		int EquPrice = converter(EquipPrice);
		assertEquals(EquPrice, 0);
		System.out.print(EquPrice==0);  ////
		System.out.print(" Equipment price is "+EquPrice+"\n");///
//8.Verify that total price is the sum of base price + Delivery, Processing and Handling Fee
		String Delivery=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText();
		System.out.println(Delivery+" Delivery, Processing Fee");  //////
		int intDeliv=converter(Delivery);
		//get the total price and convert to integer
		String TotalP=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		System.out.println(TotalP+" total String price");
		int intTotal=converter(TotalP);
//		assertEquals(BasePr+Delivery, TotalP);
		System.out.print((BasePr+intDeliv)==intTotal);
		System.out.print(" total price is the sum of base price + Delivery \n");
//9.Select color “Miami Blue”
//		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_FJ5\"]/span")).click(); // or Below
		driver.findElement(By.xpath("//span[@style='background-color: rgb(0, 120, 138);']")).click();
//10.Verify that Price for Equipment is Equal to Miami Blue price
		String EquipPr=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
		System.out.println(EquipPr+" Equipment price");
		String miami=driver.findElement(By.xpath("//li[@data-price='$2,580']")).getText();
		String Strmiami = "$2,580";
		System.out.println(miami+"$2,580"+ " Color Price");
		int intmiami = converter(Strmiami);
		int intEquip=converter(EquipPr);
		System.out.println((intmiami==intEquip)+" Price for Equipment is Equal to Miami Blue price");
//11.Verify that total price is the sum of base price + Price for Equipment+Delivery,Processing and Handling Fee
		String TotalP2=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
		System.out.println(TotalP2+" Total2 String price");
		int intTotalP2 = converter(TotalP2);
		System.out.println((intTotalP2==(BasePr+intEquip+intDeliv))+" Total is Base price + Price for Equipment + Delivery");
//12.Select 20" Carrera Sport Wheels
//		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]")).click();
		driver.findElement(By.className("//span[@V='padding-top: 100%; background-image: url(\"/icc_pcna/model/2019/98212/common/tiles-AR.jpg?hc=e18edd73f16934a5c409c02836d2fc9a\"); background-position: 0px 86.6667%;']")).click();
//13.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels
//		String EquipPr3=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
//		System.out.println(EquipPr3+" Total EquipPr3 price");
//		String CarreraSport=driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]/span/span")).getText();
//		int intEquipPr3=converter(EquipPr3);
//		int intCarreraSport=converter(CarreraSport);
//		System.out.println((intEquipPr3==(intCarreraSport+intmiami))+" Equipment is the sum of Miami Blue price + 20\" Carrera Sport Wheels");
		
		
		
		//input[@elname='last']
		System.out.println();
	}
	
	// String to INT CONVERTER
	public static int converter(String strcon){
		String con="";
		for(int i=0; i<strcon.length();i++) {
			if(Character.isDigit(strcon.charAt(i)) ) {
				con+=strcon.charAt(i);
			}else {
				continue;
			}
		}
		return Integer.parseInt(con);
	}
	
	@AfterClass
	public void closeMethod() throws InterruptedException {
//		Thread.sleep(1000);
//		driver.close();
	}
	
}
