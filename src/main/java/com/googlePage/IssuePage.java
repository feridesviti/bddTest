package com.googlePage;

import com.helper.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class IssuePage extends PageObject {
    public IssuePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h3[@class='LC20lb']/..")
    private List<WebElement> issueReference;

    @FindBy(css = "#hdtb-msb-vis")
    private WebElement navigationBar;

    @FindBy(css = "#pnnext > span:nth-child(2)")
    private WebElement nextPageButton;

    @FindBy(css = "#nav td.cur")
    private WebElement activePageNumberElement;

    public List<WebElement> getIssueReferences() {
        return issueReference;
    }

    private IssuePage clickOnNextPage() {
        nextPageButton.click();
        return this;
    }

    public List<String> getSearchResultsList(int numberOfSearchPages) {
        List<String> hrefList = new ArrayList<>();
        do {
            List<WebElement> issueReferences = getIssueReferences();
            for (WebElement issueReference : issueReferences) {
                hrefList.add(issueReference.getAttribute("href"));
            }
            clickOnNextPage();
        } while (getActivePageNumber() <= numberOfSearchPages);
        return hrefList;
    }

    public boolean isContainsExpectedDomain(String request, List<String> hrefList) {
        boolean containsExpectedDomain = false;
        for (String href : hrefList) {
            containsExpectedDomain = href.contains(request);
            if (containsExpectedDomain) {
                break;
            }
        }
        return containsExpectedDomain;
    }

    private int getActivePageNumber() {
        return Integer.valueOf(activePageNumberElement.getText());
    }
}
