# Drone-Service

## Build and run

I'm using spring boot with H2 database, so everything should work out of the box.

```shell
./gradlew bootJar
```

Or using docker

```shell
docker build -t my-app .
docker run -p 8080:8080 my-app
```

## Test

### Swagger URL

http://localhost:8080/swagger-ui.html

1. Register Drone in `drone-controller > @POST("/v1/drone")`
2. List available drones using `drone-controller > @GET("/v1/available-drones")`
3. Load medications in `drone-controller > @POST("/v1/drone/{drone-id}/load")`
4. List medications in specific drone using `drone-controller > @GET("/v1/drone/{drone-id}/medications")`
5. Check drone state and battery using `drone-controller > @GET("/v1/drone/{drone-id}")`

Drone logs can be checked using APIs in drone-log-controller


## Assumptions

1. Drone will periodically call updateBattery API to synchronize its battery capacity with backend
2. Drone will call updateState apis to update it's state to loaded, delivering, delivered, returning and idle.

