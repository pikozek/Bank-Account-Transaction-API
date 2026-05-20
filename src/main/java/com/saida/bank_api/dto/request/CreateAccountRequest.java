package com.saida.bank_api.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountRequest {
    @NotBlank(message = "Owner name is required")
    @Size(min = 2, max = 100, message = "Owner name must be 2-100 characters")
    private String ownerName;

    @NotBlank(message = "Currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a 3-letter ISO code (e.g., KZT)")
    private String currency;

    @DecimalMin(value = "0.00", message = "Initial deposit cannot be negative")
    private java.math.BigDecimal initialDeposit;
}
