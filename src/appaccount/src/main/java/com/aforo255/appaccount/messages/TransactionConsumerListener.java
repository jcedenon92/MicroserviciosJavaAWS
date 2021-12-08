package com.aforo255.appaccount.messages;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
 
import com.aforo255.appaccount.dtos.TransactionRequest;
import com.aforo255.appaccount.models.AccountModel;
import com.aforo255.appaccount.services.IAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
 
import com.fasterxml.jackson.databind.ObjectMapper;
 
//@Component
public class TransactionConsumerListener {
    
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ObjectMapper objectMapper;
    private Logger log = LoggerFactory.getLogger(TransactionConsumerListener.class);
 
    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void OnMessage(ConsumerRecord<Integer, String> consumerRecord)
            throws JsonMappingException, JsonProcessingException {
                
                log.info("****************************************************************");
                log.info("****************************************************************");
                log.info("Consumer Receives in Microservice Account");
                log.info("ConsumerRecord : {}", consumerRecord.value());
        
                TransactionRequest requestEvent = objectMapper.readValue(consumerRecord.value(), TransactionRequest.class);
        
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
                log.info("****************************************************************");
                log.info("****************************************************************");       
 
            }
}
