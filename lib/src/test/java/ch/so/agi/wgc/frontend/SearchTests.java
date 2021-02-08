package ch.so.agi.wgc.frontend;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static org.testng.Assert.*;

public class SearchTests {
    private Browser browser;
    SearchPage searchPage;
    
    @BeforeClass
    public void setUp() {
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().withHeadless(false));

        BrowserContext context = browser.newContext(new Browser.NewContextOptions().withViewport(900, 600));

        Page page = context.newPage();
        page.navigate("https://geo.so.ch/map/");
        searchPage = new SearchPage(page);
    }
    
    @AfterClass
    public void tearDown(){
        browser.close();
    }
    
    @Test
    public void searchForExactTitle() throws InterruptedException {        
        Thread.sleep(2000); // TODO: delete
        
        String searchText = "Controlling";
        searchPage.search(searchText);
        
        Thread.sleep(2000); // TODO: delete

        assertEquals(searchPage.getNumberOfControllingLayers(), 3, "Number of found layers.");
        assertTrue(searchPage.getControllingLayers().contains("Controlling AV-Mutationen"));
        assertTrue(searchPage.getControllingLayers().contains("Controlling AVGBS-Vollzugsmeldungen"));
        assertTrue(searchPage.getControllingLayers().contains("Controlling AV-Validierung"));
     }

    //@Test
    public void javascriptEvaluation() {
        
        searchPage.evaluateJs();
    }
   
}
