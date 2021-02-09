package ch.so.agi.wgc.frontend;

import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.ATTACHED;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

public class OerebPage {

    private Page page;

    private String locator_searchBar = "[placeholder=\"Search place or add map...\"]";
    private String locator_results = ".searchbox-results";
    private String locator_resultLabel = ".searchbox-result-label";
    
    public OerebPage(Page page) {
        this.page = page;
    }
    
    public void triggerHtmlExtract() {
        page.click("#AppMenu");
        
        //var expectedState = new Page.WaitForSelectorOptions().withState(ATTACHED);
        //page.waitForSelector("Real Estate Information");
        ElementHandle handle = page.querySelector("text=\"Real Estate Information\"");
        System.out.println(handle);
        handle.click();
    }
}
