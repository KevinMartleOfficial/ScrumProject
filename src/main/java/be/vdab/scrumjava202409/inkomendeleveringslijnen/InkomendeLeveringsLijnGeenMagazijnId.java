package be.vdab.scrumjava202409.inkomendeleveringslijnen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record InkomendeLeveringsLijnGeenMagazijnId(@NotBlank @Positive long inkomendeLeveringsId,
                                                   @NotBlank @Positive long artikelId,
                                                   @NotBlank @PositiveOrZero int aantalGoedgekeurd,
                                                   @NotBlank @PositiveOrZero int aantalTeruggestuurd) {

}
