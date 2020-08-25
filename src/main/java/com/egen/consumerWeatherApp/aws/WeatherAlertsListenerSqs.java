package com.egen.consumerWeatherApp.aws;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.egen.consumerWeatherApp.model.SQSMessage;
import com.egen.consumerWeatherApp.model.WeatherAlert;
import com.egen.consumerWeatherApp.service.AlertService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WeatherAlertsListenerSqs {
	

	    @Value("${sqs.url}")
	    private String sqsUrl;

	    @Value("${cloud.aws.credentials.accessKey}") 
	    private String awsAccessKey;

	    @Value("${cloud.aws.credentials.secretKey}")
	    private String awsSecretKey;

	    @Value("${cloud.aws.region.static}")
	    private String awsRegion;

	    private AmazonSQS amazonSQS;
	   // @Autowired(removed since I mentioned it in constructor 
	    private ObjectMapper objectMapper;
	   // @Autowired 
	    AlertService alertService;
	    @Autowired 
	    public WeatherAlertsListenerSqs(@Value("${sqs.url}") String sqsUrl,
	    		 @Value("${cloud.aws.credentials.accessKey}") String awsAccessKey,
	    		 @Value("${cloud.aws.credentials.secretKey}") String awsSecretKey,
	    		 @Value("${cloud.aws.region.static}") String awsRegion,
	    		 ObjectMapper objectMapper,
	    		 AlertService alertService,
	    		 AmazonSQS amazonSQS
	    		) {
	    	this.sqsUrl=sqsUrl;
	    	this.awsAccessKey=awsAccessKey;
	    	this.awsSecretKey=awsSecretKey;
	    	this.awsRegion=awsRegion;
	    	this.objectMapper=objectMapper;
	    	this.alertService=alertService;
	    	this.amazonSQS=amazonSQS;
	    }

	    @PostConstruct
	    private void postConstructor() {

	        

	        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
	                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
	        );

	        this.amazonSQS = AmazonSQSClientBuilder.standard().withCredentials(awsCredentialsProvider)
	        		 .withRegion(awsRegion).build();
	    }

	    public void startListeningToMessages() throws JsonMappingException, JsonProcessingException {

	        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqsUrl)
	                .withMaxNumberOfMessages(1)
	                .withWaitTimeSeconds(3);

	        while (true) {

	            final List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();

	            for (Message messageObject : messages) {
	                String message = messageObject.getBody();

	               // System.out.println(message);
	                
	                SQSMessage sqsMessage=objectMapper.readValue(message,SQSMessage.class);
	                System.out.println(sqsMessage);
	                WeatherAlert weatherAlert=objectMapper.readValue(sqsMessage.getMessage(),WeatherAlert.class);
	                System.out.println(weatherAlert);
	                alertService.addAlerts(weatherAlert);
	                deleteMessage(messageObject);
	            }
	        }
	    }

	    private void deleteMessage(Message messageObject) {

	        final String messageReceiptHandle = messageObject.getReceiptHandle();
	        amazonSQS.deleteMessage(new DeleteMessageRequest(sqsUrl, messageReceiptHandle));

	    }
	}