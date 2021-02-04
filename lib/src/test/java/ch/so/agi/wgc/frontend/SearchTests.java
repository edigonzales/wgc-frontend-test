package ch.so.agi.wgc.frontend;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SearchTests extends BaseTests {

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
