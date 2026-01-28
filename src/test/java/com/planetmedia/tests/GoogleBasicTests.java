package com.planetmedia.tests;

import com.planetmedia.service.GoogleTestService;
import com.planetmedia.utils.SeleniumUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import com.planetmedia.config.TestWebDriverConfig;

/**
 * Pruebas de integracion usando SpringBootTest y un bean WebDriver compartido.
 */
@SpringBootTest
@Import(TestWebDriverConfig.class)
public class GoogleBasicTests {

    @Autowired
    private GoogleTestService googleTestService;

    private SeleniumUtils seleniumUtils;

    @Autowired
    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        System.out.println("[GoogleBasicTests] setUp - webdriver: " + webDriver);
        this.seleniumUtils = new SeleniumUtils(webDriver);
    }

    @Test
    public void testNavegarAGoogle() {
        seleniumUtils.navigateTo("https://www.google.com");
        String title = seleniumUtils.getPageTitle();
        assert title.toLowerCase().contains("google");
    }

    @Test
    public void testBuscarEnGoogle() {
        googleTestService.performSearch(seleniumUtils, "Selenium WebDriver");
        boolean containsTerm = googleTestService.verifySearchResults(seleniumUtils, "Selenium");
        assert containsTerm;
    }

    @Test
    public void testBuscarMultiplesTerminos() {
        String[] searchTerms = {"Java", "Spring Boot", "Maven"};
        for (String term : searchTerms) {
            seleniumUtils.navigateTo("https://www.google.com");
            WebElement searchBox = seleniumUtils.getWebDriver().findElement(By.name("q"));
            searchBox.clear();
            seleniumUtils.sendKeys(searchBox, term);
            searchBox.submit();
            boolean containsTerm = googleTestService.verifySearchResults(seleniumUtils, term);
            assert containsTerm;
        }
    }

    @AfterEach
    public void tearDown() {
        seleniumUtils.quitDriver();
    }
}
