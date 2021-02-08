package ch.so.agi.wgc.frontend;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTests {

    protected Browser browser;

    @BeforeClass
    public void setUp() {

        //Open a browser (supports Chromium (Chrome, Edge), Firefox, and Webkit (Safari))
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().withHeadless(false));

    }

    @AfterClass
    public void tearDown(){
        browser.close();
    }
}