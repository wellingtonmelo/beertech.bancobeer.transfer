package beertech.becks.consumer.amqp;

/**
 * Interface describing the ampq consumer services
 * 
 * @param <T> the type of the message being received
 */
public interface AmqpConsumer<T> {

	void consume(T message);
}
