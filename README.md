# Integrating spring services with kafka inside docker

This deployment is made up of 2 spring services(svc1 & svc2) that communicate over a shared messaging service(kafka).
The implementation in itself is quite simple:
```
(topic:foo) -> svc1
    -> (topic:bar) -> svc2
        -> (topic:baz)
```

## Requirements
- `java(>1.8+)`
- `maven`
- `docker`
- `docker-compose`

## Setup
- `git clone ${repo_url}`
- `cd svc1`
- `./mvnw package`
- `cd ../svc2`
- `./mvnw package`
- `docker-compose up -d`
- `docker-compose logs -f` to inspect consolidate log stream of all service(s).


## Dry run
*In a new terminal*
- `docker ps`
- Ensure containers for the following are up:
    - zookeeper
    - kafka
    - svc1
    - svc2
- Now we establish a TTY from within the kafka container, in order to produce messages to topic foo,
which is the entrypoint for the flow.
    ```
    docker exec -it 01655d436678 bash -c '$KAFKA_HOME/bin/kafka-console-producer.sh --broker-list kafka:9092 --topic foo'
    > hello from the other side
    > come over to the dark side
    ```
- Switch back to the terminal where the logs are being tailed to verify if messages arrived.
     