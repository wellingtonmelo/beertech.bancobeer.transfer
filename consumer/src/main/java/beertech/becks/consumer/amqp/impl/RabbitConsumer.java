package beertech.becks.consumer.amqp.impl;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import beertech.becks.consumer.amqp.AmqpConsumer;
import beertech.becks.consumer.services.ConsumerService;
import beertech.becks.consumer.services.impl.ConsumerServiceImpl;
import beertech.becks.consumer.tos.Message;

import java.util.List;

/** The class that listens to the rabbit queue and treats the delivered message */
@Component
public class RabbitConsumer implements AmqpConsumer<Message> {

  /** Logger */
  private static final Logger LOGGER = Logger.getLogger(ConsumerServiceImpl.class.getName());

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
