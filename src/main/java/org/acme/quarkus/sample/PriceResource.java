package org.acme.quarkus.sample;

import io.smallrye.reactive.messaging.annotations.Stream;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.acme.quarkus.sample.domain.Transaction;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * A simple resource retrieving the in-memory "my-data-stream" and sending the items as server-sent events.
 */
@Path("/prices")
public class PriceResource {

    @Inject
    @Stream("my-data-stream") Publisher<Double> prices;

    @Inject
    @Stream("transactions") Publisher<KafkaMessage<Integer, String>> transactions;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<Double> stream() {
        return prices;
    }

    @GET
    @Path("/transaction-stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<KafkaMessage<Integer, String>> transactionStream() {
        return transactions;
    }

//    @Outgoing("generated-price")
}
