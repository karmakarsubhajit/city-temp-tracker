# CityTempTracker

City Temperature Tracker is project which downloads temperature data of many cities periodically from Open Weather API. This data is stored in Elastic Search and an API is exposed to let user fetch temperature and other related details of multiple cities.

## Features

- **Asynchronous Data Fetching**: Fetches temperature data for 100 cities every day using a scheduled task.
- **Elasticsearch Integration**: Stores weather data efficiently in Elasticsearch for quick retrieval.
- **REST API**: Exposes endpoints to retrieve temperature and related data for cities.
- **Dockerized Setup**: Easily deploy the entire stack using Docker and Docker Compose.

## How to get it up and running?

### Languages and technologies required on local

1. **Java 17 or later**: The application is built using Java 17.
2. **Rancher Desktop / Colima**: For managing your local Kubernetes cluster.
3. **Docker**: Required for running Elasticsearch and the application within containers.


### Clone the Repository

```bash
git clone https://github.com/karmakarsubhajit/CityTempTracker.git
cd CityTempTracker
```
