# Clientes y Servicios AREP 

En este laboratorio se busco hacer un programa que funcionara como un aplicativo web estatico cliente-servidor que soportara
multiples solcitudes y que este fuera capaz de responderlas retornando sus archivos correspondientes, en este trabajo
se utilizaron archivos html y png para probar el funcionamiento del servidor, para encontrar más detalles mirar el archivo 
Articulo.pdf.


## Compilar programa
- Para construir el programa y ejecutar todas las fases de un repositorio maven
```
mvn clean install
``` 
- Para correr los tests
```
mvn test
```
- Para generar la documentación
```
mcn javadoc:javadoc o mvn javadoc:jar (generar jar)
```

## Ejecutar Programa
Para probar el programa localmente, ejecutar y dirigirse a
```
http://localhost:35000/index.html
```
![image](https://user-images.githubusercontent.com/59893804/133163939-095b9504-6fd6-48e6-b69e-05aa961230cb.png)


Para probar el programa localmente y cargar las imagenes, ejecutar y dirigirse a
```
http://localhost:35000/imagen1.png
http://localhost:35000/imagen2.png
```
![image](https://user-images.githubusercontent.com/59893804/133164040-e4732f5e-f731-404c-98a5-09c6747d832e.png)

## Enlace Heroku
[![Clientes y Servicios ](https://www.herokucdn.com/deploy/button.png)](https://clientesyservicios-arep.herokuapp.com/index.html)

## Licencia
Ver licencia en LICENCE.txt para más detalles.