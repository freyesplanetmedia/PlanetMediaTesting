# PlanetMediaTesting

Proyecto de ejemplo para pruebas automatizadas con Selenium + Spring Boot.

Estructura
- src/main/java: aplicacion Spring Boot minima
- src/test/java: pruebas, utilidades y configuracion de WebDriver

Como ejecutar
1) Compilar:
```powershell
mvn -DskipTests=true package
```

2) Ejecutar tests (visibles, no headless):
```powershell
mvn -Dselenium.headless=false test
```

Notas
- El proyecto usa WebDriverManager para descargar chromedriver automaticamente.
- Si ejecutas desde IDE, asegurate de pasar `-Dselenium.headless=false` en VM options para ver el navegador.
- Si Chrome no esta en la ruta estandar, ajusta `WebDriverFactory` para usar `options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe")`.
