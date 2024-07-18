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
                if (event.getClass().equals(eventOfInterest)) {
                    if (eventListener.handleEvent(event)) {
                        if (this.interruptEvent == null) {
                            this.interruptEvent = event;
                        }
                    }
                }
            }
        }

        if (this.interruptEvent != null) {
            throw new InterruptException();
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
