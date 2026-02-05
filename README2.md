#Guía para ejecutar los tests

#1. Requisitos previos  
- Tener instalado Java 17 o la versión que uses en tu proyecto.  
- Tener instalado Maven y configurado en tu PATH.  
- Tener instalado Google Chrome en tu máquina.  
- Tener disponible ChromeDriver compatible con tu versión de Chrome. Si usas WebDriverManager, se descarga automáticamente.

#2. Clonar el repositorio  
git clone `https://github.com/freyesplanetmedia/PlanetMediaTesting.git` [(github.com in Bing)](https://www.bing.com/search?q="https%3A%2F%2Fgithub.com%2Ffreyesplanetmedia%2FPlanetMediaTesting.git")  
cd PlanetMediaTesting  

#3. Compilar el proyecto  
mvn clean install  

#4. Ejecutar los tests  
Opción A: Ejecutar todos los tests  
mvn test  

Opción B: Ejecutar un test específico, por ejemplo GoogleBasicTests  
mvn -Dtest=GoogleBasicTests test  

#5. Resultados  
- Los logs se muestran en la consola.  
- Las capturas de pantalla se guardan en la carpeta definida en tu código, por ejemplo C:/Users/Dell/Documents/Capturas_test_Ubicacion/.  
- Los reportes de JUnit y Maven se generan en la carpeta target/surefire-reports/.  

#6. Notas adicionales  
- El navegador se abre maximizado gracias a la configuración en TestWebDriverConfig.  
- Los tests están ordenados con la anotación @Order, por lo que se ejecutan en la secuencia definida.  
- Si ejecutas en otra máquina, ajusta las rutas de las capturas de pantalla en tu clase SeleniumUtils.  





