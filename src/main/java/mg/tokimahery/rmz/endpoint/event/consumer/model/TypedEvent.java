package mg.tokimahery.rmz.endpoint.event.consumer.model;

import mg.tokimahery.rmz.PojaGenerated;
import mg.tokimahery.rmz.endpoint.event.model.PojaEvent;

@PojaGenerated
public record TypedEvent(String typeName, PojaEvent payload) {}
