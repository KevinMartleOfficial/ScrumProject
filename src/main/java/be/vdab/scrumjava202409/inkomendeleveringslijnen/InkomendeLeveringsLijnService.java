package be.vdab.scrumjava202409.inkomendeleveringslijnen;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // readOnly = true zetten misschien?
public class InkomendeLeveringsLijnService {

    private InkomendeLeveringsLijnRepository inkomendeLeveringsLijnRepository;

    public InkomendeLeveringsLijnService(InkomendeLeveringsLijnRepository inkomendeLeveringsLijnRepository) {
        this.inkomendeLeveringsLijnRepository = inkomendeLeveringsLijnRepository;
    }

    public void voegInkomendeLeveringsLijnenToe(List<InkomendeLeveringsLijnGeenMagazijnId> inkomendeLeveringsLijnList) {
        //TODO Hier moet algo nog toegepast worden voor juiste magazijnplaatsen
        inkomendeLeveringsLijnList.stream()
                .map(lijn ->
                        new InkomendeLeveringsLijn(lijn.inkomendeLeveringsId(), lijn.artikelId(),
                                lijn.aantalGoedgekeurde() //'e' op het einde verwijderen
                                        , lijn.aantalTeruggestuurd(), 1))
                .forEach(lijn ->
                        inkomendeLeveringsLijnRepository.voegInkomendeLeveringsLijnToe(lijn));
    }
}
