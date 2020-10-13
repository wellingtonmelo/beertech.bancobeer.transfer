package beertech.becks.consumer.amqp.impl;

import beertech.becks.consumer.amqp.AmqpConsumer;
import beertech.becks.consumer.services.ConsumerService;
import beertech.becks.consumer.tos.Message;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** The class that listens to the rabbit queue and treats the delivered message */
@Component
public class RabbitConsumer implements AmqpConsumer<Message> {

  /** Logger */
  private static final Logger LOGGER = Logger.getLogger(RabbitConsumer.class);

  /** The consumer services */
  @Autowired private ConsumerService consumerService;

  /**
   * Consumes the message received from the queue
   *
   * @param message the message received from the queue
   */
  @Override
  @RabbitListener(queues = "#{queueNames}")
  public void consume(Message message) {
    LOGGER.info("Received message from the queue: " + message.toString());

    consumerService.processMessage(message);
  }
}
