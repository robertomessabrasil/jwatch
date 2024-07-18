package io.github.robertomessabrasil.jwatch.observer.listener;

import java.util.ArrayList;
import java.util.List;

abstract public class EventListener {
    private final List<Class<? extends Event>> eventsOfInterest = new ArrayList<>();

    public abstract boolean handleEvent(Event event);

    final public EventListener addEvent(Class<? extends Event> event) {
        this.eventsOfInterest.add(event);
        return this;
    }

    final public List<Class<? extends Event>> getEventsOfInterest() {
        return this.eventsOfInterest;
    }

}
