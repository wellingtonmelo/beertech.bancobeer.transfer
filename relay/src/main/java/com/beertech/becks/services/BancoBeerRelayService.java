package com.beertech.becks.services;

import com.beertech.becks.senders.BancoBeerMessageSender;
import com.beertech.becks.vos.TransactionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BancoBeerRelayService {

  private final BancoBeerMessageSender messageSender;

  @Autowired
  public BancoBeerRelayService(BancoBeerMessageSender messageSender) {
    this.messageSender = messageSender;
  }

  public void process(TransactionMessage message) {
    message.setOperation("T");
    messageSender.send(message);
  }
}
