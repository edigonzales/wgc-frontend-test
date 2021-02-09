package ch.so.agi.wgc.frontend;

import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.ATTACHED;

import java.nio.channels.Selector;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

public class OerebPage {

    private Page page;

//    private String locator_searchBar = "[placeholder=\"Search place or add map...\"]";
//    private String locator_results = ".searchbox-results";
//    private String locator_resultLabel = ".searchbox-result-label";
    
    private String locator_appMenu = "#AppMenu";
    private String locator_map = "#map";
    private String locator_realEstateInformation = "text=\"Real Estate Information\"";
    private String locator_layersAndLegend = "text=\"Layers & Legend\"";
    private String locator_parcelLayer = "text=\"Grundstücke (amtliche Vermessung)\"";
    
    private int numberOfParcelLayers;
    
    public OerebPage(Page page) {
        this.page = page;
    }
    
    public void triggerHtmlExtract() {
        
        // Das OEREB-Werkzeug aktivieren. Damit werden die Grundstücke geladen.
        page.click(locator_appMenu);        
        page.querySelector(locator_realEstateInformation).click();
        
        // TOC anzeigen. Erst so werden die Layer ins DOM gehängt.
        page.click(locator_appMenu);        
        page.querySelector(locator_layersAndLegend).click();
                
        var expectedState = new Page.WaitForSelectorOptions().withState(ATTACHED);
        page.waitForSelector(locator_parcelLayer, expectedState);
        numberOfParcelLayers = page.querySelectorAll(locator_parcelLayer).size();
        System.out.println(this.numberOfParcelLayers);
        
        // Wieder das OEREB-Werkzeug aktivieren.
        page.click(locator_appMenu);        
        page.querySelector(locator_realEstateInformation).click();

        ElementHandle mapHandle = page.querySelector(locator_map);        
        page.mouse().click(mapHandle.boundingBox().width / 2, mapHandle.boundingBox().height / 2);
        
        page.waitForTimeout(3000);
    }
    
    public int getNumberOfParcelLayers() {
        return numberOfParcelLayers;
    }
}
