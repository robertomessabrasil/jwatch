package io.github.robertomessabrasil.jwatch.observer;

import io.github.robertomessabrasil.jwatch.exception.InterruptException;
import io.github.robertomessabrasil.jwatch.observer.listener.Event;
import io.github.robertomessabrasil.jwatch.observer.listener.EventListener;

import java.util.ArrayList;
import java.util.List;

public class EventObserver {
    private Event interruptEvent;
    private List<EventListener> listeners = new ArrayList<>();

    public EventObserver subscribe(EventListener listener) {
        listeners.add(listener);
        return this;
    }

    public void notify(Event event) throws InterruptException {

        for (EventListener eventListener : this.listeners) {
            for (Class<? extends Event> eventOfInterest : eventListener.getEventsOfInterest()) {
                boolean isEventOfInterest = event.getClass().equals(eventOfInterest);
                if (isEventOfInterest) {
                    this.mustInterrupt(eventListener, event);
                }
            }
        }

        throwInterruptException();

    }

    private void throwInterruptException() throws InterruptException {
        boolean isInterruptEventNull = this.interruptEvent != null;
        if (isInterruptEventNull) {
            throw new InterruptException();
        }
    }

    private void mustInterrupt(EventListener eventListener, Event event) {
        boolean mustInterrupt = eventListener.handleEvent(event) && (this.interruptEvent == null);
        if (mustInterrupt) {
            this.interruptEvent = event;
        }
    }

    public List<EventListener> getListeners() {
        return listeners;
    }

    public EventObserver setListeners(List<EventListener> listeners) {
        this.listeners = listeners;
        return this;
    }

    public Event getInterruptEvent() {
        return interruptEvent;
    }

    public EventObserver setInterruptEvent(Event interruptEvent) {
        this.interruptEvent = interruptEvent;
        return this;
    }

    public boolean isInterrupt() {
        return this.interruptEvent != null;
    }

    public void reset() {
        this.interruptEvent = null;
    }
}
