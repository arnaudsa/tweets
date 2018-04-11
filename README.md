# tweets

### Build
No diretório raiz do projeto executar os seguintes comandos.

```sh
- $ mvn clean install
- $ docker build -t tweets .

Para verificar se a imagem tweets foi criada execute o comando.
$ docker images

```

No diretório raiz do projeto temos o arquivo Dockerfile, execute o comando abaixo para criar 
a imagem docker.
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
