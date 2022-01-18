package Selenium;// Generated by Selenium IDE
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class CVS2 {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void example() throws IOException {
        Actions actions = new Actions(driver);
        driver.get("https://uk.trustpilot.com/review/www.webhosting.uk.com");
        driver.manage().window().setSize(new Dimension(1050, 660));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div/*[text()='I accept']")).click();
        String test = driver.findElement(By.xpath("//*[text()='77']/..")).getAttribute("class");
        System.out.println(test);
        List<String> fileContent = new ArrayList<>();

        String reviewText, starRating, date;

        while(test.equals("pagination_pagination___F1qS")) {
            for (int i = 2; i < 3; i++) {
                driver.get("https://uk.trustpilot.com/review/www.webhosting.uk.com?page=" + i + "");
                String hello = "https://uk.trustpilot.com/review/www.webhosting.uk.com?page=" + i + "";
                System.out.println(hello);

                String strUrl = driver.getCurrentUrl();
                List<WebElement> reviews = driver.findElements(By.xpath("//a[@name=\"consumer-profile\"]"));
                for (int x = 0; x < 1; x++) {
                    String reviewId = reviews.get(x).getAttribute("href");
                    reviewId = reviewId.replace("https://uk.trustpilot.com", "");
                    reviewText = driver.findElement(By.xpath("//a[@href=\"" + reviewId + "\"]/parent::*/parent::*/section/div[2]/p\n")).getText();
                    System.out.println(reviewText);
                    starRating = driver.findElement(By.xpath("//a[@href=\"" + reviewId + "\"]/parent::*/parent::*/section/div[1]/div[1]/img")).getAttribute("alt");
                    System.out.println(starRating);
                    date = driver.findElement(By.xpath("//a[@href=\"" + reviewId + "\"]/parent::*/parent::*/section/div[1]/div[2]/time")).getAttribute("datetime");
                    System.out.println(date);
                    fileContent.add("\"" + reviewText + "\"" + "," + "\"" + starRating + "\"" + "," + "\"" + date + "\"");
                }
                test = driver.findElement(By.xpath("//*[text()='77']/..")).getAttribute("class");
                driver.findElement(By.xpath("//nav/*[text()='Next page']")).click();

//            Actions action = new Actions(driver);
//            WebElement we = driver.findElement(By.xpath("//nav/*[text()='Next page']"));
//            action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//nav/*[text()='Next page']"))).click().build().perform();
//            actions.moveToElement(driver.findElement(By.xpath("//nav/*[text()='Next page']"))).build().perform();
//            driver.findElement(By.xpath("//nav/*[text()='Next page']")).click();
            }
        }
            File file = new File("test2.csv");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Review,Star Rating,Date");
            bw.newLine();
            for (String s : fileContent) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
}