package com.beertech.becks.controllers;

import com.beertech.becks.services.BancoBeerRelayService;
import com.beertech.becks.vos.TransactionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(path = "/transfer")
public class BancoBeerRelayController {

  @Autowired BancoBeerRelayService relayService;

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PostMapping
  public ResponseEntity<Void> transferBalance(@RequestBody @Valid TransactionMessage message)
      throws IOException {
    relayService.process(message);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
