# Use an official OpenJDK runtime as a parent image
FROM khipu/openjdk17-alpine

# Set the working directory
WORKDIR /shop-api

# Copy the JAR file and other required files into the container
COPY jks.jks ssl/jks.jks
COPY keystore.p12 ssl/keystore.p12
COPY target/shop-api-0.0.1-SNAPSHOT.jar target.jar

# Run the JAR file
CMD ["java", "-jar", "target.jar"]
