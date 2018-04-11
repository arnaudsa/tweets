# tweets

### Tecnologias Utilizadas
* Java 1.8
* SpringFramework - Injeção de dependências, Rest
* Jackson - Serialização e Deserialização de objetos (JSON)
* Junit - testes unitários
* Mock Server - Mocar a chamada de serviços rest
* Lombok - Gerar getters, setters, builders
* Swagger - Documentação da API


### Build
```
No diretório raiz do projeto executar os seguintes comandos.

$ mvn clean install
$ docker build -t tweets .

Para verificar se a imagem tweets foi criada execute o comando.
$ docker images

```

### Executando a aplicação
```
$ docker run -p 8080:8080 -d tweets:latest

No browser acessar a url http://localhost:8080

```
