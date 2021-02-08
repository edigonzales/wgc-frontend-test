package ch.so.agi.wgc.frontend;

import com.microsoft.playwright.Page;

public class OerebPage {

    private Page page;

    private String locator_searchBar = "[placeholder=\"Search place or add map...\"]";
    private String locator_results = ".searchbox-results";
    private String locator_resultLabel = ".searchbox-result-label";
    
    public OerebPage(Page page) {
        this.page = page;
    }
}
