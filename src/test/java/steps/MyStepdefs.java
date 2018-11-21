package steps;

import com.BaseTest;
import com.googlePage.IssuePage;
import com.googlePage.SearchPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MyStepdefs {
    private BaseTest baseTest = new BaseTest();
    private EventFiringWebDriver driver;
    private SearchPage searchPage;
    private IssuePage issuePage;

    @Before
    public void before() {
        driver = baseTest.setUp();
        searchPage = new SearchPage(driver);
        issuePage = new IssuePage(driver);
    }

    @Given("^open https://www.google.com$")
    public void openGoogle()
    {
        driver.get("https://www.google.com");
    }

    @And("^input to search field text \"([^\"]*)\"$")
    public void inputToSearchFieldText(String request){
        searchPage.inputToSearchField(request);
    }

    @And("^press enter$")
    public void pressPressEnter() {
        searchPage.confirmRequest();
    }

    @Then("^check that first result contains in title request \"([^\"]*)\"$")
    public void checkThatFirstResultContainsTitleWord(String request){
        issuePage.getIssueReferences().get(0).click();
        String title = driver.getTitle();
        assertThat(title).as(driver.getCurrentUrl() +" title should contain '" + request + "'").containsIgnoringCase(request);
    }

    @Then("^check that one of \"([^\"]*)\" pages results contains \"([^\"]*)\"$")
    public void checkThatOneOfPagesResultLinksContains(String amountOfPages, String request) {
        List<String> hrefList = issuePage.getSearchResultsList(Integer.valueOf(amountOfPages));
        boolean containsExpectedDomain = issuePage.isContainsExpectedDomain(request, hrefList);
        assertThat(containsExpectedDomain).as("Search result does not contains domain '" + request).isEqualTo(true);
    }

    @After
    public void after(){
        baseTest.after();
    }
}
