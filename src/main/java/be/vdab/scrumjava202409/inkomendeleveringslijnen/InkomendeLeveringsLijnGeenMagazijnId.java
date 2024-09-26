package be.vdab.scrumjava202409.inkomendeleveringslijnen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record InkomendeLeveringsLijnGeenMagazijnId(@NotNull @Positive long inkomendeLeveringsId,
                                                   @NotNull @Positive long artikelId,
                                                   @NotNull @PositiveOrZero int aantalGoedgekeurd,
                                                   @NotNull @PositiveOrZero int aantalTeruggestuurd) {

}
