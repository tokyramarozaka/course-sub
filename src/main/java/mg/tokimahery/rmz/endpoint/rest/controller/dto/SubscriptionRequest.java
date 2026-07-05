package mg.tokimahery.rmz.endpoint.rest.controller.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import mg.tokimahery.rmz.model.SubscriptionStatus;

@Builder
public record SubscriptionRequest(
    UUID id, Instant createdAt, SubscriptionStatus status, UUID userId) {}
