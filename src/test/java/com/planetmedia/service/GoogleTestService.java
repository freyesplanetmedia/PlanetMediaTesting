package com.planetmedia.service;

import com.planetmedia.utils.SeleniumUtils;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

/**
 * Capa de servicio usada por pruebas - ejemplos de pequenos flujos reutilizables.
 */
@Service
public class GoogleTestService {

    public void performSearch(SeleniumUtils utils, String term) {
        utils.navigateTo("https://www.google.com");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        WebElement input = utils.getWebDriver().findElement(org.openqa.selenium.By.name("q"));
        input.sendKeys(term);
        input.submit();
    }

    public boolean verifySearchResults(SeleniumUtils utils, String term) {
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
        return utils.getPageTitle().toLowerCase().contains(term.toLowerCase());
    }
}
