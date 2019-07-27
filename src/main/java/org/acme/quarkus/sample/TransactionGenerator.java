package org.acme.quarkus.sample;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.acme.quarkus.sample.domain.Transaction;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class TransactionGenerator {

    private Random random = new Random();

    private Jsonb jsonb = JsonbBuilder.create();

    @Outgoing("transactions")
    public Flowable<KafkaMessage<Integer, String>> generate() {
        Transaction transaction = new Transaction();
        return Flowable.interval(5, TimeUnit.SECONDS)
            .map(tick -> KafkaMessage.of(
                random.nextInt(),
                jsonb.toJson(transaction.setAmount(BigDecimal.valueOf(random.nextDouble())))
            ));
    }
}
