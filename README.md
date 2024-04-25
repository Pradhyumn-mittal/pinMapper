# Pin Mapper

Welcome to Pin Mapper - your ultimate solution for computing distance and duration between pincodes! This Maven-built project, powered by Spring Boot and Java 17, offers a robust platform for seamless pincode analysis. Let's delve into its captivating features and seamless functionalities.

## Key Features

- **🌍 RESTful API**: Seamlessly compute distance and duration between pincodes with our intuitive API endpoints.
- **🗺️ Google Maps Integration**: Experience the power of Google Maps API, providing accurate route details, including distance and duration.
- **🚀 Caching Mechanism**: Boost performance and reduce API calls by implementing a smart caching mechanism with Redis.
- **💾 MongoDB Integration**: Store your computed distance, duration, and route data securely in our robust MongoDB database.
- **📡 Kafka Support**: Integrate effortlessly with the WeatherFetch project by publishing pincode details.
- **📚 Swagger Documentation**: Explore and test our API effortlessly with interactive Swagger documentation.
- **🧪 JUnit Testcases**: Ensures the reliability and stability of the application with comprehensive JUnit test coverage.
- **🔒 Security**: Implement secure authentication and authorization mechanisms to protect sensitive data and endpoints.
- **📈 Monitoring and Logging**: Monitor application performance and track logs for debugging and analytics purposes.
- **⚙️ Scalability**: Designed with scalability in mind, allowing easy horizontal scaling to handle increased traffic and data volume.
- **🔧 Configurability**: Easily configurable parameters and properties to adapt to different environments and use cases.

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/your_username/pin-mapper.git
   ```

2. **Navigate to the project directory**:

   ```bash
   cd pin-mapper
   ```

3. **Build the project**:

   ```bash
   mvn clean install
   ```

4. **Configure environment variables**:

   - `GOOGLE_MAPS_API_KEY`: Your Google Maps API key.
   - `MONGODB_URI`: URI for connecting to MongoDB.
   - `REDIS_HOST`: Hostname of the Redis server.
   - `REDIS_PORT`: Port of the Redis server.

5. **Run the application**:

   ```bash
   java -jar target/pin-mapper-0.0.1-SNAPSHOT.jar
   ```

6. **Access Swagger documentation**:

   Open your browser and navigate to `http://localhost:8081/pin-mapper/swagger-ui.html`.

## Required Documentation

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [MongoDB Java Driver Documentation](https://mongodb.github.io/mongo-java-driver/)
- [Redisson Documentation](https://redisson.org/)
- [Google Maps API Documentation](https://developers.google.com/maps/documentation)

For further assistance or inquiries, feel free to contact us at [Pradhyumn.work@gmail.com](mailto:Pradhyumn.work@gmail.com)!

Let Pin Mapper guide you through the world of pincodes effortlessly. Happy mapping! 🌟
