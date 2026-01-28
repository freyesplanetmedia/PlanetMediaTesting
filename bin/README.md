# PlanetMediaTesting

Proyecto de ejemplo para pruebas automatizadas con Selenium + Spring Boot.

Estructura
- src/main/java: aplicación Spring Boot mínima
- src/test/java: pruebas, utilidades y configuración de WebDriver

Cómo ejecutar
1) Compilar:
```powershell
mvn -DskipTests=true package
```

2) Ejecutar tests (visibles, no headless):
```powershell
mvn -Dselenium.headless=false test
```

Notas
- El proyecto usa WebDriverManager para descargar chromedriver automáticamente.
- Si ejecutas desde IDE, asegúrate de pasar `-Dselenium.headless=false` en VM options para ver el navegador.
- Si Chrome no está en la ruta estándar, ajusta `WebDriverFactory` para usar `options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe")`.
