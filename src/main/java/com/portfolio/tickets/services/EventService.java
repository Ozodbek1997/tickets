package com.portfolio.tickets.services;

import com.portfolio.tickets.domain.entities.Event;
import com.portfolio.tickets.domain.request.CreateEventRequest;

import java.util.UUID;

public interface EventService {

  Event createEvent(UUID organizerId, CreateEventRequest event);

}
