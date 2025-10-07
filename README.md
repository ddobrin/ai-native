# AI-Native Spring Boot Application

This project is a Spring Boot application that demonstrates how to build a native Java image for an AI-powered application using GraalVM.

## Prerequisites

*   Java 21 or later
*   Maven 3.9 or later
*   A configured GraalVM environment for native image compilation
*   A Google AI API key

## Configuration

Before running the application, you need to provide your Google AI API key. 

export GOOGLE_API_KEY=<...>

## Building the Application

You can build this project in two ways: as a standard JVM-based JAR or as a native executable.

### Standard JAR (JIT Compilation)

To build a standard, executable JAR file, run the following Maven command from the project root:

```bash
mvn clean package
```

This will create a JAR file in the `target/` directory (e.g., `ai-native-0.0.1-SNAPSHOT.jar`).

### Native Image (AOT Compilation)

To build a native executable, you need to use the `native` profile provided in the `pom.xml`. This requires a GraalVM JDK to be configured correctly.

Run the following command:

```bash
mvn clean package -Pnative
```

This process will take longer than a standard build as it performs ahead-of-time compilation. The final executable will be located in the `target/` directory (e.g., `ai-native`).

## Running the Application

### Running the JAR

To run the application from the JAR file, use the following command:

```bash
java -jar target/ai-native-0.0.1-SNAPSHOT.jar
```

### Running the Native Executable

To run the compiled native image, simply execute the file from the `target/` directory:

```bash
./target/ai-native
```

The application will start on port `8080` by default.

## Testing the Application

### Running Unit and Integration Tests

To run the test suite for the project, use the following Maven command:

```bash
mvn test
```

### Manual Testing with cURL

Once the application is running, you can test the `/generate` endpoint using a tool like `curl`. Open a new terminal and run the following command to send a request with a custom message:

```bash
curl -X POST "http://localhost:8080/generate"     -H "Content-Type: application/json"     -d '{"message": "Tell me a joke"}'
```

You can use any message string you want to send to the generative AI model. The application will print the streaming response to the console where the application is running and return a simple "ok" message in the HTTP response.
