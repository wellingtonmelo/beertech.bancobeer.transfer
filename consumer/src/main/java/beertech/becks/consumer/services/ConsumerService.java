package beertech.becks.consumer.services;

import beertech.becks.consumer.tos.Message;

/**
 * Interface describing the consumer services
 */
public interface ConsumerService {
	void processMessage(Message message);
}
