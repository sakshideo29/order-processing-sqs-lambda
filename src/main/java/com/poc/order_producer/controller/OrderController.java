package com.poc.order_producer.controller;

import com.poc.order_producer.dto.OrderRequest;
import com.poc.order_producer.service.SqsProducerService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.thirdparty.jackson.core.JsonProcessingException;
import tools.jackson.databind.ObjectMapper;


import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final SqsProducerService sqsProducerService;
    private final ObjectMapper objectMapper;

    public OrderController(
            SqsProducerService sqsProducerService,
            ObjectMapper objectMapper) {
        this.sqsProducerService = sqsProducerService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createOrder(
            @Valid @RequestBody OrderRequest orderRequest)
            throws JsonProcessingException {

        String messageBody = objectMapper.writeValueAsString(orderRequest);

        String messageId = sqsProducerService.sendMessage(messageBody);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(Map.of(
                        "message", "Order sent to SQS successfully",
                        "messageId", messageId
                ));
    }
}
