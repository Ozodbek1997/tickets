package com.portfolio.tickets.services.impl;

import com.portfolio.tickets.domain.entities.Event;
import com.portfolio.tickets.domain.request.CreateEventRequest;
import com.portfolio.tickets.repository.UserRepository;
import com.portfolio.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final UserRepository userRepository;


    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        return null;
    }
}
