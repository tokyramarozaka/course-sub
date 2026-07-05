package mg.tokimahery.rmz.endpoint.event.model;

import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import mg.tokimahery.rmz.model.Subscription;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class SubscriptionCreated extends PojaEvent {
  private final Subscription subscription;

  @Override
  public Duration maxConsumerDuration() {
    return Duration.ofSeconds(45);
  }

  @Override
  public Duration maxConsumerBackoffBetweenRetries() {
    return Duration.ofSeconds(60);
  }
}
