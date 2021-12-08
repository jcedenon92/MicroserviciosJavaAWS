package com.aforo255.appmovements.messages;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.aforo255.appmovements.dtos.TransactionRequest;
import com.aforo255.appmovements.models.MovementModel;
import com.aforo255.appmovements.services.IMovementService;
import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TransactionConsumerListenerManual implements AcknowledgingMessageListener<Integer, String> {

    @Autowired
    private IMovementService movementService;
    @Autowired
    private ObjectMapper objectMapper;
    private Logger log = LoggerFactory.getLogger(TransactionConsumerListener.class);

    @Override
    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void onMessage(ConsumerRecord<Integer, String> data, Acknowledgment acknowledgment) {
        try {

            log.info("****************************************************************");
            log.info("****************************************************************");
            log.info("Consumer Receives in Microservice Account");
            log.info("ConsumerRecord : {}", data.value());

            TransactionRequest requestEvent = objectMapper.readValue(data.value(), TransactionRequest.class);

            log.info("Id Transaction: {} -  Type: {} - Ammount: {}", requestEvent.getId(), requestEvent.getType(),
                    requestEvent.getAmount());

            MovementModel movementModel = new MovementModel();
            movementModel.setTransactionId(requestEvent.getId());
            movementModel.setAmount(requestEvent.getAmount());
            movementModel.setType(requestEvent.getType());
            movementModel.setAccountId(requestEvent.getAccountId());
            movementModel.setCreationDate(requestEvent.getCreationDate());

            log.info("Register movement {}", requestEvent.getAccountId());
            movementService.add(movementModel);

            // Commit kafka
            acknowledgment.acknowledge();

            log.info("****************************************************************");
            log.info("****************************************************************");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
