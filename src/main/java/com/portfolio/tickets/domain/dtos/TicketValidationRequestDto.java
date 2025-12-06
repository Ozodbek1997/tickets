package com.portfolio.tickets.domain.dtos;

import com.portfolio.tickets.domain.enums.TicketValidationMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketValidationRequestDto {
  private UUID id;
  private com.portfolio.tickets.domain.enums.TicketValidationMethod method;
}
