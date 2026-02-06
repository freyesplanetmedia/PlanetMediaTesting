package com.planetmedia.config;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


/*
 * Configuracion de prueba proporcionada a SpringBootTest para exponer un bean WebDriver.
 */
@TestConfiguration
public class TestWebDriverConfig {

    @Bean(destroyMethod = "quit")
    public WebDriver webDriver() {
        System.out.println("[TestWebDriverConfig] Creating WebDriver bean");

        // Configuración de Chrome para que arranque maximizado
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);

        System.out.println("[TestWebDriverConfig] WebDriver bean created");
        return driver;
    }
}

