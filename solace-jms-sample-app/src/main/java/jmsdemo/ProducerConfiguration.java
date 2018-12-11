package jmsdemo;

import javax.jms.ConnectionFactory;

import com.solacesystems.jms.SolConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration

public class ProducerConfiguration {

	@Autowired
	private SolConnectionFactory connectionFactory;

	// Example use of CachingConnectionFactory for the producer
//	@Bean
//	public JmsTemplate jmsTemplate() {
////		CachingConnectionFactory ccf = new CachingConnectionFactory(connectionFactory);
//		return new JmsTemplate(connectionFactory);
//	}
}
