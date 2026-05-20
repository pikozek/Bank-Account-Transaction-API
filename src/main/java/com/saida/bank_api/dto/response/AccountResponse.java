package com.saida.bank_api.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {
    private Long id;
    private String ownerName;
    private String accountNumber;
    private BigDecimal balance;
    private String currency;
    private LocalDateTime createdAt;
}