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
The application will start, and you can access it at http://localhost:8089. The scheduler will download temperature and related data of 100 cities and index it using elasticsearch.

### 6. API Endpoints
A Get API is exposed which takes a list of cities as input and queries the city temperature and related data from Elastic Search and returns the response.

**Endpoint:** `http://localhost:8089/api/v1/internal/city/temperature`

**Method:** `GET`

**Headers:**
- `Content-Type: application/json`

**Request Body:**
```json
{
  "data": ["Bangalore","Kolkata"]
}
```

**Response:**
```json
{
    "data": [
        {
            "recordId": "4d1c2a0b-2a75-444f-91a9-24be9e40c2c9",
            "city": "Bangalore",
            "temperatureInF": 79.0,
            "temperatureInC": 26.1,
            "extraInfo": {
                "location": {
                    "name": "Bangalore",
                    "region": "Karnataka",
                    "country": "India",
                    "lat": 12.98,
                    "lon": 77.58,
                    "localtime": "2024-08-22 21:51"
                },
                "current": {
                    "last_updated_epoch": 1724343300,
                    "last_updated": "2024-08-22 21:45",
                    "temp_c": 26.1,
                    "temp_f": 79.0,
                    "is_day": 0,
                    "condition": {
                        "text": "Partly cloudy",
                        "icon": "//cdn.weatherapi.com/weather/64x64/night/116.png",
                        "code": 1003
                    },
                    "wind_kph": 15.1,
                    "wind_degree": 230,
                    "wind_dir": "SW",
                    "pressure_mb": 1016.0,
                    "pressure_in": 30.0,
                    "precip_mm": 0.0,
                    "precip_in": 0.0,
                }
            },
            "dataSyncTimestamp": "2024-08-22 21:53:36.485"
        },
        {
            "recordId": "e52277fc-6c0f-43cc-9b1d-e1cde1173d81",
            "city": "Kolkata",
            "temperatureInF": 81.0,
            "temperatureInC": 27.2,
            "extraInfo": {
                "location": {
                    "name": "Kolkata",
                    "region": "West Bengal",
                    "country": "India",
                    "lat": 22.57,
                    "lon": 88.37,
                    "localtime": "2024-08-22 21:51"
                },
                "current": {
                    "last_updated_epoch": 1724343300,
                    "last_updated": "2024-08-22 21:45",
                    "temp_c": 27.2,
                    "temp_f": 81.0,
                    "is_day": 0,
                    "condition": {
                        "text": "Moderate or heavy rain with thunder",
                        "icon": "//cdn.weatherapi.com/weather/64x64/night/389.png",
                        "code": 1276
                    },
                    "wind_kph": 3.6,
                    "wind_degree": 10,
                    "wind_dir": "N",
                    "pressure_mb": 1005.0,
                    "pressure_in": 29.68,
                }
            },
            "dataSyncTimestamp": "2024-08-22 21:53:29.925"
        }
    ]
}
```



