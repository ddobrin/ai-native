package com.example.ainative.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.genai.Client;
import com.google.genai.JsonSerializable;
import com.google.genai.ResponseStream;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GenerateController {

    @GetMapping("/generate")
    public ResponseEntity<?> generate(@RequestParam(value = "message", defaultValue = "Hello, World!") String message) {
        String modelId = "gemini-2.5-flash";

        Client client = new Client();

        if (client.vertexAI()) {
            System.out.println("Using Vertex AI");
        } else {
            System.out.println("Using Gemini Developer API");
        }

        ResponseStream<GenerateContentResponse> responseStream =
            client.models.generateContentStream(modelId, "Tell me a story in 300 words.", null);

        System.out.println("Streaming response: ");
        for (GenerateContentResponse res : responseStream) {
            System.out.print(res.text());
        }

        // To save resources and avoid connection leaks, it is recommended to close the response
        // stream after consumption (or using try block to get the response stream).
        responseStream.close();

        return ResponseEntity.ok("ok");
    }
}