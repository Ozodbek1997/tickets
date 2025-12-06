package com.portfolio.tickets.controllers;

import com.portfolio.tickets.domain.dtos.CreateEventRequestDto;
import com.portfolio.tickets.domain.dtos.CreateEventResponseDto;
import com.portfolio.tickets.domain.entities.Event;
import com.portfolio.tickets.domain.request.CreateEventRequest;
import com.portfolio.tickets.mappers.EventMapper;
import com.portfolio.tickets.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.portfolio.tickets.util.JwtUtil.parseUserId;


@RestController
@RequestMapping(path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

  private final EventMapper eventMapper;
  private final EventService eventService;

  @PostMapping
  public ResponseEntity<CreateEventResponseDto> createEvent(
      @AuthenticationPrincipal Jwt jwt,
      @Valid @RequestBody CreateEventRequestDto createEventRequestDto) {
    CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
    UUID userId = parseUserId(jwt);

    Event createdEvent = eventService.createEvent(userId, createEventRequest);
    CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createdEvent);
    return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
  }

}
