package com.aforo255.appmovements.models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Document(collection = "movement")
public class MovementModel {
    @BsonId
	private String id ; 
    private Integer transactionId;
	private double amount ;
	private String type;
	private Integer accountId;
    private String creationDate;
    private String processDate;
    
    public MovementModel() {
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");   
		this.setProcessDate(dateFormat.format(date));
    }

    
    public String getCreationDate() {
        return creationDate;
    }


    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


    public String getProcessDate() {
        return processDate;
    }


    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }


    public Integer getTransactionId() {
        return transactionId;
    }
    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
}
