package com.example.demo.controller;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("mqtt")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Test {

  private final MqttClient client;
  private static final Logger logger = LoggerFactory.getLogger(Test.class);

  @PostMapping
  public ResponseEntity<String> test(){
    String topic = "test";
    String message = "Test";
    try {
      client.publish(topic, new MqttMessage(message.getBytes()));
      logger.info("Message published to topic '" + topic + "'");
      return ResponseEntity.ok("Message published");
    } catch (MqttException e) {
      System.err.println("Failed to publish message: " + e.getMessage());
      logger.error("Failed send message: ", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to publish message");
    }
  }

}
