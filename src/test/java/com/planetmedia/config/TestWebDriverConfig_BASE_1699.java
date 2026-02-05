package com.planetmedia.config;

import com.planetmedia.testing.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Configuracion de prueba proporcionada a SpringBootTest para exponer un bean WebDriver.
 */
@TestConfiguration
public class TestWebDriverConfig {

    @Bean(destroyMethod = "quit")
    public WebDriver webDriver() {
        System.out.println("[TestWebDriverConfig] Creating WebDriver bean");
        WebDriver driver = WebDriverFactory.createChromeDriver();
        System.out.println("[TestWebDriverConfig] WebDriver bean created");
        return driver;
    }
}
