package be.vdab.scrumjava202409.inkomendeLeveringen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record InkomendeLeveringMetNaam(@NotBlank String naam, @NotBlank String leveringsbonNummer,
                                       @NotNull @PastOrPresent LocalDate leveringsbondatum)
{
}
