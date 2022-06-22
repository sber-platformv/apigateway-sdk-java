# API Gateway SDK для подписи запросов на Java

Для вызова API опубликованных c режимом аутентификации APP, запрос должен быть подписан при помощи APP_KEY и SECRET_KEY.
Для подписи запросов используются API Gateay SDK на одном из поддерживаемых языков программирования.

## Пререквизиты

* Java 11 и выше.

## Подключение SDK к проекту

1. Импортируйте SDK в проект.
    ```xml
   <dependency>
        <groupId>ru.sber.platformv.faas</groupId>
        <artifactId>apigateway-sdk-java</artifactId>
   <dependency>
    ```

## Пример использования SDK

1. Создайте запрос и передайте в него `APP_KEY` и `SECRET_KEY`.
    ```java
    Request request = new Request();
        request.setKey("key");
        request.setSecret("secret");
        request.setMethod("GET");
        request.setUrl("https://my-domain.example.com/v1/test");
        request.addHeader("Content-Type", "text/plain");
        request.setBody("demo");
    ```
  
3. Подпишите запрос
    ```java
    HttpRequestBase signedRequest = Client.sign(request);
    ```

4. Обратитесь к API и просмотрите ответ.
    ```java
    CloseableHttpClient client = HttpClients.custom().build();
    HttpResponse response = client.execute(signedRequest);
    System.out.println(response.getStatusLine().toString());
    ```
