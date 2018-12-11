package jmsdemo;

import com.solacesystems.jcsmp.Topic;
import com.solacesystems.jcsmp.impl.TopicImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author Howells
 * @ClassName test.java
 * @Description TODO
 * @createTime 2018年11月28日 13:52:00
 */
@Configuration
public class test {
    @Bean
    public TestBean peoBean(){
        TestBean bean=new TestBean();
        bean.setName("peo");
        return  bean;
    }
}
