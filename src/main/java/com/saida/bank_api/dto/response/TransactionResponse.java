package com.saida.bank_api.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long id;
    private Long fromAccountId;
    private Long toAccountId;
    private String type;
    private String description;
    private String amount;
    private String timestamp;

}
