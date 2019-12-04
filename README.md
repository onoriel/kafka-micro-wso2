# kafka-micro-wso2
Event Driven Kafka Microservices secured and expose through WSO2 Api Manager 

## Infrastructure Setup

### Single Zookeeper / Multiple Kafka

* Zookeeper is up and running
Zookeeper is required to manage the kafka cluster & to select the leader nodes for kafka topics partition etc.
* Kafka broker is up and running
In real life, nobody runs just 1 broker. we run multiple brokers. Kafka brokers have the messages for the topics.

If you want to have three brokers and experiment with kafka replication / fault-tolerance.

- Zookeeper will be available at `$DOCKER_HOST_IP:2181`
- Kafka will be available at `$DOCKER_HOST_IP:9092,$DOCKER_HOST_IP:9093,$DOCKER_HOST_IP:9094`

Enter to **_kafka-config_** folder and run:
```
docker-compose -f zk-single-kafka-multiple.yml up
docker-compose -f zk-single-kafka-multiple.yml down
```

###   DB - Config
Enter to **_db-config_** folder and run:
```
docker-compose -f docker-compose.yaml up
docker-compose -f docker-compose.yaml down
```
###   WSO2 - API Manager [Reference Guide](https://hub.docker.com/r/wso2/wso2am)

* Pull latest **_wso2am_** image from ducker hub:
	```
	$ docker pull wso2/wso2am
	```
* The following command starts a Linux Ubuntu-based API Manager Docker image.
	```
	$ docker run -it -p 8280:8280 -p 8243:8243 -p 9443:9443 --name api-manager wso2/wso2am	
	```
#### Accessing management console
To access the management console, use the Docker host IP and port 9443 as follows:
```
https://{DOCKER_HOST}:9443/carbon
```
To access the API Manager Publisher, use the Docker host IP and port 9443 as follows:
```
https://{DOCKER_HOST}:9443/publisher
```
To access the API Manager Store, use the Docker host IP and port 9443 as follows:
```
https://{DOCKER_HOST}:9443/store
```
## Persistence Setup

### Kafka Manager

You can access the Kafka manager at localhost:9000  (If you are running docker-toolbox, then use the IP of the VM instead of localhost)

* Click on the cluster drop down to add our cluster.
  - Name it as you wish: kc-onoriel
  - The zookeeper address is zoo:2181
* Now click on the Topic drop down to create a new topic
  - Name it as user-service-event
  - Create 3 partitions with 2 replica

### Database Schema

You can access the PgAdmin manager at http://localhost/browser/  (If you are running docker-toolbox, then use the IP of the VM instead of localhost)
* Click on create new server.
  - Host: Use the dockerhost IP instead of localhost or 127.0.0.1
  - username: onoriel
  - password: admin
  - port 5432
Then, open a Query Tool tab using the main menu and execute the schema.sql content inside the **_db-config_** folder


## Start Microservices
Enter to **_services_** folder and run inside each service folder:
```
mvn spring-boot:run
```

## Config API Manager
Either you can set up this repo services or create a new one following this guide:
* [Config new back end service with WSO2](https://apim.docs.wso2.com/en/latest/GettingStarted/quick-start-guide/)

To config, our order service as a WSO2 Api Manager back end just follow the previous step and change the service endpoint to
```
http://[DOCKER_HOST_IP]:8081/order-service/all
```

## Test services

### Update User

These 
```
curl -X PUT \
  http://localhost:8080/user-service/update \
  -H 'Content-Type: application/json' \
  -d '{
    "id": 1,
    "firstname":"onoriel",
    "lastname": "munoz",
    "email": "admin-updated@vass.es"
}'
```
> At this point, we should be able to successfully run the user-service. We should be able to create users / update users. Whenever user info is updated, we raise an event to the Kafka topic.

### Create Order
With postman or any API REST tool send a request to create a new order:
```
curl -X POST \
  http://localhost:8081/order-service/create \
  -H 'Content-Type: application/json' \
  -d '{
        "user": {
        	"id": 1,
            "firstname":"onoriel1",
		    "lastname": "munoz",
		    "email": "admin-updated@vass.es"
        },
        "product": {
            "id": 1,
            "description": "ipad"
        },
        "price": 300
    }'
```

### Get Orders using API Manager

To get all the order thru the API Manager you have two options:

1. Use the scrip **_TestGetAllOrders.sh_** inside WSO2 folder, first you have to set up values for CONSUMER_KEY and CONSUMER_SECRET inside the script which are defined by WSO2 in the application PROD KEYS section.
2. With postman or any API REST tool send a request to retrieve all the orders:

    Get a token:
    ```
    curl -k -X POST https://localhost:8243/token -d "grant_type=client_credentials" -H"Authorization: Basic $(echo -n "$CONSUMER_KEY:$CONSUMER_SECRET"|base64)"
    ```
    Retrieve orders:
    ```
    curl -X GET --insecure \
      https://localhost:8243/order/1.0.0 \
      -H 'Authorization: Bearer [AUTH_TOKEN]'
    ```





