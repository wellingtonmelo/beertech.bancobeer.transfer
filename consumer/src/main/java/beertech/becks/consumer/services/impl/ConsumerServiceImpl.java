package beertech.becks.consumer.services.impl;

import beertech.becks.consumer.services.ConsumerService;
import beertech.becks.consumer.tos.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/** The class implementing the description of the consumer services */
@Service
public class ConsumerServiceImpl implements ConsumerService {
  /** Logger */
  private static final Logger LOGGER = Logger.getLogger(ConsumerServiceImpl.class.getName());

  /** The API endpoint */
  @Value("${api.transactions.endpoint}")
  private String endpoint;

  /**
   * Posts the received rabbit message to the API
   *
   * @param message the rabbit message received
   */

  @Override
  public void processMessage(Message message) {

    if (message.getOperation().equals("T")) {
      transferBalance(message);
    } else {
      createTransaction(message);
    }
  }

  private void createTransaction(Message message) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String json = null;
    try {
      json = new ObjectMapper().writeValueAsString(message);
    } catch (JsonProcessingException e) {
      LOGGER.error("Error converting message to json: " + e.getMessage());
    }

    new RestTemplate().postForLocation(endpoint, new HttpEntity<>(json, headers));
  }

  private void transferBalance(Message message) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String json = null;
    try {
      json = new ObjectMapper().writeValueAsString(message);
    } catch (JsonProcessingException e) {
      LOGGER.error("Error converting message to json: " + e.getMessage());
    }

    new RestTemplate().postForLocation(endpoint, new HttpEntity<>(json, headers));
  }
}
