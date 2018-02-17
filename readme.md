# ata-meetup

Trivial java application to greet people at Agile Test Automation Meetup.

## How to test

```sh
mvn clean test
```

## How to build

```sh
mvn clean package
```

## How to run

```sh
java -jar -Dhttp.server.port=${PORT} target/ata-meetup-jar-with-dependencies.jar
```

Default port is 8080.

## How to create docker image

```sh
docker build -t pdincau/ata-meetup .
```

## How to run docker image

```sh
docker run --rm=true -it -p 8080:8080 --name ata-meetup pdincau/ata-meetup
```

## Greeting route

You can check the health of the service by calling:

```sh
curl localhost:${PORT}/hello?name=yourname
```

## Healthcheck route

You can check the health of the service by calling:

```sh
curl localhost:${PORT}/ping
```
