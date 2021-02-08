package ch.so.agi.wgc.frontend;

import com.microsoft.playwright.Page;

import java.util.List;
import java.util.stream.Collectors;

import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.ATTACHED;
import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.DETACHED;

public class SearchPage {

    private Page page;

    private String locator_searchBar = "[placeholder=\"Search place or add map...\"]";
    private String locator_results = ".searchbox-results";
    private String locator_resultLabel = ".searchbox-result-label";
    
    public SearchPage(Page page) {
        this.page = page;
    }

    public void search(String query) {
        clearSearchBar();
        page.fill(locator_searchBar, query);

        var expectedState = new Page.WaitForSelectorOptions().withState(ATTACHED);
        page.waitForSelector(locator_results, expectedState);
    }

    public void clearSearchBar(){
        page.fill(locator_searchBar, "");

        var expectedState = new Page.WaitForSelectorOptions().withState(DETACHED);
        page.waitForSelector(locator_results, expectedState);
    }

    public int getNumberOfControllingLayers(){
       return page.querySelectorAll(locator_resultLabel).size();
    }

    public List<String> getControllingLayers(){
        return page.querySelectorAll(locator_resultLabel)
                .stream()
                .map(e -> e.innerText())
                .collect(Collectors.toList());
    }
    
    public void evaluateJs() {
//        Object dimensions = page.evaluate("() => {\n" +
//                "  return {\n" +
//                "      width: document.documentElement.clientWidth,\n" +
//                "      height: document.documentElement.clientHeight,\n" +
//                "      deviceScaleFactor: window.devicePixelRatio\n" +
//                "  }\n" +
//                "}");
//        System.out.println(dimensions);
        
//        page.evaluate("() => alert(map)");
        Object foo = page.evaluate("() => {return Map}");
        System.out.println(foo);
    }
}