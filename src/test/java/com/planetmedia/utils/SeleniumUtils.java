package com.planetmedia.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Helper ligero con operaciones comunes de WebDriver usadas por pruebas.
 */
public class SeleniumUtils {

    private final WebDriver driver;

    public SeleniumUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void sendKeys(WebElement el, String keys) {
        el.sendKeys(keys);
    }

    public void clickElement(WebElement el) {
        el.click();
    }

    public void quitDriver() {
        try { driver.quit(); } catch (Exception ignored) {}
    }
}
