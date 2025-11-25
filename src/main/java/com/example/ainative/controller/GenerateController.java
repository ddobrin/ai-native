package com.example.ainative.controller;

import com.google.genai.Client;
import com.google.genai.ResponseStream;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class GenerateController {

    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestBody Map<String, String> request) {
        String message = request.getOrDefault("message", "Hello, World!");
        String modelId = "gemini-3-pro-preview";

        Client client = new Client();

        if (client.vertexAI()) {
            System.out.println("Using Vertex AI");
        } else {
            System.out.println("Using API Key");
        }

        long startTime = System.currentTimeMillis();
        System.out.println("Test streaming model");
        System.out.println("ModelId: " + modelId);
        System.out.println("Message: " + message);
        ResponseStream<GenerateContentResponse> responseStream =
            client.models.generateContentStream(modelId, message, null);
        System.out.println("Streaming response: " + (System.currentTimeMillis() - startTime) + "ms");
        for (GenerateContentResponse res : responseStream) {
            System.out.print(res.text());
        }

        // To save resources and avoid connection leaks, it is recommended to close the response
        // stream after consumption (or using try block to get the response stream).
        responseStream.close();

        startTime = System.currentTimeMillis();
        System.out.println("\n\nTest non-streaming model");
        System.out.println("ModelId: " + modelId);
        System.out.println("Message: " + message);
        GenerateContentResponse response =
            client.models.generateContent(modelId, message, null);
        System.out.println("Non-streaming response: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.print(response.text());

        return ResponseEntity.ok("ok");
    }
}