package jmsdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Service
    static class MessageProducer implements CommandLineRunner {

        private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

        @Autowired
        private JmsTemplate jmsTemplate;
        /*
        For backwards compatibility:
        - As before, these exist only in the specific scenario where the app is deployed in Cloud Foundry.*/
        @Value("${solace.jms.demoQueueName}")
        private String queueName;

        @Override
        public void run(String... strings) {
            String msg ="{\"eventInfo\":{\"eventCode\":\"BR\"},\"shipment\":{\"party\":[{\"partyId\":1,\"companyId\":\"SMARUS772643806\",\"partyName\":\"TEST COMPANY\",\"partyType\":\"CGN\"}],\"numberAssociation\":[{\"csBookingReferenceNumber\":\"BR001\",\"bookingNumber\":\"BC004\"}],\"container\":[{\"containerId\":\"1\",\"containerNumber\":\"OOCL111111\",\"checkDigital\":\"1\",\"sizeType\":\"45GC\",\"grossWeight\":\"20KGS\",\"inboundHaulage\":\"M\",\"outboundHaulage\":\"M\",\"inboundTrafficeMode\":\"Rail\",\"outboundTrafficeMode\":\"Truck\",\"isSoc\":\"0\",\"refeerInfo\":{\"atmosphereType\":\"NP\",\"temperature\":\"1\",\"temperatureUnit\":\"C\"}},{\"containerId\":\"2\",\"containerNumber\":\"OOCL222222\",\"checkDigital\":\"2\"}],\"extenalReference\":[{\"referenceId\":1,\"referenceType\":\"PO\",\"referenceNumber\":\"PO111111\"}],\"cargo\":[{\"cargoId\":1,\"cargoDescription\":\"iMac mini\",\"grossWeight\":4500,\"grossWeightUnit\":\"KG\",\"netWeight\":800,\"netWeightUnit\":\"KG\"}],\"routes\":[{\"point\":[{\"locationType\":\"MTP\",\"locationName\":\"\",\"arrivalEstimateTime\":{}},{\"locationType\":\"ORG\",\"locationName\":\"\",\"arrivalEstimateTime\":{}},{\"locationType\":\"POL\",\"locationName\":\"HamburgHamburgHamburgDEHAMGermany\",\"arrivalService\":\"\",\"arrivalVesselName\":\"COSCO ADEN\",\"arrivalVoyageNumber\":\"822E\",\"arrivalDirection\":\"\",\"arrivalEstimateTime\":{},\"departureService\":\"\",\"departureVesselName\":\"COSCO ADEN\",\"departureVoyageNumber\":\"822E\",\"departureDirection\":\"\",\"departureEstimateTime\":{}},{\"locationType\":\"POD\",\"locationName\":\"HamburgHamburgHamburgDEHAMGermany\",\"arrivalService\":\"\",\"arrivalVesselName\":\"COSCO ADEN\",\"arrivalVoyageNumber\":\"822E\",\"arrivalDirection\":\"\",\"arrivalEstimateTime\":{},\"departureService\":\"\",\"departureVesselName\":\"COSCO ADEN\",\"departureVoyageNumber\":\"822E\",\"departureDirection\":\"\",\"departureEstimateTime\":{}},{\"locationType\":\"FND\",\"locationName\":\"\",\"arrivalEstimateTime\":{}}]}]}}";
            logger.info("============= Sending " + msg);
            System.out.println("successful");
            for(int i=0;i<100;i++){
                logger.info("========第"+i+"个========");
                this.jmsTemplate.convertAndSend("Queue1", msg);
            }
        }
    }

    @Component
    static class MessageHandler {

        private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

        // Retrieve the name of the queue from the application.properties file
        @JmsListener(destination = "Queue")
        public void processMsg(Message msg) {
        	StringBuilder msgAsStr = new StringBuilder("============= Received \nHeaders:");
        	MessageHeaders hdrs = msg.getHeaders();
        	msgAsStr.append("\nUUID: ").append(hdrs.getId());
        	msgAsStr.append("\nTimestamp: ").append(hdrs.getTimestamp());
            for (String key : hdrs.keySet()) {
                msgAsStr.append("\n").append(key).append(": ").append(hdrs.get(key));
            }
        	msgAsStr.append("\nPayload: ").append(msg.getPayload());
            logger.info(msgAsStr.toString());
        }
    }


}
