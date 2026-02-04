package com.planetmedia.tests;

import com.planetmedia.testing.ChromeExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ChromeExtension.class)
public class GoogleBasicoSinSpringTests {

    @Test
    public void pruebaAbrirGoogle() {
        WebDriver driver = ChromeExtension.getDriver();
        System.out.println("[GoogleBasicoSinSpringTests] driver=" + driver);
        driver.get("https://www.google.com");
        System.out.println("[GoogleBasicoSinSpringTests] Title: " + driver.getTitle());
        assertTrue(driver.getTitle().toLowerCase().contains("google"));
    }

    @Test
    public void pruebaBuscar() throws InterruptedException {
        WebDriver driver = ChromeExtension.getDriver();
        driver.get("https://www.google.com");
        try { Thread.sleep(3500); } catch (InterruptedException ignored) {}
        var input = driver.findElement(By.name("q"));
        input.sendKeys("Selenium WebDriver");
        input.submit();
        Thread.sleep(5000);
        assertTrue(driver.getTitle().toLowerCase().contains("selenium"));

    }
}
