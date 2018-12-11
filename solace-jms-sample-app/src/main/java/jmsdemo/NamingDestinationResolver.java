package jmsdemo;

import org.springframework.jms.support.destination.DestinationResolver;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @author Howells
 * @ClassName NamingDestinationResolver.java
 * @Description TODO
 * @createTime 2018年11月29日 08:49:00
 */
public class NamingDestinationResolver implements DestinationResolver {
    public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
        if (destinationName.endsWith("Queue")) {
            return session.createQueue(destinationName);
        } else  if(destinationName.endsWith("Topic")) {
            return session.createTopic(destinationName);
        }
        throw new RuntimeException("Naming convention not respected for destination " + destinationName);
    }
}
