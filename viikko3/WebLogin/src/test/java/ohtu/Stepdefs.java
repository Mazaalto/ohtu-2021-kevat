package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    //tässä olen, nämä tehtävänannosta
    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    //tehtävästä
    @When("When  a valid username {string} and password {string} and matching password confirmation are entered")
    public void correctUsernameAndPasswordAndMatchingPasswordAreGiven(String username, String password, String confirmation) throws Throwable {
        createUser(username, password, password);
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void aValidUsernameAndPasswordAndMatchingPasswordConfirmationAreEntered(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        createUser(string, string2, string2);
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("incorrect username {string} and incorrect password {string} are given")
    public void incorrectUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("a invalid username {string} and password {string} and matching password confirmation are entered")
    public void aInvalidUsernameAndPasswordAndMatchingPasswordConfirmationAreEntered(String string, String string2, String confirmation) {
        // Write code here that turns the phrase above into concrete actions
        createUser(string, string2, confirmation);
    }

    @Then("user is not created and error {string} is reported")
    public void userIsNotCreatedAndErrorIsReported(String string) {
        pageHasContent("invalid username or password");
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Then("a new user is created")
    public void aNewUserIsCreated() {

        //Welcome to Ohtu Application!
        pageHasContent("Welcome to Ohtu Application!");

    }

    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    /*
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
     */
    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    //tässä olen
    private void createUser(String username, String password, String confirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    //tässä tuli virhe-ilmon jutut:

}
