# CityTempTracker

City Temperature Tracker is project which downloads temperature data of many cities periodically from weatherapi. This data is stored in Elastic Search and an API is exposed to let user fetch temperature and other related details of multiple cities. It is built using Java , Spring Boot, ElasticSearch.

## Features

- **Asynchronous Data Fetching**: Fetches temperature data for 100 cities every day using a scheduled task.
- **Elasticsearch Integration**: Stores weather data efficiently in Elasticsearch for quick retrieval.
- **REST API**: Exposes endpoints to retrieve temperature and related data for cities.
- **Dockerized Setup**: Easily deploy the entire stack using Docker and Docker Compose.

## How to get it up and running?

### 1. Languages and technologies required on local

1. **Java 17 or later**: The application is built using Java 17.
2. **Rancher Desktop / Colima**: For managing your local Kubernetes cluster.
3. **Docker**: Required for running Elasticsearch and the application within containers.


### 2. Clone the Repository

```bash
git clone https://github.com/karmakarsubhajit/CityTempTracker.git
cd CityTempTracker
```

### 3. Configure API Keys

1. Obtain an API key from [weatherapi](https://www.weatherapi.com).
2. In application.properties file in the src/main/resources directory, update the field temp.service.api.key with the key generated above
```bash
temp.service.api.key=your_generated_api_key
```

### 4. Build the Project

Use Gradle to build the project:
```bash
./gradlew clean build
```

### 5. Starting the application

#### a. Start Elasticsearch

The application uses Elasticsearch for storing city temperature data. To set up Elasticsearch, use Docker Compose:

```bash
docker-compose up -d
```
This command will start the Elasticsearch container. Ensure Elasticsearch is running before starting the application.

#### b. Build and run the application
```bash
./gradlew bootRun
```
The application will start, and you can access it at http://localhost:8089.


