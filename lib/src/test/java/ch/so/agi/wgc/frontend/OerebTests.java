package ch.so.agi.wgc.frontend;

import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.ATTACHED;
import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.DETACHED;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;

public class OerebTests {
    private Browser browser;
    private OerebPage oerebPage;
    
    @BeforeClass
    public void setUp() {
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().withHeadless(false));

        BrowserContext context = browser.newContext(new Browser.NewContextOptions().withViewport(900, 600));

        Page page = context.newPage();
        page.navigate("https://geo.so.ch/map/?c=2600576%2C1215500&s=1000");  
        
        // TODO: Wann weiss ich robust, ob die Karte ready ist?
//        var expectedState = new Page.WaitForSelectorOptions().withState(ATTACHED);
//        page.waitForSelector(".background-layer-item-active", expectedState);
        
        page.waitForLoadState(Page.LoadState.NETWORKIDLE); // network idle for 500ms
//        page.waitForTimeout(5000);        
        
        oerebPage = new OerebPage(page);
        
    }
    
    @AfterClass
    public void tearDown(){
        browser.close();
    }
    
    @Test 
    public void htmlOk() throws InterruptedException {
        
        Thread.sleep(2000);
        
        oerebPage.triggerHtmlExtract();
        
        Thread.sleep(1000);
        
        
    }

}
