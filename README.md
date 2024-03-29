# Introducción

Este proyecto esta generado con Java 1.8 y Sprong Boot 2.7.16. Para desplegar la solución se siguieron los pasos que se detallan a continuación:

Repositorio GitHub: https://github.com/christiangarciareyes/backend-notas/tree/master

Repositorio Docker Hub: https://hub.docker.com/r/cgarciar29/backend-notas

# 1. Generar artefecto Java

`mvn clean install -DskipTests`

# 2. Crear imagen docker

`docker build -t backend-notas .`

# 3. Crear contenedor

`docker run -p 8145:8145 --name notas backend-notas`

# 4. Detener contenedor

`docker stop notas`

# 5. Borrar contenedor

`docker rm notas`

Para subir la solución a un respositorio de Docker Hub se necesita seguir con los pasos que se detallan a continuación:

# 1. Autenticarse a Docker Hub

`docker login`

# 2. Generar un identificador por usuario

`docker tag backend-notas cgarciar29/backend-notas:latest`

# 3. Subir imagen a repositorio

`docker push cgarciar29/backend-notas:latest`

# 4. Imagen subido a respositorio correctamente

Para descargar la solución desde un respositorio de Docker Hub se necesita seguir con los pasos que se detallan a continuación:

# 1. Descargar imagen de contenedor público

`docker pull cgarciar29/backend-notas`

# 2. Crear contenedor desde imagen descargada

`docker run -p 8145:8145 --name notas cgarciar29/backend-notas`

Revisar la colección para la prueba de consumo de servicios de la solución presentada.
