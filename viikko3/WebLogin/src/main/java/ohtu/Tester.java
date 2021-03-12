package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");

        // tehtävät merkattu seuraavasti: 
        // 1 oli siis alkutilanne tehtävässä
        // 2 epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
        // 3 uuden käyttäjätunnuksen luominen (Register new user)
        // 4 uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
            
        sleep(2);
        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());

        //1-2
        // WebElement element = driver.findElement(By.linkText("login"));
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        //olen tässä
        sleep(2);

        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        // 2 == epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
        //1-2
        // element.sendKeys("pekka");
        
        // 3
        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("matias" + r.nextInt(100000));

        element = driver.findElement(By.name("password"));
        // 1 == oikeasalasana
        // element.sendKeys("akkep");
        
        // 2 epäonnistunut..
        //element.sendKeys("akkep12921");
        
        //3
        element.sendKeys("uudenkayttajansalasana123");
        //1-2
        //element = driver.findElement(By.name("login"));
        
        // 3 passwordConfirmation
        element = driver.findElement(By.name("passwordConfirmation"));
        //3 
        element.sendKeys("uudenkayttajansalasana123");

        sleep(2);
        element.submit();
        System.out.println("submitin painamisen jälkeen");
        System.out.println(driver.getPageSource());
        
        // 4 skenaario, eli: uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
        // painetaan continue jne.
        WebElement elementContinue = driver.findElement(By.linkText("continue to application mainpage"));
        elementContinue.click();
        
        sleep(3);
        System.out.println(driver.getPageSource());
        
        WebElement elementlogout = driver.findElement(By.linkText("logout"));
        elementlogout.click();
        
        System.out.println(driver.getPageSource());
        
        driver.quit();

        //alkuperäinen skenaario, ilman HTMLunitdriveria
        /*WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
        
        driver.quit();
         */
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
