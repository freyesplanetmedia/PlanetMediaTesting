package com.planetmedia.testing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

/**
 * Fabrica para crear instancias de WebDriver usadas en pruebas.
 * Centraliza la creacion para que las pruebas compartan configuracion y buenas practicas.
 */
public class WebDriverFactory {

    /**
     * Crear un ChromeDriver.
     * Usa la propiedad de sistema selenium.headless=true para ejecutar en headless.
     */
    public static WebDriver createChromeDriver() {
        return createChromeDriverInternal(false);
    }

    // helper interno que puede intentar reintento en modo headless
    private static WebDriver createChromeDriverInternal(boolean alreadyRetriedHeadless) {
        System.out.println("[WebDriverFactory] Creating ChromeDriver (start)");

        // Asegurar que el binario de chromedriver este disponible y sea compatible
        WebDriverManager.chromedriver().setup();

        String driverPath = System.getProperty("webdriver.chrome.driver");
        System.out.println("[WebDriverFactory] webdriver.chrome.driver=" + driverPath);

        ChromeOptions options = new ChromeOptions();
        String headlessProp = System.getProperty("selenium.headless", "false");
        System.out.println("[WebDriverFactory] selenium.headless=" + headlessProp);
        boolean headless = "true".equalsIgnoreCase(headlessProp);
        if (headless) {
            options.addArguments("--headless=new");
        }
        // Opciones recomendadas para CI y mayor fiabilidad
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        // Intentar detectar el binario de Chrome en rutas comunes de Windows para evitar fallos si no esta en PATH
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                String[] commonPaths = new String[]{
                        System.getenv("PROGRAMFILES") + "\\Google\\Chrome\\Application\\chrome.exe",
                        System.getenv("PROGRAMFILES(X86)") + "\\Google\\Chrome\\Application\\chrome.exe",
                        System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe"
                };
                for (String p : commonPaths) {
                    if (p != null) {
                        File f = new File(p);
                        if (f.exists() && f.canExecute()) {
                            System.out.println("[WebDriverFactory] Found chrome binary: " + p);
                            options.setBinary(p);
                            break;
                        }
                    }
                }
            }
        } catch (Throwable t) {
            System.err.println("[WebDriverFactory] Error while detecting chrome binary: " + t.getMessage());
        }

        try {
            System.out.println("[WebDriverFactory] os.name=" + System.getProperty("os.name") + " java.version=" + System.getProperty("java.version"));
            ChromeDriver driver = new ChromeDriver(options);
            System.out.println("[WebDriverFactory] ChromeDriver created");
            return driver;
        } catch (Throwable t) {
            System.err.println("[WebDriverFactory] Failed to create ChromeDriver: " + t.getMessage());
            t.printStackTrace(System.err);
            // Si no se intento en headless aun, reintentar una vez en headless
            if (!alreadyRetriedHeadless && !"true".equalsIgnoreCase(System.getProperty("selenium.headless", "false"))) {
                System.out.println("[WebDriverFactory] Attempting fallback to headless mode and retrying");
                System.setProperty("selenium.headless", "true");
                return createChromeDriverInternal(true);
            }
            throw new RuntimeException("Unable to create ChromeDriver", t);
        }
    }
}
