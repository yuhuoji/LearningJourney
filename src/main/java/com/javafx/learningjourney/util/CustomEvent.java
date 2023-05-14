package com.javafx.learningjourney.util;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * 封装了事件相关的数据
 */
public class CustomEvent extends Event {
    public static final EventType<CustomEvent> CUSTOM_EVENT_TYPE = new EventType<>(Event.ANY, "CUSTOM_EVENT");

    private final String eventMessage;

    public CustomEvent(String eventMessage) {
        super(CUSTOM_EVENT_TYPE);
        this.eventMessage = eventMessage;
    }

    public String getEventMessage() {
        return eventMessage;
    }
}
