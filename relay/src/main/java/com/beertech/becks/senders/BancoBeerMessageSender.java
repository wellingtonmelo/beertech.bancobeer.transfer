package com.beertech.becks.senders;

import com.beertech.becks.vos.TransactionMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BancoBeerMessageSender {

  @Value("${amqp.exchange}")
  private String exchangeName;

  @Value("${amqp.routeKey}")
  private String routeKey;

  private AmqpTemplate amqpTemplate;

  @Autowired
  public BancoBeerMessageSender(final AmqpTemplate amqpTemplate) {
    this.amqpTemplate = amqpTemplate;
  }

  public void send(final TransactionMessage transactionMessage) {
    amqpTemplate.convertAndSend(exchangeName, routeKey, transactionMessage);
  }
}
