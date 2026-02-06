package com.planetmedia.tests;

import com.planetmedia.testing.ChromeExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Import;
import com.planetmedia.config.TestWebDriverConfig;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Import(TestWebDriverConfig.class)
@ExtendWith(ChromeExtension.class)
public class SinapsisBasicTest {
	
	//La idea es crear algunos test automaticos para la pagina Sinapsis
	@Test
    public void pruebaAbrirSinapsis() throws InterruptedException {
        WebDriver driver = ChromeExtension.getDriver();
        System.out.println("[SinapsisBasicTest] driver=" + driver);
        driver.get("https://sinapsis.kio.tech/");
        System.out.println("[SinapsisBasicTest] Title: " + driver.getTitle());
        assertTrue(driver.getTitle().toLowerCase().contains("sinapsis"));
        driver.findElement(By.xpath("/html/body/main/div/div[2]/button[1]")).click();
        try { Thread.sleep(10000); } catch (InterruptedException ignored) {}

    }


}
