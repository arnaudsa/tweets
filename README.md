# tweets

**```Criando a imagem docker```**
```
No diretório raiz do projeto temos o arquivo Dockerfile, execute o comando para criar a imagem docker.
```
Java 8
Maven 3
Lombok 1.16


**```Build```**
```java
mvn clean install -U jacoco:prepare-agent jacoco:report sonar:sonar -Dsonar.host.url=http://sonar.ns2online.com.br/
```

**```Run Stress Test```**
```java
mvn gatling:execute
```
