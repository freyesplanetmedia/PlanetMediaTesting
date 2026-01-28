package com.planetmedia.testing;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

/**
 * Extension JUnit 5 para crear y destruir un WebDriver por prueba.
 * Util para pruebas que no usan el contexto de Spring.
 */
public class ChromeExtension implements BeforeEachCallback, AfterEachCallback {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    @Override
    public void beforeEach(ExtensionContext context) {
        WebDriver driver = WebDriverFactory.createChromeDriver();
        DRIVER.set(driver);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }
}
