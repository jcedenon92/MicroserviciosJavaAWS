package com.aforo255.appaccount.messages;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.aforo255.appaccount.dtos.TransactionRequest;
import com.aforo255.appaccount.models.AccountModel;
import com.aforo255.appaccount.services.IAccountService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TransactionConsumerListenerManual implements AcknowledgingMessageListener<Integer, String> {

    @Autowired
    private IAccountService accountService;
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

            double newAmount = 0;
            AccountModel accountModel = new AccountModel();
            accountModel = accountService.findById(requestEvent.getAccountId());
            switch (requestEvent.getType()) {
                case "deposit":
                    newAmount = accountModel.getTotalAmount() + requestEvent.getAmount();
                    break;

                case "withdrawal":
                    newAmount = accountModel.getTotalAmount() - requestEvent.getAmount();
                    break;
            }
            accountModel.setTotalAmount(newAmount);
            log.info("Update account {}", requestEvent.getAccountId());
            accountService.update(accountModel);
            
            //Commit Kafka
            acknowledgment.acknowledge();
            
            log.info("****************************************************************");
            log.info("****************************************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
