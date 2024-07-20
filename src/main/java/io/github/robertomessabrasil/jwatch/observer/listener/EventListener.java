package io.github.robertomessabrasil.jwatch.observer.listener;

import java.util.ArrayList;
import java.util.List;

public abstract class EventListener {
    private final List<Class<? extends Event>> eventsOfInterest = new ArrayList<>();

    public abstract boolean handleEvent(Event event);

    public final EventListener addEvent(Class<? extends Event> event) {
        this.eventsOfInterest.add(event);
        return this;
    }

    public final List<Class<? extends Event>> getEventsOfInterest() {
        return this.eventsOfInterest;
    }

}
