package com.portfolio.tickets.services.impl;

import com.portfolio.tickets.domain.entities.Event;
import com.portfolio.tickets.domain.entities.TicketType;
import com.portfolio.tickets.domain.entities.User;
import com.portfolio.tickets.domain.request.CreateEventRequest;
import com.portfolio.tickets.exceptions.UserNotFoundException;
import com.portfolio.tickets.repository.EventRepository;
import com.portfolio.tickets.repository.UserRepository;
import com.portfolio.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final UserRepository userRepository;
  private final EventRepository  eventRepository;


    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {

        User orginizer = userRepository.findById(organizerId)
                .orElseThrow(()-> new UserNotFoundException(
                        String.format("User with id '%s' not found",organizerId)
                ));
        Event eventToCreate = new Event();

        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream().map(
                ticketType -> {
                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    ticketTypeToCreate.setEvent(eventToCreate);
                    return ticketTypeToCreate;
                }).toList();


        eventToCreate.setName(event.getName());
        eventToCreate.setStart(event.getStart());
        eventToCreate.setEnd(event.getEnd());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setOrganizer(orginizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }
}
