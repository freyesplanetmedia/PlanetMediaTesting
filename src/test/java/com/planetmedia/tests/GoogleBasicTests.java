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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;


/**
 * Pruebas de integracion usando SpringBootTest y un bean WebDriver compartido.
 */
@SpringBootTest
@Import(TestWebDriverConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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


/*
    @Test

   /* @Test
>>>>>>> ee3de5209912166fad787c10fcc5ffd83e9fefa3
    public void testNavegarAGoogle() {
        seleniumUtils.navigateTo("https://www.google.com");
        String title = seleniumUtils.getPageTitle();
        assert title.toLowerCase().contains("google");
    }

   /* @Test   
    public void testBuscarEnGoogle() {
        googleTestService.performSearch(seleniumUtils, "Selenium Webdriver"); 
        boolean containsTerm = googleTestService.verifySearchResults(seleniumUtils, "Selenium");
        assert containsTerm;
    }*/

   /* @Test
    public void testBuscarMultiplesTerminos() {
        String[] searchTerms = {"Java", "Spring Boot", "Maven"};
        for (String term : searchTerms) {
            seleniumUtils.navigateTo("https://gmsubastas-uat-cwgychg6c7g6c5hp.northeurope-01.azurewebsites.net/login");
            System.out.println("Se ingreso a Sitio de Subastas");
            WebElement searchBox = seleniumUtils.getWebDriver().findElement(By.name("q"));
            searchBox.clear();
            seleniumUtils.sendKeys(searchBox, term);
            searchBox.submit();
            boolean containsTerm = googleTestService.verifySearchResults(seleniumUtils, term);
            assert containsTerm;
        }
    }*/
      
    // este metodo es para poder sacar capturas de pantalla asi como asignarles un destino deseado
    public void tomarScreenshot(String rutaDestino) {
        try {
            File screenshot = ((TakesScreenshot) seleniumUtils.getWebDriver()).getScreenshotAs(OutputType.FILE);
            File destino = new File(rutaDestino);
            Files.copy(screenshot.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("[Acción] Screenshot guardado en: " + destino.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("[Error] No se pudo tomar el screenshot: " + e.getMessage());
        }
    }

        //se navega a la url y se realiza la siguiente lista de acciones
  
    @Test
    @Order(1)
    public void testInicio()throws InterruptedException, UnsupportedFlavorException, IOException {
    	try {

        seleniumUtils.navigateTo("https://gmsubastas-uat-cwgychg6c7g6c5hp.northeurope-01.azurewebsites.net/login");
        System.out.println("[Acción] Se ingresó al sitio de Subastas");

        WebElement inicioSpan = seleniumUtils.getWebDriver().findElement(By.xpath("//span[text()='Inicio']"));
        inicioSpan.click();
        System.out.println("[Acción] Se hizo clic en el botón 'Inicio'");
        String title = seleniumUtils.getPageTitle();
        System.out.println("[Resultado] El título de la página después de login es: " + title);
    
        Thread.sleep(8000); 
        tomarScreenshot("C:/Users/Dell/Documents/Capturas_Test_Pruebas/captura1.png");
        
        //selecciona la seccion subastas por finalizar
        WebElement porfinalizar = seleniumUtils.getWebDriver().findElement(By.xpath("//a[@href='/PorFinalizar']"));
        porfinalizar.click();
        System.out.println("[Acción] Se hizo clic en el botón 'Por finalizar'");
        String title1 = seleniumUtils.getPageTitle();
            
        Thread.sleep(5000);
        tomarScreenshot("C:/Users/Dell/Documents/Capturas_Test_Pruebas/captura2.png");
        
        WebElement compartirIcon = seleniumUtils.getWebDriver().findElement(By.cssSelector("i.compartir-icon"));
        compartirIcon.click();
        System.out.println("[Acción] Se hizo clic en el ícono 'Compartir'");
        
        Thread.sleep(5000);
        tomarScreenshot("C:/Users/Dell/Documents/Capturas_Test_Pruebas/captura3.png");
        
        Thread.sleep(4000);
        //busca el enlace que aparece en el modal emergente
        WebDriverWait wait = new WebDriverWait(seleniumUtils.getWebDriver(), Duration.ofSeconds(10));
        WebElement inputEnlace = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enlace")));
        Thread.sleep(4000);
        
        //se obtiene el valor del input que contiena a la URL
        String urlCopiada = inputEnlace.getAttribute("value");
        System.out.println("[Acción] URL obtenida del input: " + urlCopiada);
        Thread.sleep(4000);
        
        //se da clic en el boton de cerrar
        WebElement cerrarBtn = seleniumUtils.getWebDriver().findElement(By.cssSelector("button.btnSubmit"));
        cerrarBtn.click();
        System.out.println("[Acción] Se hizo clic en el botón 'Cerrar'");
        Thread.sleep(3000);
        
        //se abre nueva pestaña
        ((JavascriptExecutor) seleniumUtils.getWebDriver()).executeScript("window.open();");
        tomarScreenshot("C:/Users/Dell/Documents/Capturas_Test_Pruebas/captura4.png");
        
        //se enfoca en la nueva pentaña que se abre 
        ArrayList<String> tabs = new ArrayList<>(seleniumUtils.getWebDriver().getWindowHandles());
        seleniumUtils.getWebDriver().switchTo().window(tabs.get(tabs.size() - 1));
        Thread.sleep(5000);
        tomarScreenshot("C:/Users/Dell/Documents/Capturas_Test_Pruebas/captura5.png");
        
        seleniumUtils.getWebDriver().get(urlCopiada);
        System.out.println("[Acción] Se abrió nueva pestaña y se pegó la URL: " + urlCopiada);
        Thread.sleep(5000);
        tomarScreenshot("C:/Users/Dell/Documents/Capturas_Test_Pruebas/captura6.png");
    	} catch (Exception e) {
            // Captura cualquier error en el flujo completo
            e.printStackTrace();
            System.out.println("[Error] Ocurrió un problema en el test: " + e.getMessage());
            tomarScreenshot("C:/Users/Dell/Documents/Capturas_Test_Pruebas/error.png");
        }

    }

    @Test
    @Order(2)
    public void testProbarUbicacion()throws InterruptedException, UnsupportedFlavorException, IOException {
    	try {
    		//se navega hacia el sitio web y se da clic en la seccion de inicio 
            seleniumUtils.navigateTo("https://gmsubastas-uat-cwgychg6c7g6c5hp.northeurope-01.azurewebsites.net/login");
            System.out.println("[Acción] Se ingresó al sitio de Subastas");

            WebElement inicioSpan = seleniumUtils.getWebDriver().findElement(By.xpath("//span[text()='Inicio']"));
            inicioSpan.click();
            
            //se toma evidencia obteniendo el titulo de la pagina y imprimiendolo 
            System.out.println("[Acción] Se hizo clic en el botón 'Inicio'");
            String title = seleniumUtils.getPageTitle();
            System.out.println("[Resultado] El título de la página después de login es: " + title); 	
            
            Thread.sleep(4000);
            tomarScreenshot("C:/Users/Dell/Documents/Capturas_test_Ubicacion/captura1.png");
            
             //selecciona la seccion subastas nuevas
            WebElement porfinalizar = seleniumUtils.getWebDriver().findElement(By.xpath("//a[@href='/Nuevas']"));
            porfinalizar.click();
            System.out.println("[Acción] Se hizo clic en el botón 'Nuevas'");
            tomarScreenshot("C:/Users/Dell/Documents/Capturas_test_Ubicacion/captura2.png");
            Thread.sleep(4000);
            
            // Scroll hasta el elemento
            WebElement ubicacionIcon = seleniumUtils.getWebDriver().findElement(By.cssSelector("img[alt='Ubicación']"));
            ((JavascriptExecutor) seleniumUtils.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", ubicacionIcon);
            
            Thread.sleep(2000);
            ubicacionIcon.click();
            System.out.println("[Acción] Se hizo clic en el botón 'Ubicacion");
            tomarScreenshot("C:/Users/Dell/Documents/Capturas_test_Ubicacion/captura3.png");
            Thread.sleep(8000);

            ArrayList<String> tabs = new ArrayList<>(seleniumUtils.getWebDriver().getWindowHandles());
            seleniumUtils.getWebDriver().switchTo().window(tabs.get(tabs.size() - 1));
            Thread.sleep(5000); 
            
            
          
    	} catch (Exception e) {
            // Captura cualquier error en el flujo completo
            e.printStackTrace();
            System.out.println("[Error] Ocurrió un problema en el test: " + e.getMessage());
            tomarScreenshot("C:/Users/Dell/Documents/Capturas_Test_Pruebas/error.png");
        }

    }


    @AfterEach
    public void tearDown() {
        seleniumUtils.quitDriver();
   }
    
}
