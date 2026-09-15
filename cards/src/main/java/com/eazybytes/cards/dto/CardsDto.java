package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Schema(
        name="Cards",
        description = "Schema to hold Card Information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardsDto {
    @NotEmpty(message = "Mobile Number can not be a null or empty" )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile Number of Eazy Bank Cards", example = "9912345678"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card Number can not be a null or empty" )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Card Number of Eazy Bank Cards", example = "5432198765"
    )
    private String cardNumber;

    @NotEmpty(message = "Card Type not be a null or empty" )
     @Schema(
            description = "Card Type of Eazy Bank Cards", example = "Visa"
    )
    private String cardType;

    @NotEmpty(message = "Card limit not be a null or empty" )
    @Schema(
            description = "Card Limit of Eazy Bank Cards", example = "0.0"
    )
    private int totalLimit;

    @NotEmpty(message = "Card amount used not be a null or empty" )
    @Schema(
            description = "Card amount used of Eazy Bank Cards", example = "0.00"
    )
    private int amountUsed;

    @NotEmpty(message = "Card available amount not be a null or empty" )
    @Schema(
            description = "Card available amount  of Eazy Bank Cards", example = "0.00"
    )
    private int availableAmount;



}
