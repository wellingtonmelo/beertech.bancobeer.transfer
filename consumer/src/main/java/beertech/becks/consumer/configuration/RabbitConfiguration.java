package beertech.becks.consumer.configuration;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/** The configuration class for our rabbit queue */
@Configuration
public class RabbitConfiguration {

  @Value("${spring.rabbitmq.queues}")
  private List<String> queues;

  /**
   * The jackson converter to convert json messages into objects
   *
   * @return a Jackson2JsonMessageConverter instance
   */
  @Bean
  MessageConverter jacksonConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public List<String> queueNames() {
    return queues;
  }
}
