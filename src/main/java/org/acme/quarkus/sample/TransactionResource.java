package org.acme.quarkus.sample;

import io.smallrye.reactive.messaging.annotations.Stream;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * A simple resource retrieving the in-memory "my-data-stream" and sending the items as server-sent events.
 */
@Path("/transactions")
public class TransactionResource {

    private Jsonb jsonb = JsonbBuilder.create();

    @Inject
    @Stream("transactions") Publisher<KafkaMessage<Integer, String>> transactions;

    @GET
    @Path("/transaction-stream")
    @Produces({MediaType.SERVER_SENT_EVENTS, MediaType.APPLICATION_JSON})
    public Publisher<KafkaMessage<Integer, String>> transactionStream() {
        return transactions;
    }

}
