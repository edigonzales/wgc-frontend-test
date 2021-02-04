package ch.so.agi.wgc.frontend;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTests {

    private Browser browser;
    protected SearchPage searchPage;

    @BeforeClass
    public void setUp(){

        //Open a browser (supports Chromium (Chrome, Edge), Firefox, and Webkit (Safari))
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().withHeadless(false));

        //A single browser tab
        Page page = browser.newPage();
        page.navigate("https://geo.so.ch/map/");
        searchPage = new SearchPage(page);
    }

    @AfterClass
    public void tearDown(){
        browser.close();
    }
}