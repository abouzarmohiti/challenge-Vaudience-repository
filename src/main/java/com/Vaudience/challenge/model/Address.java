package com.Vaudience.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Address {

    @NotBlank(message = "city is mandatory")
    private String city;

    @NotBlank(message = "postalCode is mandatory")
    private String postalCode;
}
